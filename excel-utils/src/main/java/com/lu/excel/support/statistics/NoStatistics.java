package com.lu.excel.support.statistics;

import com.lu.excel.statistics.AbstractStatistics;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 不进行统计
 * Created by Administrator on 2017/4/7.
 */
public final class NoStatistics extends AbstractStatistics<Void> {
    @Override
    public void values(Void value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void show(Workbook workbook, Sheet sheet, Row row, Cell cell) {
        throw new UnsupportedOperationException();
    }
}
