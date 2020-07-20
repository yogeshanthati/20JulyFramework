package com.pa.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;

public class ExcelReader {

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName, String testCaseName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(Constants.Path_TestData));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowCount = 0;
		sheet = book.getSheet(sheetName);
		for (int j = 0; j < sheet.getLastRowNum(); j++) {
			String tcName = sheet.getRow(j).getCell(0).toString();
			if (tcName.equals(testCaseName)) {
				rowCount = j;
				break;
			}
		}
		System.out.println(rowCount - 2);
		System.out.println(sheet.getRow(rowCount).getLastCellNum());
		
		boolean flag = false;
		Object[][] data = new Object[rowCount - 2][sheet.getRow(rowCount).getLastCellNum()-2];
		for (int i = rowCount,i1=0; i1<rowCount - 2 ; i++,i1++) {
			for (int k = 2,k1 = 0; k1 < sheet.getRow(rowCount).getLastCellNum()-2; k++,k1++) {
				String value = sheet.getRow(i + 1).getCell(k).toString();
				if (value == "") {
					flag = true;
					break;
				} else {
					data[i1][k1] = value;
				}
			}
		}
		return data;

	}
}
