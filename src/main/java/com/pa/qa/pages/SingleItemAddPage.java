package com.pa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pa.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SingleItemAddPage extends TestBase {
	SingleItemAddPage() {
		super();
	}

	protected static String HOME_PAGE_TITLE = "Selenium Ruby – Automation Practice Site";

	@Override
	protected void VerifyValidPage() {

		if (driver.getTitle().trim().contains(HOME_PAGE_TITLE)) {
			test.log(LogStatus.PASS, "Sucessfully validated the Page " + HOME_PAGE_TITLE);
		} else {
			test.log(LogStatus.FAIL, "Failed To Validate The page" + HOME_PAGE_TITLE);
			Assert.assertTrue(false);

		}
	}


	@Override
	protected void WaitForPageLoad() {
		try {
			new WebDriverWait(driver, 40)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='add-to-cart']")));
		} catch (Exception e) {
			test.log(LogStatus.INFO, e.getMessage());

		}
	}

}
