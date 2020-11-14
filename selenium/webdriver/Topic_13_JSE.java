 
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_13_JSE {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	String email, userID, password, loginPageUrl;
	String name, dobInput, dobOutput, address, city, state, pin, phone;
	
	String firstName, lastName, emailAddress;
	
	By nameBy = By.name("name");
	By dobBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By phoneBy = By.name("telephoneno");
	By emailBy = By.name("emailid");
	By passwordBy = By.name("password");
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email = generateEmail();
		name = "Daisie Edmott";
		dobInput = "04/12/1980";
		dobOutput = "1980-04-12";
		address = "46930 Haas Parkway";
		city = "Washington";
		state = "District of Columbia";
		pin = "205992";
		phone = "2028046679";
		loginPageUrl = driver.getCurrentUrl();
		
		emailAddress = generateEmail();
		firstName = "Haanh";
		lastName = "Test";
	}
	
	@Test
	public void TC_01() {
		driver.get("https://www.myntra.com/men-jeans");
		
		String homePageTitle = (String) executeForBrowser(driver, "return document.title");
		System.out.println(homePageTitle);
		String homePageUrl = (String) executeForBrowser(driver, "return document.URL");
		System.out.println(homePageUrl);
	}
	
	@Test
	public void TC_02_jsE() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		
		//get page domain & verify domain
		String liveGuruDomain = (String) executeForBrowser(driver, "return document.domain");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		//get page URL & verify
		String homePageUrl = (String) executeForBrowser(driver, "return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/");
		
		//Open Mobile page
		clickToElementByJS(driver, "//a[text()='Mobile']");
		
		//Add Samsung to cart & verify message
		clickToElementByJS(driver, "//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSeconds(3);
		
		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));
				
		//Open customer service page
		clickToElementByJS(driver, "//a[text()='Customer Service']");
		String customerServiceTitle = (String) executeForBrowser(driver, "return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		//Scroll to element Newsletter text box
		scrollToElement(driver, "//input[@id='newsletter']");
		
		//Input valid mail to Newsletter text box
		sendkeyToElementByJS(driver, "//input[@id='newsletter']", generateEmail());
		
		//Click subcribe button & verify text
		clickToElementByJS(driver, "//button[@title='Subscribe']");
		
		Assert.assertTrue(getInnerText(driver).contains("Thank you for your subscription."));
	
		//Navigate to domain demoguru99.com/v4
		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		
		String bankHomePageURL = (String) executeForBrowser(driver, "return document.domain");
		Assert.assertEquals(bankHomePageURL, "demo.guru99.com");
		}
	
	@Test
	public void TC_03_Remove_Attribute() {
		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		
		//Register 
		highlightElement(driver, "//a[text()='here']");
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		highlightElement(driver, "//input[@name='emailid']");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		
		highlightElement(driver, "//input[@name='btnLogin']");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		//Login
		driver.get("http://demo.guru99.com/v4/");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		
		//Select "New Customer" & enter data
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameBy).sendKeys(name);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		
		//Chrome, Safari, Firefox latest has date time picker-> Remove date time picker attribute
		removeAttributeInDOM(driver, "//input[@name='dob']", "type" );
		sleepInSeconds(3);
		driver.findElement(dobBy).sendKeys(dobInput);
		
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(phoneBy).sendKeys(phone);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);
		
		//Click Submit
		driver.findElement(By.name("sub")).click();
		
		//Verify info
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dobOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	}	
	
	@Test
	public void TC_04_Create_Account_jsE() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		
		//Click on My account
		clickToElementByJS(driver, "//div[@class='footer']//a[text()='My Account']");
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//Click on Create Account
		clickToElementByJS(driver, "//a[@title='Create an Account']");
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		//Enter info
		sendkeyToElementByJS(driver, "//input[@id='firstname']", firstName);
		sendkeyToElementByJS(driver, "//input[@id='lastname']", lastName);
		sendkeyToElementByJS(driver, "//input[@id='email_address']", emailAddress);
		sendkeyToElementByJS(driver, "//input[@id='password']", "123123");
		sendkeyToElementByJS(driver, "//input[@id='confirmation']", "123123");
		
		
		//Click Register button
		clickToElementByJS(driver, "//button[@title='Register']");

		//Verify register success message
		Assert.assertTrue(getInnerText(driver).contains("Thank you for registering with Main Website Store."));
		
		//Logout
		clickToElementByJS(driver, "//a[@title = 'Log Out']");
		
		//Verify navigate to home page after logout
		Assert.assertTrue(driver.findElement(By.xpath("//img[@src='http://live.demoguru99.com/media/wysiwyg/test/logo.png']")).isDisplayed());
		  
	}
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSeconds(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}
	
	public void sleepInSeconds(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return "gmlogin123" + rand.nextInt(9999) + "@gmail.com";
	}
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 