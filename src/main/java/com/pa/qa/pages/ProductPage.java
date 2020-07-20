package com.pa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pa.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class ProductPage extends TestBase {

//	protected static  String HOME_PAGE_TITLE="Automation Practice Site";

	public ProductPage() {
		super();
	}

	@FindBy(xpath = "//h1[@class='product_title entry-title']")
	private WebElement productTitle;

	@FindBy(xpath = "//button[@type='submit' and text()='Add to basket']")
	private WebElement btnAddToBasket;

	public String getProductTitle() {
		return productTitle.getText();
	}

	public void clickOnAddToBasket_btn() {
		if (btnAddToBasket.isEnabled()) {
			btnAddToBasket.click();
		}

	}

	@Override
	protected void VerifyValidPage() {

		if (productTitle.isDisplayed()) {
			test.log(LogStatus.PASS, "Sucessfully validated the Page ");
		} else {
			test.log(LogStatus.FAIL, "Failed To Validate The page");
			Assert.assertTrue(false);
		}
	}

	@Override
	protected void WaitForPageLoad() {
		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='product_title entry-title']")));
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage());
		}

	}

}
