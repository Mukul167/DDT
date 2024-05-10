package com.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

public class web05 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//ScreenRecorderUtil.startRecord("main");
        Date currentDate = new Date();
        String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
        System.out.println(screenshotFileName);

        List<Dimension> resolutions = new ArrayList<>();
        resolutions.add(new Dimension(360, 640)); 
        resolutions.add(new Dimension(414, 896));  
        resolutions.add(new Dimension(375, 667));  
        
        
        WebDriver chromeDriver = new ChromeDriver();
        captureScreenshots(chromeDriver, "Mobile", resolutions, screenshotFileName);
   
      
        WebDriver firefoxDriver = new FirefoxDriver();
        captureScreenshots(firefoxDriver, "Mobile", resolutions, screenshotFileName);
        
    }

    public static void captureScreenshots(WebDriver driver, String deviceName, List<Dimension> resolutions, String screenshotFileName) throws Exception {
    	ScreenRecorderUtil.startRecord("captureScreenshots");
    	for (Dimension resolution : resolutions) {
            int width = resolution.getWidth();
            int height = resolution.getHeight();
            Dimension dimension = new Dimension(width, height);
            driver.manage().window().setSize(dimension);
            driver.get("https://www.getcalley.com/how-calley-auto-dialer-app-works/");
            
            
           // String filePath = "D://Pictures//Mobile";
            String filepath = "D:\\Pictures\\Mobile\\";
            String filename = deviceName + "_" + width + "x" + height + "_" + screenshotFileName + ".png";
            
            Shutterbug.shootPage(driver, Capture.FULL, true).save(filepath + filename);
            ScreenRecorderUtil.stopRecord();
        }
    

	}

}
