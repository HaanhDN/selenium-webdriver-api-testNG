 
package webdriver;

import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.List;
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


public class Topic_15_Wait_P6_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Not_found_Only_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println("TC_01 - START: " + getDateTimeSecond());
		
		
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='reg_form_box']")).isDisplayed());
		} catch (Exception e) {
			
		}
	 
		System.out.println("TC_01 - END: " + getDateTimeSecond());
	}
	
	@Test
	public void TC_02_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);
		
		System.out.println("TC_02 - START - Implicit: " + getDateTimeSecond());
		
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());
		} catch (Exception e) {	
		}
		System.out.println("TC_02 - END - Implicit: " + getDateTimeSecond());
	
		System.out.println("TC_02 - START - Explicit: " + getDateTimeSecond());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='login']")));
		} catch (Exception e) {	
		}
		System.out.println("TC_02 - END - Explicit: " + getDateTimeSecond());
	}
	
	@Test
	public void TC_03_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);
		
		System.out.println("TC_03 - START - Implicit: " + getDateTimeSecond());
		
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//button[@name='automation-test']")).isDisplayed());
		} catch (Exception e) {	
		}
		System.out.println("TC_03 - END - Implicit: " + getDateTimeSecond());
	
		System.out.println("TC_03 - START - Explicit: " + getDateTimeSecond());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='automation-test']")));
		} catch (Exception e) {	
		}
		System.out.println("TC_03 - END - Explicit: " + getDateTimeSecond());
	
	}
	@Test
	public void TC_04_Not_Found_Explicit_By() {
		explicitWait = new WebDriverWait(driver, 6);
	
		System.out.println("TC_04 - START - Explicit: " + getDateTimeSecond());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='automation-test']")));
		} catch (Exception e) {	
		}
		
		System.out.println("TC_04 - END - Explicit: " + getDateTimeSecond());
	}
	
	@Test
	public void TC_05_Not_Found_Explicit_WebElement() {
		explicitWait = new WebDriverWait(driver, 6);
		
		System.out.println("TC_05 - START - Explicit: " + getDateTimeSecond());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@name='automation-test']"))));
		} catch (Exception e) {	
		}
		
		System.out.println("TC_05 - END - Explicit: " + getDateTimeSecond());
	}
		
	public String getDateTimeSecond() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 
