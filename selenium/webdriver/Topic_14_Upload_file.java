 
package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_14_Upload_file {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String projectFolder = System.getProperty("user.dir");
	
	String autumnImgName = "Upload 1.jpg";
	String forestImgName = "Upload 2.jpg";
	String lakeImgName = "Upload 3.jpeg";
	
	String autumnImgPath = projectFolder + "//uploadFiles//" + autumnImgName;
	String forestImgPath = projectFolder + "//uploadFiles//" + forestImgName;
	String lakeImgPath = projectFolder + "//uploadFiles//" + lakeImgName;
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	}
	
//	@Test
//	public void TC_01_Sendkey_1_File() {
//		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
//		
//		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//
//		//1 file per time
//		uploadFile.sendKeys(autumnImgPath);
//		sleepInSeconds(2);
//		
//		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		uploadFile.sendKeys(forestImgPath);
//		sleepInSeconds(2);	
//		
//		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		uploadFile.sendKeys(lakeImgPath);
//		sleepInSeconds(2);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + autumnImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + forestImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + lakeImgName + "']")).isDisplayed());
//		
//		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
//		for (WebElement start : startButton) {
//			start.click();
//			sleepInSeconds(1);
//		}
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + autumnImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + forestImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + lakeImgName + "']")).isDisplayed());
//		
//	}
//	
//	@Test
//	public void TC_02_Sendkey_Multiple_file() {
//		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
//		
//		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		
//		uploadFile.sendKeys(autumnImgPath + "\n" + forestImgPath + "\n" + lakeImgPath);
//		sleepInSeconds(2);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + autumnImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + forestImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" + lakeImgName + "']")).isDisplayed());
//		 
//		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
//		for (WebElement start : startButton) {
//			start.click();
//			sleepInSeconds(1);
//		}
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + autumnImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + forestImgName + "']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + lakeImgName + "']")).isDisplayed());
//		}
	
	@Test
	public void TC_03_Exercise_04() {
		driver.get("https://gofile.io/uploadFiles");
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//input[@name='filesUploaded']")).sendKeys(autumnImgPath + "\n" + forestImgPath + "\n" + lakeImgPath);
		sleepInSeconds(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text() = '" + autumnImgName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text() = '" + forestImgName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text() = '" + lakeImgName + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@id='uploadFiles-btnUpload']")).click();
		
		driver.findElement(By.xpath("//a[@id='uploadFiles-uploadRowResult-downloadLink']")).click();
		
		switchToWindowByID(parentID);
		
		Assert.assertTrue(isDownloadIconDisplayed(autumnImgName, "download"));
		Assert.assertTrue(isDownloadIconDisplayed(forestImgName, "download"));
		Assert.assertTrue(isDownloadIconDisplayed(lakeImgName, "download"));
		
		Assert.assertTrue(isDownloadIconDisplayed(autumnImgName, "showInfo"));
		Assert.assertTrue(isDownloadIconDisplayed(forestImgName, "showInfo"));
		Assert.assertTrue(isDownloadIconDisplayed(lakeImgName, "showInfo"));
		
	}	
	public boolean isDownloadIconDisplayed(String fileName, String actionName) {
		WebElement element = driver.findElement(By.xpath("//td[contains(text(), '" + fileName + "')]/following-sibling::td/a[contains(@class, '" + actionName + "')]"));
		return element.isDisplayed();
	}

	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String WindowID : allWindows) {
			if(!WindowID.equals(parentID)) {
				driver.switchTo().window(WindowID);
				break;
			}
		}
	}
	
	public void sleepInSeconds(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 