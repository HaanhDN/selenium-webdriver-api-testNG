package testNG;

import org.testng.annotations.Test;


public class Topic_05_Priority_Skip_Description {

	@Test(priority = 1, enabled=false)
	public void User_01_Create() {
		
	}
	
	@Test(priority = 2, description = "User - Edit user details after created success")
	public void User_02_Edit() {
		
	}
	
	@Test(priority = 3)
	public void User_03_View() {
		
	}
	
	@Test(priority = 4)
	public void User_04_Delete() {
	
	}
}