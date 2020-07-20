package com.pa.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter; //ctr+shift+F to format the code
import com.pa.qa.util.*;
import com.relevantcodes.extentreports.ExtentReports;



public abstract class TestBase extends Constants {
	protected abstract void VerifyValidPage();

	protected abstract void WaitForPageLoad();

	public TestBase() {
		PageFactory.initElements(driver, this);
		WaitForPageLoad();       // ctrl+shift+F  to format the code 
		VerifyValidPage();
	}

	public static void Cofigurereport() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		TimeStamp = dateFormat.format(date);
		extent = new ExtentReports(
				System.getProperty("user.dir") + "/TestResults/Extentreports/" + TimeStamp + "/ExtentReport.html",
				true);
	}

	public static void configureDataBase() {

		try {
			Class.forName(prop.getProperty("mySqlDriver"));
			connect = DriverManager.getConnection(prop.getProperty("mySqlConnectURL"),
					prop.getProperty("mySqlUserName"), prop.getProperty("mySqlPassword"));
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void intialization() throws MalformedURLException {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("src/main/resources/testconfig.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"./src/main/resources/BrowserSpecificDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			Log.info("Driver Intiallized");
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/BrowserSpecificDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else {

			System.setProperty("webdriver.ie.driver", "./src/main/resources/BrowserSpecificDrivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		Reporter.log("Browser Launched successfully");// high level reporting.
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		Log.info("Url Has Entered");
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
}
























/// In Case of running test in multiple nodes and for mobile testing
/*
 * DesiredCapabilities Capabilities= DesiredCapabilities.chrome(); driver=new
 * RemoteWebDriver(new URL(" http://localhost:4444/wd/hub"),Capabilities);
 * 
 * 
 * 
 * // event firing web driver code. EventFiringWebDriver e_driver = new
 * EventFiringWebDriver(driver); // Now create object of EventListerHandler to
 * register it with EventFiringWebDriver eventListener = new WebEventListener();
 * e_driver.register(eventListener); driver = e_driver;
 */

//for docker this code:
// DesiredCapabilities dc= DesiredCapabilities.chrome();
// URL url=new URL("http://192.168.99.100:4444/wd/hub");
// driver= new RemoteWebDriver(url,dc);
