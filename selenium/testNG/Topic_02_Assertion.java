package testNG;

import org.testng.annotations.Test;
import org.testng.Assert;


public class Topic_02_Assertion {
  @Test
  public void TC_01() {
	  String fullName = "Automation Testing";
	  
	  String address = null;
	  
	  //Verify condition is True
	  Assert.assertTrue(fullName.contains("Testing"));
	 
	  //Verify condition is false
	  Assert.assertFalse(fullName.contains("Manual"));
	  
	  //Verify condition is are equal
	  Assert.assertEquals(fullName, "Automation Testing");
	  
	  Assert.assertNull(address);
  }
}
