package com.desktop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.mobile.ScreenRecorderUtil;

public class web03 {

	private WebDriver chromeDriver;
	private WebDriver firefoxDriver;
	private String screenshotFileName;

	@BeforeMethod
	public void setUp() throws Exception {

		ScreenRecorderUtil.startRecord("web03-DeskTop");

		Date currentDate = new Date();
		screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
		System.out.println(screenshotFileName);
	}

	@Test(priority = 1)
	public void OpenChrome() throws Exception {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		chromeDriver = new ChromeDriver(chromeOptions);
		List<Dimension> resolutions = new ArrayList<>();
		resolutions.add(new Dimension(1920, 1080));
		resolutions.add(new Dimension(1366, 768));
		resolutions.add(new Dimension(1536, 864));
		captureScreenshotsForDevice(chromeDriver, "desktop", resolutions);
		/*
		 * FirefoxOptions firefoxOptions = new FirefoxOptions();
		 * firefoxOptions.addPreference("dom.webnotifications.enabled", false);
		 * firefoxDriver = new FirefoxDriver(firefoxOptions);
		 */
	}

	@Test(priority = 2)
	public void OpenFireFox() throws Exception {

		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addPreference("dom.webnotifications.enabled", false);

		List<Dimension> resolutions = new ArrayList<>();
		resolutions.add(new Dimension(1920, 1080));
		resolutions.add(new Dimension(1366, 768));
		resolutions.add(new Dimension(1536, 864));

		// captureScreenshotsForDevice(chromeDriver, "desktop", resolutions);
		captureScreenshotsForDevice(firefoxDriver, "desktop", resolutions);
	}

	public void captureScreenshotsForDevice(WebDriver driver, String deviceName, List<Dimension> resolutions)
			throws Exception {
		for (Dimension resolution : resolutions) {
			int width = resolution.getWidth();
			int height = resolution.getHeight();
			Dimension dimension = new Dimension(width, height);
			driver.manage().window().setSize(dimension);
			driver.get("https://www.getcalley.com/see-a-demo/");

			String filePath = "D:\\Pictures\\Desktop\\";
			String filename = deviceName + "_" + width + "x" + height + "_" + screenshotFileName + ".png";

			Shutterbug.shootPage(driver, Capture.FULL, true).save(filePath + filename);
		}
	}

	@AfterMethod
	public void End() throws Exception {

		/*
		 * chromeDriver.quit(); firefoxDriver.quit();
		 */
		ScreenRecorderUtil.stopRecord();
	}
}
