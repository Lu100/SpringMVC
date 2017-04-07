package com.lu.excel.handler;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description: 表格处理抽象类</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  10:45
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  10:45   Luyongjia
 * </pre>
 */
public abstract class AbstractCellHandler<T> {
    public abstract void handle(T data, Workbook workbook, Sheet sheet, Row row, Cell cell);
}
