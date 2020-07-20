package com.pa.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pa.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class BasketPage extends TestBase {

	public BasketPage() {
		super();
	}

	@FindBy(xpath = "//input[@type='submit' and @value='Update Basket']")
	private WebElement btnUpdateBasket;
	@FindBy(xpath = "//input[@type='number' and @title='Qty']")
	private WebElement txtQuantity;
	@FindBy(xpath = "//tr[@class='cart_item']/td[ @data-title='Product']")
	private WebElement cartProductSingle;
	@FindBys(@FindBy(xpath = "//tr[@class='cart_item']/td[ @data-title='Product']"))
	private List<WebElement> cartProduct;
	@FindBys(@FindBy(xpath = "//tr[@class='cart_item']/td[ @data-title='Price']"))
	private List<WebElement> cartPrice;
	@FindBys(@FindBy(xpath = "//tr[@class='cart_item']/td[ @data-title='Quantity']"))
	private List<WebElement> cartQuantity;
	@FindBys(@FindBy(xpath = "//tr[@class='cart_item']/td[ @data-title='Total']"))
	private List<WebElement> cartTotal;
	@FindBy(xpath = "//tr[@class='cart_item']//following::a[@title='Remove this item']")
	private WebElement btnRemove;

	public String getBasketProductName() {

		return cartProductSingle.getText();
	}

	@Override
	protected void VerifyValidPage() {
		// TODO Auto-generated method stub
		if (btnUpdateBasket.isDisplayed()) {
			test.log(LogStatus.PASS, "Sucessfully validated the Page ");
		} else {
			test.log(LogStatus.FAIL, "Failed To Validate The page");
			Assert.assertTrue(false);
		}

	}

	@Override
	protected void WaitForPageLoad() {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//tr[@class='cart_item']//following::a[@title='Remove this item']")));
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.getMessage());
		}
	}

}
