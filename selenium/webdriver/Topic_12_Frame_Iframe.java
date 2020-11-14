 
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_12_Frame_Iframe {
	WebDriver driver;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	@Test
	public void TC_01_IFrame() {
		driver.get("https://kyna.vn/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/child::iframe")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Kyna.vn']")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "169K likes");
		System.out.println(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText());
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='cs-live-chat']/child::iframe")));
		
		driver.findElement(By.xpath("//div[@class='meshim_widget_widgets_Favicon favicon']")).click();
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Chào bạn. Chúng tôi có thể giúp gì cho bạn?']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='editing field profile_field']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='meshim_widget_widgets_Form generated_form']")).isDisplayed());
		
		driver.switchTo().defaultContent();
	
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("java");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
	
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='menu-heading']/h1[contains(text(), \"'java'\")]")).isDisplayed());
		
	}
	
	@Test
	public void TC_02_Window_Tab_By_ID() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		String parentWindowID = driver.getWindowHandle();
		System.out.println(parentWindowID);
		
		driver.findElement(By.xpath("//a[text()= 'ELEARNING']")).click();
		
		switchToWindowByID(parentWindowID);
		
		Assert.assertEquals(driver.getTitle(), "Automation FC");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.vn/");
		}
		
		

	@Test
	public void TC_03_Window_Tab_By_Title() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()= 'ELEARNING']")).click();
		
		switchToWindowByTitle("Automation FC");
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(), "Automation FC");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.vn/");
		
		switchToWindowByTitle("[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fb-page fb_iframe_widget']//iframe")));
		sleepInSeconds(5);
		driver.findElement(By.xpath("//a[@title='Automation FC']")).click();
		
		switchToWindowByTitle("Automation FC | Facebook");
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(), "Automation FC - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://m.facebook.com/automationfc/");
		
		switchToWindowByTitle("[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='youtube-player']")));
		driver.findElement(By.xpath("//div[@id='player']//a[text()='[Online 10] - Topic 01 (Intro Course/ Outline/ Target/ Rule)']")).click();
		
		switchToWindowByTitle("[Online 10] - Topic 01 (Intro Course/ Outline/ Target/ Rule) - YouTube");
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(), "[Online 10] - Topic 01 (Intro Course/ Outline/ Target/ Rule) - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/watch?v=HrLvqGMaMXU&feature=emb_title");
		
		driver.findElement(By.xpath("//input[@id = 'search']")).sendKeys("Selenium Grid");
		
		closeAllWindowsWithoutParent(parentID);
	}
	@Test
	public void TC_04_Window_Tab_Exercise_1() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		switchToWindowByTitle("Google");
		sleepInSeconds(3);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
		
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text() = 'TIKI']")).click();
		
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		closeAllWindowsWithoutParent(parentID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test
	public void TC_05_Window_Tab_Exercise_2() {
		driver.get("https://kyna.vn/");
		
		String parentID = driver.getWindowHandle();
		
		//click icon facebook
		clickToElementByJS("//div[@class='hotline']/descendant::img[@alt='facebook']");
		
		//switch to tab facebook & verify
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		
		//switch back to parent
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		
		//click icon youtube
		clickToElementByJS("//div[@class='hotline']/descendant::img[@alt='youtube']");
		sleepInSeconds(3);
		
		//switch to youtube tab & verify
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		//switch back to parent
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		
		//click icon zalo
		clickToElementByJS("//div[@class='hotline']/descendant::img[@alt='zalo']");
		
		//switch to zalo tab & verify
		switchToWindowByTitle("Zalo Official Account");
		Assert.assertEquals(driver.getTitle(), "Zalo Official Account");
		
		//switch back to parent
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		
		//close all tabs except parent
		closeAllWindowsWithoutParent(parentID);
	
	}
	
	@Test
	public void TC_06_Window_Tab_Exercise_3() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		//click Mobile
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		//Add sony xperia to compare
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		
		//verify text
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
	
		
		//Add samsung galaxy to compare
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		
		//verify text
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		//click compare button
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		//switch to compare tab
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		//verify window title
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		// close tab & switch to Mobile window
		driver.close();
		switchToWindowByTitle("Mobile");
		
		//Click clear all link 
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		//Accept alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Are you sure you would like to remove all products from your comparison?");
		alert.accept();
		
		//Verify message
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}
	
	public void sleepInSeconds(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String windowID : allWindows) {
			driver.switchTo().window(windowID);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindows : allWindows) {
			if(!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
	}
	
	public void clickToElementByJS(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 