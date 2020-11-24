 
package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Topic_15_Wait_P2_Find_Element {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	//implicitWait: wait time to find element
	//Find element: findElement/ findElements
	@Test
	public void TC_01_Find_Element() {
		//single
			//WebElement element = driver.findElement(By.xpath(""));
		
		//When find element
		//Case 1: only 1 matching node
			//1.1 - when start finding, element show immediately -> move on to next step right away, no waiting
			//1.2 - when start finding, element doesn't show immediately, keep finding every 0.5s. 
				//If element found, move on to next step. 
				//If element not found, wait until timeout. If still not found, throw exception & fail test case
		
		//Case 2: more than 1 matching node
			//If found >1 elements -> interact with first element
		//Case 3: no matching node
			//Find element - polling 0.5s. If cannot find element -> wait until time out. If still not found, throw exception & fail test case
		
	}
	
	@Test
	public void TC_02_Find_Elements() {
		List<WebElement> textboxes;
		//When find element
				//Case 1: only 1 matching node
					//don't need to wait for timeout
					//Return a list containing that element
		textboxes = driver.findElements(By.id("email"));
		System.out.println("Size :" + textboxes.size());
		
				//Case 2: more than 1 matching node
					//don't need to wait for timeout
					//Return a list containing that element
		textboxes = driver.findElements(By.xpath("//div[@class='input-box']/input"));
		System.out.println("Size :" + textboxes.size());
				//Case 3: no matching node
					//wait for timeout
					// find element every 0.5s
					//if cannot find element at timeout, not fail test case -> return an empty list & move on to next step
		textboxes = driver.findElements(By.id("not_found"));
		System.out.println("Size :" + textboxes.size());
		}
	

		
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 