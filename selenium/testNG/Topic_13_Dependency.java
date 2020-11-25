package testNG;

import org.testng.annotations.Test;
import org.testng.Assert;


public class Topic_13_Dependency {
	String fullName = "Automation Testing";
  @Test
  public void TC_01_Create_User() {
	Assert.assertNotNull(fullName);
	
	 
	
  }
  
  @Test(dependsOnMethods = "TC_01_Create_User")
  public void TC_02_View_User() {
	 
	
	 
	
  }
  
  @Test(dependsOnMethods = "TC_01_Create_User")
  public void TC_03_Edit_User() {
	  Assert.assertNull(fullName);
	 
  }
  
  @Test(dependsOnMethods = "TC_01_Create_User")
  public void TC_04_Move_User() {
	 
	 
	  
  }
  
  @Test(dependsOnMethods = {"TC_01_Create_User", "TC_03_Edit_User" })
  public void TC_05_Edit_User() {
	 
	 
	  
  }
}
