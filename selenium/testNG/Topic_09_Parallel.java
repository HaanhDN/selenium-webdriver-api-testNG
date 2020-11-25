package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Topic_09_Parallel {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id = 'pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@Parameters({ "browser" })
	
	@BeforeClass
	public void beforeClass(@Optional("firefox") String browserName) {
		
		if(browserName.equals("firefox")) {
		driver = new FirefoxDriver();
		} else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectLocation + "/browserDrivers/chromedriver");
			driver = new ChromeDriver();
		} else {
			new RuntimeException("Your browser name is invalid!");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_LoginToSystem() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}