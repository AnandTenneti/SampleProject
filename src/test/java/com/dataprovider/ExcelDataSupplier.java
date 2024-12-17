package com.dataprovider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static com.dataprovider.TestData.PATH_TO_EXCEL_FILE;
import static com.dataprovider.TestData.USER_DIR;

public class ExcelDataSupplier {
    @DataProvider(name = "excelLoginData")
    public String[][] getLoginData() throws IOException {
        String excelFilePath = USER_DIR + PATH_TO_EXCEL_FILE;
        File excelFile = new File(excelFilePath);
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet2");
        int noOfRows = sheet.getPhysicalNumberOfRows();
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        String loginData[][] = new String[noOfRows - 1][noOfColumns];
        for (int i = 0; i < noOfRows - 1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df = new DataFormatter();
                loginData[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
            }
        }
        workbook.close();
        fis.close();
        return loginData;
    }
}
