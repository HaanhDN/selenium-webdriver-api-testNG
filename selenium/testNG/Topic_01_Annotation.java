package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotation {
  @Test
  public void TC_01() {
	  System.out.println("Run Testcase 01");
  }
  @Test
  public void TC_02() {
	  System.out.println("Run Testcase 02");
  }
  @Test
  public void TC_03() {
	  System.out.println("Run Testcase 03");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Run before method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Run After method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Run Before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Run After class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Run before test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Run after test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Run before suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Run after suite");
  }

}
