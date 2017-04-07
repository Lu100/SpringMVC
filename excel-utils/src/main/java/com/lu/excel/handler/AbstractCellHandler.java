package com.lu.excel.handler;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description: 表格处理抽象类</b>
 * </pre>
 */
public interface AbstractCellHandler<T> {
    void handle(T data, Workbook workbook, Sheet sheet, Row row, Cell cell);
}
