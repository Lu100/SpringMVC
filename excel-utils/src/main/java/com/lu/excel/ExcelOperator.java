package com.lu.excel;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.lu.excel.annotation.CellMarker;
import com.lu.excel.handler.AbstractCellHandler;
import com.lu.excel.statistics.AbstractStatistics;
import com.lu.excel.support.handler.StringCellHandler;
import com.lu.excel.support.statistics.NoStatistics;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:表格操作对象</b>
 * </pre>
 * 需要通过静态方法创建(ExcelOperator.newExcelOperator)
 */
public class ExcelOperator<T> {

    private Class targetClass;
    private Workbook workbook;
    private Sheet sheet;
    private int currentRow = -1;

    private boolean prepareFlag;
    private boolean finishFlag;

    private ExcelOperator(Class<T> tClass) {
        this.targetClass = tClass;
        prepare();
    }

    /**
     * 不允许外部直接实例化，需要通过静态方法生成实例
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> ExcelOperator<T> newExcelOperator(Class<T> tClass) {
        return new ExcelOperator<>(tClass);
    }

    /**
     * 追加前控制
     */
    private void prepare() {
        if (prepareFlag) return;
        synchronized (this) {
            checkClass(targetClass);
            //暂时固定为只使用2007版本的Excel格式
            workbook = ExcelUtils.new2007VersionExcel();
            sheet = workbook.createSheet();
            //一般取第一行作为Title行
            generateTitle();
            currentRow = 0;
            prepareFlag = true;
        }
    }

    /**
     * 追加数据
     *
     * @param collection 数据集合
     */
    public void append(Collection<T> collection) {
        checkState();
        for (T t : collection) {
            //统一先自增，再创建Row
            currentRow++;
            Row row = sheet.createRow(currentRow);
            setColumn(t, workbook, sheet, row);
        }
    }

    /**
     * 追加数据
     *
     * @param ts 对象数组
     */
    public void append(T[] ts) {
        checkState();
        for (T t : ts) {
            //统一先自增，再创建Row
            currentRow++;
            Row row = sheet.createRow(currentRow);
            setColumn(t, workbook, sheet, row);
        }
    }

    /**
     * 追加数据
     *
     * @param t 对象
     */
    public void append(T t) {
        checkState();
        //统一先自增，再创建Row
        currentRow++;
        Row row = sheet.createRow(currentRow);
        setColumn(t, workbook, sheet, row);
    }

    /**
     * 完成操作
     */
    public File ok() throws IOException {
        generateDetail();
        File file = ExcelUtils.saveWorkbook(workbook);
        finishFlag = true;
        return file;
    }

    /**
     * 完成操作
     */
    public File ok(String path) throws IOException {
        generateDetail();
        File file = ExcelUtils.saveWorkbook(workbook, path);
        finishFlag = true;
        return file;
    }

    /**
     * 完成操作
     */
    public File ok(File file) throws IOException {
        generateDetail();
        File excel = ExcelUtils.saveWorkbook(workbook, file);
        finishFlag = true;
        return excel;
    }

    private void checkState() {
        if (!prepareFlag) {
            throw new IllegalStateException("操作器还没有准备好！");
        }
        if (finishFlag) {
            throw new IllegalStateException("操作器已经完成工作，禁止添加数据！");
        }
    }

    /**
     * 设置一行
     *
     * @param data     数据
     * @param workbook 工作簿
     * @param sheet    表格页
     * @param row      当前行
     */
    private void setColumn(T data, Workbook workbook, Sheet sheet, Row row) {
        int cellNumber = 0;
        for (ScanResult scanResult : scanResults) {
            setValue(workbook, sheet, data, cellNumber++, row, scanResult);
        }
    }

    /**
     * 设置一个Cell值
     *
     * @param workbook   工作簿
     * @param sheet      表格页
     * @param t          数据
     * @param cellNumber 表格列数
     * @param row        当前行
     */
    private void setValue(Workbook workbook, Sheet sheet, T t, int cellNumber, Row row, ScanResult scanResult) {
        try {
            if (scanResult.type == Type.field) {
                Field declaredField = t.getClass().getDeclaredField(scanResult.name);
                declaredField.setAccessible(true);
                Object o = declaredField.get(t);
                AbstractCellHandler handler = HandlerManager.getInstance().getHandler(scanResult.handlerClass);
                handler.handle(o, workbook, sheet, row, row.createCell(cellNumber));
                scanResult.doValues(o);
            }
            if (scanResult.type == Type.method) {
                Method method = t.getClass().getDeclaredMethod(scanResult.name);
                method.setAccessible(true);
                Object result = method.invoke(t);
                AbstractCellHandler handler = HandlerManager.getInstance().getHandler(scanResult.handlerClass);
                handler.handle(result, workbook, sheet, row, row.createCell(cellNumber));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成title行
     */
    private void generateTitle() {
        int counter = 0;
        //如果需要改标题样式，可以定制下面这个处理器
        StringCellHandler handler = HandlerManager.getInstance().getHandler(StringCellHandler.class);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(0);
        for (ScanResult scanResult : scanResults) {
            sheet.setColumnWidth(counter, scanResult.width * 256);
            handler.handle(scanResult.title, workbook, sheet, row, row.createCell(counter++));
        }
    }

    /**
     * 生成统计行
     */
    private void generateDetail() {
        int counter = 0;
        currentRow++;
        Row row = sheet.createRow(currentRow);
        for (ScanResult scanResult : scanResults) {
            sheet.setColumnWidth(counter, scanResult.width * 256);
            if (scanResult.getAbstractStatistics() != null)
                scanResult.getAbstractStatistics().show(workbook, sheet, row, row.createCell(counter));
            counter++;
        }

    }


    /**
     * 参数名称与处理类的映射器
     */
    private final List<ScanResult> scanResults = Lists.newArrayList();

    /**
     * 检查这个类是否已经经过缓存
     *
     * @param clazz 需要检查的类型
     */
    private void checkClass(Class clazz) {
        if (scanResults.isEmpty()) {
            synchronized (scanResults) {
                buildHandlerMap(clazz);
            }
        }
    }

    /**
     * 创建一个字段与处理类的映射器
     */
    private void buildHandlerMap(Class targetClass) {
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //使用Spring的注解工具类
            CellMarker cell = AnnotationUtils.getAnnotation(field, CellMarker.class);
            putScanResultToCollection(cell, Type.field, field.getName());
        }
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            //使用Spring的注解工具类
            CellMarker cell = AnnotationUtils.getAnnotation(declaredMethod, CellMarker.class);
            putScanResultToCollection(cell, Type.method, declaredMethod.getName());
        }
        Collections.sort(scanResults);
    }

    private void putScanResultToCollection(CellMarker cellMarker, Type type, String name) {
        if (cellMarker != null) {
            ScanResult scanResult = new ScanResult(type, name, cellMarker.title(), cellMarker.sequence(), cellMarker.width(), cellMarker.handler());
            if (!cellMarker.statistics().equals(NoStatistics.class)) {
                try {
                    //获取统计接口
                    AbstractStatistics abstractStatistics = cellMarker.statistics().newInstance();
                    scanResult.setAbstractStatistics(abstractStatistics);
                } catch (InstantiationException | IllegalAccessException e) {
                    System.err.println(String.format("[%s] can not be instantiation!", cellMarker.statistics().getName()));
                }
            }
            scanResults.add(scanResult);
        }
    }

    private class ScanResult implements Comparable<ScanResult> {
        Type type;
        String name;
        String title;
        int sequence;
        int width;
        Class<? extends AbstractCellHandler> handlerClass;
        AbstractStatistics abstractStatistics;

        ScanResult(Type type, String name, String title, int sequence, int width, Class<? extends AbstractCellHandler> handlerClass) {
            this.type = type;
            this.name = name;
            this.title = title;
            this.sequence = sequence;
            this.width = width;
            this.handlerClass = handlerClass;
        }

        public void doValues(Object data) {
            if (abstractStatistics != null) {
                abstractStatistics.values(data);
            }
        }


        public AbstractStatistics getAbstractStatistics() {
            return abstractStatistics;
        }

        public void setAbstractStatistics(AbstractStatistics abstractStatistics) {
            this.abstractStatistics = abstractStatistics;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ScanResult that = (ScanResult) o;
            return Objects.equal(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public int compareTo(ScanResult that) {
            return this.sequence - that.sequence;
        }
    }

    enum Type {
        method, field
    }

}
