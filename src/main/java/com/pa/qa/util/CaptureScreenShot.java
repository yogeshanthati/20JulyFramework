package com.pa.qa.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.pa.qa.util.Constants;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class CaptureScreenShot {

	public static String captureScreen(WebDriver driver, String screenName) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "//TestResults//Test-ScreenShots//" + screenName
				+ Constants.TimeStamp + ".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

	public static void captureScreenOfAnElement(WebElement we, String name) {

		File src = we.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "//TestResults//Test-ScreenShots//" + name + Constants.TimeStamp
				+ ".png";
		File target = new File(dest);
		try {
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	
	@Step("Screenshot of an element :{1}")
	@Attachment(value = "Page screenshot", type = "image/png")
	public static byte[] takeScreenshotOfAnElementToAllure(WebElement we,String Message) {
		return (we).getScreenshotAs(OutputType.BYTES);
	}
	
	@Step("Snapshot below:")
	@Attachment(value = "Failure Page screenshot", type = "image/png")
	public static byte[] addScreenshotToAllure(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}

