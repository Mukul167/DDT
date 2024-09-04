package com.mobile;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class web04 {

    private WebDriver chromeDriver;
    private WebDriver firefoxDriver;
    private String screenshotFileName;

    @BeforeMethod
    public void setUp() throws Exception {
        
        ScreenRecorderUtil.startRecord("web04_Mobile");

        Date currentDate = new Date();
        screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeDriver = new ChromeDriver(chromeOptions);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        firefoxDriver = new FirefoxDriver(firefoxOptions);
    }

    @Test
    public void captureScreenshots() throws Exception {
        List<Dimension> resolutions = new ArrayList<>();
        resolutions.add(new Dimension(360, 640));
        resolutions.add(new Dimension(414, 896));
        resolutions.add(new Dimension(375, 667));

        captureScreenshotsForDevice(chromeDriver, "Chrome", resolutions);
        captureScreenshotsForDevice(firefoxDriver, "Firefox", resolutions);
    }

    public void captureScreenshotsForDevice(WebDriver driver, String deviceName, List<Dimension> resolutions) throws Exception {
        for (Dimension resolution : resolutions) {
            int width = resolution.getWidth();
            int height = resolution.getHeight();
            Dimension dimension = new Dimension(width, height);
            driver.manage().window().setSize(dimension);
            driver.get("https://www.getcalley.com/calley-teams-features/");

            String filepath = "D:\\Pictures\\Mobile\\";
            String filename = deviceName + "_" + width + "x" + height + "_" + screenshotFileName + ".png";

            Shutterbug.shootPage(driver, Capture.FULL, true).save(filepath + filename);
        }
    }

    @AfterMethod
    public void End() throws Exception {
       
        chromeDriver.quit();
        firefoxDriver.quit();
        ScreenRecorderUtil.stopRecord();
    }
}
