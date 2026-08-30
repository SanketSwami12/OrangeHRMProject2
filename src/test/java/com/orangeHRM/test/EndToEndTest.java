package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.pages.HomePage;
import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.utilities.DataProviders;
import com.orangeHRM.utilities.ExtentManager;

public class EndToEndTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages()
	{
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		
	}
	
	
	@Test (dataProvider = "validLoginData", dataProviderClass =DataProviders.class)
	public void VerifyLoginAndHomePageEndToEnd(String username, String password)
	{
		ExtentManager.logStep("Navigating to Login page entering username and password");
		loginPage.login(username, password);
		
		//      TC_HP_001 //

		ExtentManager.logStep("Verifying Dashboard/Home Page");
		homePage.isDashboardTabVisible();
		Assert.assertTrue(homePage.isDashboardTabVisible(), "Dashboard is not dislpayed after successful login");
		ExtentManager.logStep("Dashboard Validation Successful");
		
	    // TC_HP_002
		ExtentManager.logStep("OrangeHRM Logo is displayed");
		homePage.VerifyOrangeHRMLogo();
		Assert.assertTrue(homePage.VerifyOrangeHRMLogo(),"OrangeHRM Logo is not displayed");
		ExtentManager.logStep("OrangeHRM Logo Validation Successful");
		
		// TC_HP_003 - Verify Left Navigation Menu

		ExtentManager.logStep("Verifying Left Navigation Menu");

		Assert.assertTrue(homePage.isAdminTabVisible(),
		        "Admin tab is not visible");

		Assert.assertTrue(homePage.isPimTabVisible(),
		        "PIM tab is not visible");

		Assert.assertTrue(homePage.isLeaveTabVisible(),
		        "Leave tab is not visible");

		Assert.assertTrue(homePage.isTimeTabVisible(),
		        "Time tab is not visible");

		Assert.assertTrue(homePage.isRecruitmentTabVisible(),
		        "Recruitment tab is not visible");

		Assert.assertTrue(homePage.isMyInfoTabVisisble(),
		        "My Info tab is not visible");

		Assert.assertTrue(homePage.isPerformanceTabVisible(),
		        "Performance tab is not visible");

		Assert.assertTrue(homePage.isDashboardTabVisible(),
		        "Dashboard tab is not visible");

		Assert.assertTrue(homePage.isDirectoryTabVisible(),
		        "Directory tab is not visible");

		Assert.assertTrue(homePage.isMaintenanceTabVisible(),
		        "Maintenance tab is not visible");

		Assert.assertTrue(homePage.isClaimTabVisible(),
		        "Claim tab is not visible");

		Assert.assertTrue(homePage.isBuzzTabVisible(),
		        "Buzz tab is not visible");

		ExtentManager.logStep("Left Navigation Menu validation successful");
		
		// TC_HP_004 - Verify all Quick Launch options

		ExtentManager.logStep("Verifying all Quick Launch options");

		Assert.assertTrue(homePage.isAssignLeaveTabVisible(),
		        "Assign Leave option is not visible");

		Assert.assertTrue(homePage.isLeaveListTabVisible(),
		        "Leave List option is not visible");

		Assert.assertTrue(homePage.isTimeSheetsTabVisible(),
		        "Timesheets option is not visible");

		Assert.assertTrue(homePage.isApplyLeaveTabVisible(),
		        "Apply Leave option is not visible");

		Assert.assertTrue(homePage.isMyLeaveTabVisible(),
		        "My Leave option is not visible");

		Assert.assertTrue(homePage.isMyTimeSheetTabVisible(),
		        "My Timesheet option is not visible");

		ExtentManager.logStep("All Quick Launch options validation successful");
		
		// TC_HP_005 - Verify Employee Leave Configuration

		ExtentManager.logStep("Clicking Employee Leave Today Settings");

		homePage.clickEmployeeLeaveSettings();

		ExtentManager.logStep("Verifying Employee Leave Configuration popup is displayed");

		Assert.assertTrue(homePage.isEmplyoeesLeavePopUpDisplayed(),
		        "Employee Leave Configuration popup is not displayed");

		ExtentManager.logStep("Enabling Show Leave Period toggle");

		homePage.enableShowLeavePeriodToggle();

		ExtentManager.logStep("Clicking Save button");

		homePage.emplyoeesOnleaveSaveBtn();

		ExtentManager.logStep("Employee Leave Configuration completed successfully");
		
		// Logout

		ExtentManager.logStep("Logging out from the application");

		homePage.logout();

		ExtentManager.logStep("Logged out successfully");
																				
	}
	
	

}
