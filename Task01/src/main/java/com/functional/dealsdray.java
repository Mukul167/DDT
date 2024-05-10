package com.functional;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import com.mobile.ScreenRecorderUtil;

public class dealsdray {

		private WebDriver driver;
	
		@BeforeMethod
		public void setUp() throws Exception {	
		ScreenRecorderUtil.startRecord("Functional Test");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		}
		@Test
		public void loginAndUploadFile() throws IOException {
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://demo.dealsdray.com/");
		
		//UserName
		driver.findElement(By.xpath("//input[@name= 'username']")).sendKeys("prexo.mis@dealsdray.com");
		//Password
		driver.findElement(By.xpath("//input[@name= 'password']")).sendKeys("prexo.mis@dealsdray.com");
		//LoginButton
		driver.findElement(By.cssSelector(".MuiButton-root")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-1s178v5"))).click();
		//Clicking the order button.
		driver.findElement(By.xpath("//span[contains(text(),'Orders')]")).click();
		
		WebElement bulk = driver.findElement(By.xpath("//button[contains(text(),'Add Bulk Orders')]"));
		bulk.click();
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']")));
		
		//Uploading the file
		WebElement fileInput1 = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath = "D:\\PDF\\demo-data.xlsx";
		fileInput1.sendKeys(filePath);
		
		driver.findElement(By.xpath("//button[contains(text(),'Import')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Validate Data')]")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("validate.png"));
		}
		
		@AfterMethod
		public void stopRecording() throws Exception {
		ScreenRecorderUtil.stopRecord();
		}
	

}
