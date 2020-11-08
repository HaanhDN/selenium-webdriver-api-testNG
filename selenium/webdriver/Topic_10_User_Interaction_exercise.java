 
package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_User_Interaction_exercise {
	WebDriver driver;
	Actions action;
 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	@Test
	public void TC_01_Hover_Tooltip() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		
		String hoverText = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText();
		System.out.println(hoverText);
		Assert.assertEquals(hoverText, "We ask for your age only for statistical purposes.");
	}
	
	@Test
	public void TC_02_Hover_Element() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
		
		driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text() = 'Kids Home Bath']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text() = 'Kids Home Bath']")).isDisplayed());

	}

	@Test
	public void TC_03_Click_and_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		System.out.println("List number = " + listNumbers.size());
		
		Assert.assertEquals(listNumbers.size(), 12);
		
		action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(3)).release().perform();
		
		listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
		
		System.out.println("List number = " + listNumbers.size());
		
		Assert.assertEquals(listNumbers.size(), 4);
	}
	
	@Test
	public void TC_04_Click_and_Hold_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		System.out.println("List number = " + listNumbers.size());
		
		Assert.assertEquals(listNumbers.size(), 12);
		
		action.keyDown(Keys.COMMAND).perform();
		action.click(listNumbers.get(1)).perform();
		action.click(listNumbers.get(4)).perform();
		action.click(listNumbers.get(7)).perform();
		action.click(listNumbers.get(8)).perform();
		action.click(listNumbers.get(10)).perform();
		action.keyUp(Keys.COMMAND).perform();

		listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
		System.out.println("List number = " + listNumbers.size());
		Assert.assertEquals(listNumbers.size(), 5);
	}
	
	@Test
	public void TC_05_Double_click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");	
	}
	
	@Test
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.cssSelector(".context-menu-one"))).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		driver.findElement(By.cssSelector(".context-menu-icon-quit")).click();
		
		driver.switchTo().alert().accept();
		
		Assert.assertFalse(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());
		
	}
	
	@Test
	public void TC_07_Drag_and_Drop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		WebElement sourceCircle = driver.findElement(By.cssSelector("#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("#droptarget"));
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		
		Assert.assertEquals(targetCircle.getCssValue("background-color"), "rgba(3, 169, 244, 1)");
		
	}
	
	@AfterClass
	public void afterClass() {
	  driver.quit();
	  }

} 