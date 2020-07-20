package com.pa.qa.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.qameta.allure.Step;

public class DataBaseConnection extends Constants {

	public static List<String> getDBData(String fieldName) throws SQLException {
		List<String> lstdata = new ArrayList<String>();
		try {
			stmt = connect.createStatement();
			rs = stmt.executeQuery(prop.getProperty("Query"));
			while (rs.next()) {
				System.out.println(rs.getString(fieldName));
				lstdata.add(rs.getString(fieldName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstdata;
	}
@Step("get the db data of field {0}")
	public static String getDBLoginData(String fieldName) throws SQLException {
		String res = null;
		stmt = connect.createStatement();
		rs = stmt.executeQuery(prop.getProperty("query"));
		while (rs.next()) {
			res = rs.getString(fieldName);
			System.out.println(res);
		}
		return res;

	}
}
