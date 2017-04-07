package com.lu.excel.statistics;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 统计抽象类
 */
public interface AbstractStatistics<T> {
    /**
     * 根据指定的字段或方法 ，迭代数据
     */
    void values(T value);

    /**
     * 具体如何显示数据
     */
    void show(Workbook workbook, Sheet sheet, Row row, Cell cell);

}
