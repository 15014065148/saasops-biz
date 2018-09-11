package com.eveb.poiexcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LbTest1 {
    public static void main(String[] args) {
        // 读取Excel表格
        List<LbTest> list = readExcel("D:/lbTest.xls");
        System.out.println(list.toString());
    }

    /**
     * 读取Excel
     *
     * @return 数据集合
     */
    private static List<LbTest> readExcel(String fileStr) {
        List<LbTest> list = new ArrayList<LbTest>();
        HSSFWorkbook workbook = null;
        try {
            // 读取Excel文件
            InputStream inputStream = new FileInputStream(fileStr);
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 循环工作表
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (Objects.isNull(hssfRow)) continue;
                // 将单元格中的内容存入集合
                LbTest lbTest = new LbTest();
                for (int i = 0; i < hssfRow.getLastCellNum(); i++) {

                }
                HSSFCell cell = hssfRow.getCell(0);
                if (isAllCell(cell)) continue;
                lbTest.setName(cell.getStringCellValue());

                cell = hssfRow.getCell(1);
                if (isAllCell(cell)) continue;
                lbTest.setYxtz((Double) cell.getNumericCellValue());

                cell = hssfRow.getCell(2);
                if (isAllCell(cell)) continue;
                lbTest.setPc((Double) cell.getNumericCellValue());

                cell = hssfRow.getCell(3);
                if (isAllCell(cell)) continue;
                lbTest.setDl((Double) cell.getNumericCellValue());

                cell = hssfRow.getCell(4);
                if (isAllCell(cell)) continue;
                lbTest.setBbinTz((Double) cell.getNumericCellValue());

                cell = hssfRow.getCell(5);
                if (isAllCell(cell)) continue;
                lbTest.setBbinPc((Double) cell.getNumericCellValue());

                cell = hssfRow.getCell(6);
                if (isAllCell(cell)) continue;
                lbTest.setBbinDl((Double) cell.getNumericCellValue());

                cell = hssfRow.getCell(7);
                if (isAllCell(cell)) continue;
                lbTest.setCe((Double) cell.getNumericCellValue());
                list.add(lbTest);
            }
        }
        return list;
    }

    private static boolean isAllCell(HSSFCell cell) {
        if (cell == null) {
            return true;
        }
        return false;
    }
}
