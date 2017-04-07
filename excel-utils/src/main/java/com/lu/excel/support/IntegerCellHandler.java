package com.lu.excel.support;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description: 整数类型处理</b>
 * </pre>
 */
public class IntegerCellHandler extends CellHandlerWrapper<Integer> {
    @Override
    public void handle(Integer data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        cell.setCellValue(String.valueOf(data));
        cell.setCellStyle(centerAlign(workbook));
    }
}
