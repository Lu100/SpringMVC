package com.lu.excel.support;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description: 整数类型处理</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  13:07
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  13:07   Luyongjia
 *         new file.
 * </pre>
 */
public class IntegerCellHandler extends CellHandlerWrapper<Integer> {
    @Override
    public void handle(Integer data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        cell.setCellValue(String.valueOf(data));
        cell.setCellStyle(centerAlign(workbook));
    }
}
