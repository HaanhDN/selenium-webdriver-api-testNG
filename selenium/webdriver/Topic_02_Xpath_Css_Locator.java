package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator {
	//Selenium Locator(Xpath/ Css)

	WebDriver driver;
	@Test
	public void TC_01() {
		//1-Open Firefox
		driver = new FirefoxDriver();
		
		//2-Go to Facebook
		driver.get("https://www.facebook.com/");
		
		//3-Enter Email textbox
		//Action: enter, select, hover, dragdrop, get text...
		//find element - singular, find elements - plural
		
		//id
		driver.findElement(By.id("email")).sendKeys("haanh1312.ftu@gmail.com");
		
	    //class
		driver.findElement(By.className("_featuredLogin__formContainer")).isDisplayed();
		
		//Name
		driver.findElement(By.name("pass")).sendKeys("123456");
		
		//Tagname
		int linkNumber = driver.findElements(By.tagName("a")).size();
		System.out.println("Link number = " + linkNumber);
		
		//Link text (link - a)
		driver.findElement(By.linkText("English (UK)")).click();
		
		//Partial link text (a)
		driver.findElement(By.partialLinkText("Việt")).click();
		
		//Css
		driver.findElement(By.cssSelector("#email")).sendKeys("haanh1312.ftu@gmail.com");
		driver.findElement(By.cssSelector("input[id='email']")).clear();
		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("automation@gmail.com");
		
		//Xpath
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");

		 //Xpath text
		driver.findElement(By.xpath("//a[text()='English (UK)']")).click();
	}
	
//    public void TC_02() {
//		
//	}

}
