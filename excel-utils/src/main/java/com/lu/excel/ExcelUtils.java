package com.lu.excel;


import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class ExcelUtils {
    /**
     * 创建一个2007版本的Excel表格
     *
     * @return
     */
    public static Workbook new2007VersionExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        return workbook;
    }
    /**
     * 创建一个2003版本的Excel表格
     *
     * @return
     */
    public static Workbook new2003VersionExcel() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        return workbook;
    }

    public static File saveWorkbook(Workbook sheets, File file) throws IOException {
        OutputStream outputStream = FileUtils.openOutputStream(file);
        sheets.write(outputStream);
        return file;
    }

    public static File saveWorkbook(Workbook sheets, String path) throws IOException {
        File file = new File(path);
        return saveWorkbook(sheets, file);
    }

    public static File saveWorkbook(Workbook sheets) throws IOException {
        File file = generateFile();
        return saveWorkbook(sheets, file);
    }

    //测试
//    private static final String TEMP_DIR = "E://xls/";
    //临时目录
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static final File TEMP_DIR_FILE = new File(TEMP_DIR);
    private static final String SUFFIX = "xls";

    private static File generateFile() throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "") + "." + SUFFIX;
        File target = new File(TEMP_DIR_FILE, uuid);
        target.createNewFile();
        return target;
    }

}
