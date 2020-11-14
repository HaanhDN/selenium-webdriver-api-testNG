 
package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_Popup_Exercise {
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		sleepInSeconds(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
		driver.findElement(By.id("loginEmail")).sendKeys("gmlogin123@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("123123");
		
		
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
	}
	
	@Test
	public void TC_02_Fixed_Popup() {
		driver.get("https://bni.vn/");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='sgpb-popup-dialog-main-div']")).isDisplayed());
		
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		sleepInSeconds(2);
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id ='sgpb-popup-dialog-main-div']")).isDisplayed());
		
		driver.navigate().refresh();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='sgpb-popup-dialog-main-div']")).isDisplayed());
	}

	@Test
	public void TC_03_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		String keyword = "Selenium";
		sleepInSeconds(8);
		
		//if element is not displayed but still exist in DOM
		if(driver.findElement(By.xpath("//div[@id = 'mailch-bg']")).isDisplayed()) {
			System.out.println("If popup shows, close the popup");
			driver.findElement(By.xpath("//div[@id = 'close-mailch']")).click();
			sleepInSeconds(2);
	
		}
		System.out.println("If popup doesn't show, move to step 2");
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys(keyword + '\n');
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#primary .page-title")).getText().contains(keyword));
		
		List <WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement title : postTitles) {
			Assert.assertTrue(title.getText().contains(keyword));
		}
	}
	
	@Test
	public void TC_04_Random_Popup_Not_In_DOM() {
		driver.get("https://bizbooks.vn/");
		List<WebElement> randomPopup = driver.findElements(By.xpath("//div[@class='mepuzz-bn-modal']//div[@class='modal-info']"));
		
		if(randomPopup.size() > 0 && randomPopup.get(0).isDisplayed()) {
			System.out.println("If popup shows, close the popup");
			driver.findElement(By.xpath("//button[@class='mepuzz-btn-close']")).click();
			sleepInSeconds(2);	
		} 
		System.out.println("If popup doesn't show, move to step 2");
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