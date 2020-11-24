 
package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Topic_15_Wait_P5_Explicit_wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty("user.dir");
	String projectFolder = System.getProperty("user.dir");
	
	By startButton = By.xpath("//div[@id='start']/button");
	By loadingIcon = By.xpath("//div[@id='loading']");
	By helloText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
	
	String autumnImgName = "Upload1.jpg";
	String forestImgName = "Upload2.jpg";
	String lakeImgName = "Upload3.jpeg";
	
	String autumnImgPath = projectFolder + "//uploadFiles//" + autumnImgName;
	String forestImgPath = projectFolder + "//uploadFiles//" + forestImgName;
	String lakeImgPath = projectFolder + "//uploadFiles//" + lakeImgName;
	
	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Loading_Icon_Invisible_10s() {
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	
		driver.findElement(startButton).click();
		
		//Wait for loading icon to disappear in 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
		
	}
	
	@Test
	public void TC_02_Loading_Icon_Invisible_4s() {
		explicitWait = new WebDriverWait(driver, 4);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		
		//Wait for loading icon to disappear in 4s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
		
		}
	
	@Test
	public void TC_03_Hello_World_visible_10s() {
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		
		//Wait for Hello World text to appear in 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
		
		}

	@Test
	public void TC_04_Hello_World_visible_4s() {
		explicitWait = new WebDriverWait(driver, 4);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
		
		driver.findElement(startButton).click();
		
		//Wait for Hello World text to appear in 4s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
		
	}
	
	@Test
	public void TC_05_Select_Date() {
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		WebElement selectedDate = driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_Label1"));
		
		Assert.assertEquals(selectedDate.getText(), "No Selected Dates to display.");
		
		//wait until date 24 is clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='24']/parent::td"))).click();
		
		//wait until ajax loading disappears
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style ='display:none;')]/div[@class='raDiv']")));
		
		//wait until date 24 is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = '24']/parent::td[@class='rcSelected']")));
		
		selectedDate = driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_Label1"));
		
		Assert.assertEquals(selectedDate.getText(), "Tuesday, November 24, 2020");
	}
	@Test
	public void TC_06_Upload_Gofile() {
		driver.get("https://gofile.io/uploadFiles");
		
		String parentID = driver.getWindowHandle();
		
		explicitWait = new WebDriverWait(driver, 30);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='dropZoneBtnSelect']")));
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(autumnImgPath + "\n" + forestImgPath + "\n" + lakeImgPath);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text() = '" + autumnImgName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text() = '" + forestImgName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text() = '" + lakeImgName + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@id='uploadFiles-btnUpload']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Your files have been successfully uploaded']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='uploadFiles-uploadRowResult-downloadLink']"))).click();
		
		switchToWindowByID(parentID);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='datatable']")));
		
		Assert.assertTrue(isDownloadIconDisplayed(autumnImgName, "download"));
		Assert.assertTrue(isDownloadIconDisplayed(forestImgName, "download"));
		Assert.assertTrue(isDownloadIconDisplayed(lakeImgName, "download"));
		
		Assert.assertTrue(isDownloadIconDisplayed(autumnImgName, "showInfo"));
		Assert.assertTrue(isDownloadIconDisplayed(forestImgName, "showInfo"));
		Assert.assertTrue(isDownloadIconDisplayed(lakeImgName, "showInfo"));
		
	}	
//	
	@Test
	public void TC_07_Upload_Filebin() {
		driver.get("https://filebin.net/");
		
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(autumnImgPath + "\n" + forestImgPath + "\n" + lakeImgPath);
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='progress']"))));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = '" + autumnImgName + "']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = '" + forestImgName + "']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = '" + lakeImgName + "']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + autumnImgName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + forestImgName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + lakeImgName + "']")).isDisplayed());
		
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

	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 