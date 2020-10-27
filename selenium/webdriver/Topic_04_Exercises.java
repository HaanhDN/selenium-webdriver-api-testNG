 
package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Exercises {
	WebDriver driver;
	By myAccountLinkBy = By.xpath("//div[@class='footer']//a[text()='My Account']");
	By createNewAccBy = By.xpath("//a[@title='Create an Account']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}
	
	
	@Test
	public void TC_01_Title() {
		driver.findElement(myAccountLinkBy).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(createNewAccBy).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_02_Url() {
		driver.findElement(myAccountLinkBy).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(createNewAccBy).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");	
		}
	
	@Test
	public void TC_03_Navigation() {
		driver.findElement(myAccountLinkBy).click();
		driver.findElement(createNewAccBy).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");	
		}
	
	@Test
	public void TC_04_Page_Source() {
		driver.findElement(myAccountLinkBy).click();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		driver.findElement(createNewAccBy).click();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
		}
	

	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 