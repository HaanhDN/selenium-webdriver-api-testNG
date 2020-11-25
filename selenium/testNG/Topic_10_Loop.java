package testNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Topic_10_Loop {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test(invocationCount = 5)
	public void TC_01_Register() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.findElement(By.id("firstname")).sendKeys("Automation");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		driver.findElement(By.id("email_address")).sendKeys("autofc" + getRandomNumber() + "@live.com");
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.id("confirmation")).sendKeys("123123");
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	
	@Test(dataProvider = "email")
	public void TC_02_Register_DataProvider(String emailAdress, String password) throws InterruptedException{
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.findElement(By.id("firstname")).sendKeys("Automation");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		driver.findElement(By.id("email_address")).sendKeys(emailAdress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	
	@DataProvider(name="email")
	public Object[][] getEmailAddress() {
		return new Object[][] { 
			{"autofc01" + getRandomNumber() + "@live.com", "123123"}, 
			{"autofc02" + getRandomNumber() + "@live.com", "123123"},
			{"autofc03" + getRandomNumber() + "@live.com", "123123"}}; 
	}
	
public int getRandomNumber() {
	Random rand = new Random();
	return rand.nextInt(9999);
}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}