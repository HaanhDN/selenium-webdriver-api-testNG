 
package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select;
	String gender, firstName, lastName, date, month, year, email, companyName,password;
	
	By genderFemaleBy = By.id("gender-female");
	By firstNameBy = By.id("FirstName");
	By lastNameBy = By.id("LastName");
	By birthDayBy = By.xpath("//select[@name ='DateOfBirthDay']");
	By birthMonthBy  = By.xpath("//select[@name ='DateOfBirthMonth']");
	By birthYearBy  = By.xpath("//select[@name ='DateOfBirthYear']");
	By companyBy = By.id("Company");
	By emailBy = By.id("Email");
	By passwordBy = By.id("Password");
		

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		gender = "Female";
		firstName = "Jolyn";
		lastName = "Fancutt";
		date = "1";
		month = "May";
		year = "1980";
		email = generateEmail();
		companyName = "Youopia";
		password = "CEHvfZfZh";
		
	}
	public String generateEmail() {
		Random rand = new Random();
		return "jfancutt8" + rand.nextInt(9999) + "@auda.org.au";
		
	}
	
	@Test
	public void TC_01_Register_and_Verify() {
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.className("ico-register")).click();
		
		driver.findElement(genderFemaleBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
	
		select = new Select(driver.findElement(birthDayBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(birthMonthBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(birthYearBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(companyBy).sendKeys(companyName);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
	
		driver.findElement(By.className("ico-account")).click();
		
		Assert.assertTrue(driver.findElement(genderFemaleBy).isSelected());
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(birthDayBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(birthMonthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(birthYearBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), companyName);
		
	}
	
	@Test
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		List<String> itemText = new ArrayList<String>();
		itemText.add("Manual");
		itemText.add("Mobile");
		itemText.add("Desktop");
		itemText.add("Perfomance");
		
		select = new Select(driver.findElement(By.name("user_job2")));
		
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		select.selectByVisibleText("Perfomance");
		
		List<WebElement> itemSelected = select.getAllSelectedOptions();
		List<String> itemSelectedText = new ArrayList<String>();
		
		Assert.assertEquals(itemSelected.size(), 4);
		
		for (WebElement item : itemSelected) {
			itemSelectedText.add(item.getText());
			System.out.println(item.getText());
		}
		Assert.assertEquals(itemText, itemSelectedText);
		
	}

	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 