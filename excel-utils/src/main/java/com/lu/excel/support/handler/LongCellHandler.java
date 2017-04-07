package com.lu.excel.support.handler;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:长整型处理</b>
 * </pre>
 */
public class LongCellHandler extends CellHandlerWrapper<Long> {
    @Override
    public void handle(Long data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        cell.setCellValue(String.valueOf(data));
        CellStyle cellStyle = centerAlign(workbook);
        DataFormat dataFormat = workbook.createDataFormat();
        //以存文本显示
        cellStyle.setDataFormat(dataFormat.getFormat("0"));
        cell.setCellStyle(cellStyle);
    }
}
