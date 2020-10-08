package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_Check_Selenium {
	//Khai bao bien dai dien cho Selenium WebDriver
	WebDriver driver;
	//Pre-condition
	@BeforeClass
	  public void beforeClass() {
		//Mo trinh duyet
		 driver = new FirefoxDriver();
		//Mo app can test len
		 driver.get("https://www.facebook.com/");
	  }
	
	
	@Test
  public void TC_01_Check_Title() {
	  String homePageTitle = driver.getTitle();
	  
	  Assert.assertEquals(homePageTitle, "Facebook - Đăng nhập hoặc đăng ký");
  }
  
  @Test
  public void TC_02_Check_URL() {
	  String homePageUrl = driver.getCurrentUrl();
	  
	  Assert.assertEquals(homePageUrl, "https://www.facebook.com/");
  }

  //Post-condition
  @AfterClass
  public void afterClass() {
	  //Dong trinh duyet
	  driver.quit();
  }

}
