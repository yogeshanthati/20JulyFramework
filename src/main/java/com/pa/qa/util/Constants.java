package com.pa.qa.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.pa.qa.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Constants {
	public static int PAGE_LOAD_TIMEOUT = 50;
	public static int IMPLICIT_WAIT = 40;
	public static final String Path_TestData = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\MyTestData.xlsx";
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	public static Properties prop;
	public static String TimeStamp;
	public static Connection connect;
	public static Statement stmt;
	public static ResultSet rs;

	@BeforeSuite
	public void ConfigureReport() {
		TestBase.Cofigurereport();
	}

	@AfterSuite
	public void tearDown()

	{
		extent.flush();
		extent.close();

	}

}