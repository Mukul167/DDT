package com.functional;

import java.io.File;
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
import org.apache.commons.io.FileUtils;

import com.mobile.ScreenRecorderUtil;

public class dealsdray {

	public static void main(String[] args) throws Exception {
		ScreenRecorderUtil.startRecord("main");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demo.dealsdray.com/");
		
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name= 'username']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//input[@name= 'password']")).sendKeys("prexo.mis@dealsdray.com");
		
		driver.findElement(By.cssSelector(".MuiButton-root")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-1s178v5"))).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Orders')]")).click();
		
		WebElement bulk = driver.findElement(By.xpath("//button[contains(text(),'Add Bulk Orders')]"));
		bulk.click();
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']")));
			
		WebElement fileInput1 = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath = "D:\\PDF\\demo-data.xlsx";
		fileInput1.sendKeys(filePath);
		
		driver.findElement(By.xpath("//button[contains(text(),'Import')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Validate Data')]")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("validate.png"));
		ScreenRecorderUtil.stopRecord();
		
	}

}
