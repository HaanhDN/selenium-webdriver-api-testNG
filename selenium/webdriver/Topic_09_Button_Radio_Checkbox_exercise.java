 
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Button_Radio_Checkbox_exercise {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	By loginButton = By.xpath("//button[@class='fhs-btn-login']");

 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		}
		else {
			System.out.println("Element is disabled" );
		return false;
		}
	}
	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	public boolean isElementSelected(WebElement element) {
		if(element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		}
		else {
			System.out.println("Element is not selected" );
		return false;
		}
	}
	
	public void sleepInSeconds(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void clickByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(isElementEnabled(loginButton));
		
		driver.findElement(By.id("login_username")).sendKeys("gmlogin123@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123123");
		
		Assert.assertTrue(isElementEnabled(loginButton));
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(isElementEnabled(loginButton));
		
		removeDisabledAttributeByJS(loginButton);
		sleepInSeconds(3);
		
		Assert.assertTrue(isElementEnabled(loginButton));
		
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		
	}
	
	@Test
	public void TC_02_Default_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		WebElement dualZoneCheckBox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	   
		Assert.assertFalse(isElementSelected(dualZoneCheckBox));
		
		dualZoneCheckBox.click();
		Assert.assertTrue(isElementSelected(dualZoneCheckBox));
		
		dualZoneCheckBox.click();
		Assert.assertFalse(isElementSelected(dualZoneCheckBox));
		
	    driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
	    
	    WebElement kw147Radio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
     	Assert.assertFalse(isElementSelected(kw147Radio));
     	kw147Radio.click();
	  
		Assert.assertTrue(isElementSelected(kw147Radio));
	}
	
	@Test
	public void TC_03_Custom_Checkbox_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		WebElement summerRadioInput = driver.findElement(By.xpath("//label/div[contains(text(),' Summer ')]/preceding-sibling::div/input"));
		clickByJS(summerRadioInput);
		Assert.assertTrue(isElementSelected(summerRadioInput));
		

	}
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 