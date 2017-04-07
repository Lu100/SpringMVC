package com.lu.excel.support;

import org.apache.poi.ss.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description: 普通时间类型单元格处理器</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  16:35
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  16:35   Luyongjia
 *         new file.
 * </pre>
 */
public class SimpleDateCellHandler extends CellHandlerWrapper<Date> {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();

    @Override
    public void handle(Date data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        CellStyle cellStyle = centerAlign(workbook);
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm"));
        cell.setCellValue(data);
        cell.setCellStyle(cellStyle);
    }
}
