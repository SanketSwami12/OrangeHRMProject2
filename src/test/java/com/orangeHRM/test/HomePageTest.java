package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.pages.HomePage;
import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.utilities.DataProviders;
import com.orangeHRM.utilities.ExtentManager;
import com.orangeHRM.utilities.RetryAnalyzer;

public class HomePageTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	
	@BeforeMethod
	public void setupPages()
	{
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	@Test(dataProvider="validLoginData", dataProviderClass=DataProviders.class)
	//@Test (retryAnalyzer = RetryAnalyzer.class)
	public void VerifyOrangeHRMLogo(String username, String password)
	{
		//ExtentManager.startTest("Home Page - Verify Logo Test"); --> This has been implemented in TestListner
		ExtentManager.logStep("Navigating to Login page entering username and password");

		loginPage.login(username, password);
		ExtentManager.logStep("Verifying Logo is visible or not");

		Assert.assertTrue(homePage.VerifyOrangeHRMLogo(), "Logo is not visible");
		ExtentManager.logStep("Validation Successfull");
		ExtentManager.logStep("Logged out Successfully");

	}
	
	@Test(dataProvider="validLoginData", dataProviderClass=DataProviders.class)
	public void VerifyLeftNavigationMenu(String username, String password)
	{
		ExtentManager.logStep("Navigating to Login page entering username and password");
		
		loginPage.login(username, password);
		
		ExtentManager.logStep("Verify Left Navigation Menu Is Displayed Properly");
		
		Assert.assertTrue(homePage.isPimTabVisible(), "PIM menu is not visible");
		Assert.assertTrue(homePage.isLeaveTabVisible(), "Leave menu is not visible");
		Assert.assertTrue(homePage.isTimeTabVisible(), "Time menu is not visible");
		Assert.assertTrue(homePage.isRecruitmentTabVisible(), "Recruitment menu is not visible");
		Assert.assertTrue(homePage.isMyInfoTabVisisble(), "My Info menu is not visible");
		Assert.assertTrue(homePage.isPerformanceTabVisible(), "Performance menu is not visible");
		Assert.assertTrue(homePage.isDashboardTabVisible(),"Dashboard menu is not visible");
		Assert.assertTrue(homePage.isDirectoryTabVisible(),"Directory menu is not visible");
		Assert.assertTrue(homePage.isMaintenanceTabVisible(), "Maintenance menu is not visible");
		Assert.assertTrue(homePage.isClaimTabVisible(),"Claim menu is not visible");
		Assert.assertTrue(homePage.isBuzzTabVisible(), "Buzz menu is not visible");
		
		
		ExtentManager.logStep("Left Navigation Menu validation successful");
		homePage.logout();
		
	}
	
	@Test(dataProvider="validLoginData", dataProviderClass=DataProviders.class) //TC-04
	public void VerifyQuickLaunchOptions(String username, String password)
	{
	    ExtentManager.logStep("Navigating to Login page entering username and password");

	    loginPage.login(username, password);

	    ExtentManager.logStep("Verifying Quick Launch options");

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

	    ExtentManager.logStep("All Quick Launch options are displayed successfully");
	    homePage.logout();
	}

	//TC05
	@Test(dataProvider="validLoginData", dataProviderClass = DataProviders.class)
	public void VerifyEmployeeLeaveConfiguration(String username, String password)
	{
		ExtentManager.logStep("Navigating to Login Page Entering Username and Password");
		loginPage.login(username, password);
		ExtentManager.logStep("Verify Employee Leave Configuration");
		homePage.clickEmployeeLeaveSettings();
		homePage.isEmplyoeesLeavePopUpDisplayed();
		homePage.enableShowLeavePeriodToggle();
		homePage.emplyoeesOnleaveSaveBtn();
		homePage.logout();
		
	}
}
