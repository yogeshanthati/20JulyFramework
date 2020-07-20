
package com.pa.qa.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pa.qa.base.TestBase;
import com.pa.qa.pages.*;
import com.pa.qa.util.*;
import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LogInToAppTest extends Constants {

	// written all the pages class references
	MyAccountPageRegisterAndLogin MyAccountPageRegisterAndLogin;
	String sheetName = "MyLogin";

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		TestBase.intialization();
		Log.startTestCase(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName());

	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Excel Login Test ")
	@Story("Verifying Excel data Login")
	@Test(dataProvider = "getLogInTestData")
	public void LogInToApp(String UserName, String Password, String Sample1, String Sample2) {
		MyAccountPageRegisterAndLogin = new MyAccountPageRegisterAndLogin();
		MyAccountPageRegisterAndLogin = MyAccountPageRegisterAndLogin.LogIn(UserName, Password, Sample1, Sample2);

	}

	@DataProvider
	public Object[][] getLogInTestData() {
		Object data[][] = ExcelReader.getTestData(sheetName, "LogInToApp");
		return (data);
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = CaptureScreenShot.captureScreen(driver, this.getClass().getSimpleName());
			Constants.test.log(LogStatus.FAIL, result.getThrowable());
			Constants.test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
			CaptureScreenShot.addScreenshotToAllure(driver);
		}

		driver.quit();
		extent.endTest(test);
		Log.endTestCase(this.getClass().getSimpleName());
	}

}
