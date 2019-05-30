package com.crm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	
	static String config = "\\Users\\Raj Oberoi\\CRMProject\\CRMProjectSelenium\\src\\main\\java\\com\\crm\\configFile\\config.properties";
//	String chromeDriver = prop.getProperty("chromeDriver");

	public static void loadconfigFile() {

		try {
			prop = new Properties();
			FileInputStream loadConfig = new FileInputStream(config);
			prop.load(loadConfig);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openBrowser() throws Exception {

		String BrowserName = prop.getProperty("browser");
		if (BrowserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriver"));
			driver = new ChromeDriver(options);
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public WebElement explicitWait(WebElement elementToBeLoaded) {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));		
		return elementToBeLoaded;
	}
	
	public static String randomWord(int numberOfChar) {

		String shortID = "";
		String x = "";
		for (int z = 1; z <= 1; z++) {
			shortID = RandomStringUtils.randomAlphabetic(numberOfChar);
			x = x + shortID.toUpperCase();
		}
		return x;
	
	}
	
	public static void tearDown() {
		
		driver.close();
		
	}
	

}
