package com.lu.excel.statistics;

import org.apache.poi.ss.usermodel.*;

/**
 * Created by Administrator on 2017/4/7.
 */
public class CounterStatistics implements AbstractStatistics<Number> {
    private int counter = 0;

    @Override
    public void values(Number value) {
        counter += 1;
    }

    @Override
    public void show(Workbook workbook, Sheet sheet, Row row, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(String.format("The count of this column is [%d]", counter));
    }
}
