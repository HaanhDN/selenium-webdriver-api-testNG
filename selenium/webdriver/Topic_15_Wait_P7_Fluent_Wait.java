 
package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.FluentWait;


public class Topic_15_Wait_P7_Fluent_Wait {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
	}
	
	@Test
	public void TC_01_Wait_And_Click() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		waitAndClickElement(By.xpath("//div[@id='start']/button"));
		
		waitAndVerifyText(By.xpath("//div[@id='finish']/h4"), "Hello World!");
		
	}
	
	@Test
	public void TC_02_Wait_And_Verify_Text() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		waitAndVerifyTextEndsWith(By.xpath("//div[@id='javascript_countdown_time']"), "00");
		
	}

	public void waitAndClickElement(By xpath) {
		
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		//Total waiting time = 15s
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
			//check once every second
			.pollingEvery(1, TimeUnit.SECONDS)
			//ignore  if get exception No Such element
			.ignoring(NoSuchElementException.class);
		
		WebElement element = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(xpath);
			}
		});
		element.click();
	}
	
	public void waitAndVerifyText(By xpath, String expectedText) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		//Total waiting time = 15s
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
			//check once every second
			.pollingEvery(1, TimeUnit.SECONDS)
			//ignore  if get exception No Such element
			.ignoring(NoSuchElementException.class);
		fluentDriver.until(new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				return driver.findElement(xpath).getText().equals(expectedText);
	}
		});
	}
	
	public void waitAndVerifyTextEndsWith(By xpath, String expectedText) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		//Total waiting time = 15s
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
			//check once every second
			.pollingEvery(100, TimeUnit.MILLISECONDS)
			//ignore  if get exception No Such element
			.ignoring(NoSuchElementException.class);
		fluentDriver.until(new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				String actualText = driver.findElement(xpath).getText();
				System.out.println("Text = " + actualText);
				return actualText.endsWith(expectedText);
	}
		});
	}
	

	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 