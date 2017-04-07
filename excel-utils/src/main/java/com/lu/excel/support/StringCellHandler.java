package com.lu.excel.support;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:字符串实现</b>
 * </pre>
 */
public class StringCellHandler extends CellHandlerWrapper<String> {
    @Override
    public void handle(String data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        //设置居中
        cell.setCellStyle(centerAlign(workbook));
        cell.setCellValue(data);
    }
}
