 
package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Accept_Confirm_Prompt_Alert_exercise {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	By resultText = By.xpath("//p[@id='result']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
	}
	
	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
	}
	
	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String fullName = "Automation FC";
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys(fullName);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + fullName );
		
	}

	@Test
	public void TC_04_Authentication_Alert_By_Pass_Url() {
		//Truyen user name + password vao url: https://username:password@domain
//		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		driver.get("http://the-internet.herokuapp.com/");
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		System.out.println(basicAuthenLink);
		
		inputNameandPasswordToHref(basicAuthenLink, "admin", "admin");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public void inputNameandPasswordToHref(String href, String name, String password) {
		String[] hrefSplit = href.split("//");
		href = hrefSplit[0] + "//" + name + ":" + password + "@" + hrefSplit[1];
		driver.get(href);
	}
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 