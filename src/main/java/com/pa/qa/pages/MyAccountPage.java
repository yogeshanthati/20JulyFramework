package com.pa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pa.qa.base.TestBase;

public class MyAccountPage extends TestBase {
	protected static String MYACNT_PAGE_TITLE = "";

	public MyAccountPage() {
		super();
	}

	@Override
	protected void VerifyValidPage() {
		if (driver.getTitle().trim().contains(MYACNT_PAGE_TITLE)) {
			System.out.println("valid page");
		} else {
			System.out.println("invalid Page");
		}

	}

	@Override
	protected void WaitForPageLoad() {
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='username']")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
