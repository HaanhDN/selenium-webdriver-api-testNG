 
package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_Exercises {
	WebDriver driver;
	By emailTextBoxBy = By.id("email");
	By educationTextAreaBy = By.id("edu");
	By ageUnder18Radio = By.id("under_18");
	By jobRole01 = By.id("job1");
	By slider01 = By.id("slider-1");
	By javaCheckboxBy = By.id("java");
	
	By passwordTextBoxBy = By.id("password");
	By disabledRadioBy = By.id("radio-disabled");
	By jobRole03 = By.id("job3");
	By slider02 = By.id("slider-2");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Element_Displayed() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Email text box
		if(driver.findElement(By.id("mail")).isDisplayed()) {
			System.out.println("Element is displayed");
			driver.findElement(By.id("mail")).sendKeys("Automation Testing");
		}
		else {
			System.out.println("Element is not displayed");
		}
		
		Thread.sleep(3000);
		
		//Education text area
		if(driver.findElement(By.id("edu")).isDisplayed()) {
			System.out.println("Element is displayed");
			driver.findElement(By.id("edu")).sendKeys("Automation Testing");
		}
		else {
			System.out.println("Element is not displayed");
		}
			
		Thread.sleep(3000);
			
		//Age (under 18) radio button
		if(driver.findElement(By.id("under_18")).isDisplayed()) {
				System.out.println("Element is displayed");
				driver.findElement(By.id("under_18")).click();
		}
		else {
				System.out.println("Element is not displayed");
		}
		
		Thread.sleep(3000);
		
	}
		
	@Test
	public void TC_02_Element_Displayed() {
		driver.navigate().refresh();
		
		if(isElementDisplayed(emailTextBoxBy)) {
			driver.findElement(emailTextBoxBy).sendKeys("Automation Testing");
		}
		
		if(isElementDisplayed(educationTextAreaBy)) {
			driver.findElement(educationTextAreaBy).sendKeys("Automation Testing");
		}
		
		if(isElementDisplayed(ageUnder18Radio)) {
			driver.findElement(ageUnder18Radio).click();
		}
	}
	
	@Test
	public void TC_03_Element_Enabled() {
	
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		
		Assert.assertTrue(isElementEnabled(emailTextBoxBy));
		Assert.assertTrue(isElementEnabled(educationTextAreaBy));
		Assert.assertTrue(isElementEnabled(ageUnder18Radio));
		Assert.assertTrue(isElementEnabled(jobRole01));
		Assert.assertTrue(isElementEnabled(slider01));
		
		Assert.assertFalse(isElementEnabled(passwordTextBoxBy));
		Assert.assertFalse(isElementEnabled(disabledRadioBy));
		Assert.assertFalse(isElementEnabled(jobRole03));
		Assert.assertFalse(isElementEnabled(slider02));
		
		}
	
	//checkbox/Radio
	@Test
	public void TC_04_Element_Selected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		
		Assert.assertFalse(isElementSelected(ageUnder18Radio));
		Assert.assertFalse(isElementSelected(javaCheckboxBy));
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaCheckboxBy).click();
		
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertTrue(isElementSelected(javaCheckboxBy));
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaCheckboxBy).click();
		
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertFalse(isElementSelected(javaCheckboxBy));
		
		}
	
	@Test
	public void TC_05_Register_Mailchimp() throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");
		WebElement password = driver.findElement(By.xpath("//input[@id='new_password']"));
		
		driver.findElement(By.id("email")).sendKeys("gmlogin123@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Haanh");
		
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		
		//Lower Case
		password.sendKeys("auto");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		
		//Special char
		password.clear();
		password.sendKeys("auto!@$");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		
		//Upper Case
		password.clear();
		password.sendKeys("Auto!@$");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		
		//8 characters minimum
		password.clear();
		password.sendKeys("Automation!@$");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		
		//1 number
		password.clear();
		password.sendKeys("Automation123");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		
		//Full
		password.clear();
		password.sendKeys("Automation!@#123");
		Thread.sleep(1000);
		Assert.assertTrue(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		
		
		WebElement checkbox = driver.findElement(By.xpath("//input[@id='marketing_newsletter']"));
		checkbox.click();
		Assert.assertTrue(checkbox.isSelected());
	}
	//Common functions
	//All element: checkbox/radio/textbox/link/text...
		public boolean isElementDisplayed(By by) {
			if(driver.findElement(by).isDisplayed()) {
				System.out.println("Element is displayed");
				return true;
			}
			else {
				System.out.println("Element is not displayed" );
			return false;
			}
		}
			
		//Checkbox/textbox/radio/button/image...
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

		public boolean isElementSelected(By by) {
			if(driver.findElement(by).isSelected()) {
				System.out.println("Element is selected");
				return true;
			}
			else {
				System.out.println("Element is not selected" );
			return false;
			}
		}
		
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 