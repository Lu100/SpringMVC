package com.lu.excel.support;

import org.apache.poi.ss.usermodel.*;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:长整型处理</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年04月06日  14:30
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年04月06日  14:30   Luyongjia
 *         new file.
 * </pre>
 */
public class LongCellHandler extends CellHandlerWrapper<Long> {
    @Override
    public void handle(Long data, Workbook workbook, Sheet sheet, Row row, Cell cell) {
        cell.setCellValue(String.valueOf(data));
        CellStyle cellStyle = centerAlign(workbook);
        DataFormat dataFormat = workbook.createDataFormat();
        //以存文本显示
        cellStyle.setDataFormat(dataFormat.getFormat("0"));
        cell.setCellStyle(cellStyle);
    }
}
