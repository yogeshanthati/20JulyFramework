package com.pa.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	static XSSFWorkbook book;
	static XSSFSheet sheet;

	public static Object[][] getTestData(String sheetName, String testCaseName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(Constants.Path_TestData));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = new XSSFWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowCount = 0;
		sheet = book.getSheet(sheetName);
		for (int j = 0; j < sheet.getLastRowNum(); j++) {
			String tcName = sheet.getRow(j).getCell(0).getStringCellValue();
			if (tcName.equals(testCaseName)) {
				rowCount = j;
				break;
			}
		}
		int rowCount1 = rowCount;
		int Size = 0;
		int s = 0;
		while (true) {
			if ((sheet.getRow(rowCount1 + 1).getCell(3).getStringCellValue().equals(""))) {
				Size = s;
				break;
			}
			if (sheet.getRow(rowCount1 + 1).getCell(2).getStringCellValue().equalsIgnoreCase("Yes")) {
				s++;
			}
			rowCount1++;
		}

		Object[][] data = new Object[Size][sheet.getRow(rowCount).getLastCellNum() - 3];
		for (int i = rowCount, i1 = 0; i1 < Size; i++, i1++) {
			for (int k = 3, k1 = 0; k1 < sheet.getRow(rowCount).getLastCellNum() - 3; k++, k1++) {
				String value = sheet.getRow(i + 1).getCell(k).getStringCellValue();
				if (value == "") {
					break;
				} else {
					if (sheet.getRow(i + 1).getCell(2).getStringCellValue().equalsIgnoreCase("Yes")) {
						data[i1][k1] = value;
					}
				}
			}

		}
		return data;
	}
}
