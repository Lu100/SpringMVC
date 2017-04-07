package com.lu.excel.handler;

import com.lu.excel.support.CellHandlerWrapper;
import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月07日  8:43
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月07日  8:43   Luyongjia
 *         new file.
 * </pre>
 */
public class CustomHandler extends CellHandlerWrapper<String> {
    @Override
    public void handle(String data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        CellStyle cellStyle = centerAlign(workbook);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("这一行是自定义的！");
    }
}
