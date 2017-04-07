package com.lu.excel.support;

import com.lu.excel.handler.AbstractCellHandler;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;


public abstract class CellHandlerWrapper<T> extends AbstractCellHandler<T> {
    /**
     * 设置居中
     */
    protected CellStyle centerAlign(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
}
