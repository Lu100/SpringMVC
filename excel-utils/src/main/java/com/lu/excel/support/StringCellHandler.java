package com.lu.excel.support;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:字符串实现</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  10:51
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  10:51   Luyongjia
 *         new file.
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
