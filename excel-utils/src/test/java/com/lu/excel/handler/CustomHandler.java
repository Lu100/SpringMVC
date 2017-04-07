package com.lu.excel.handler;

import com.lu.excel.support.handler.CellHandlerWrapper;
import org.apache.poi.ss.usermodel.*;

public class CustomHandler extends CellHandlerWrapper<String> {
    @Override
    public void handle(String data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        CellStyle cellStyle = centerAlign(workbook);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("这一行是自定义的！");
    }
}
