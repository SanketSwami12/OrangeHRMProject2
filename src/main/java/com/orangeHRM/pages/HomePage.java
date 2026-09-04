//               Test Cases (For test cases click on + icon)
//TC01 — Verify Dashboard is displayed after successful login
//
//Steps:
//
//Login with valid credentials.
//Verify the Dashboard/Home Page is displayed.
//
//Expected: Dashboard should be displayed successfully. ✅

// TC02 — Verify Dashboard heading

//Steps:
//
//Login with valid credentials.
//Verify the "Dashboard" heading.
//
//Expected: Dashboard heading should be visible. ✅

//TC03 — Verify left-side navigation menu

//Steps:
//
//Login with valid credentials.
//Verify the left navigation menu items are displayed.
//
//Expected: Required menu items should be visible, such as:
//
//Admin
//PIM
//Leave
//Time
//Recruitment
//My Info
//Performance
//Dashboard
//Directory
//Maintenance
//Claim
//Buzz

// TC04 - Verify all Quick Launch options are displayed on the Dashboard.
// Assign Leave
//Leave List
//Timesheets
//Apply Leave
//My Leave
//My Timesheet

package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM.actiondriver.ActionDriver;
import com.orangeHRM.base.BaseClass;

public class HomePage {
	
	private ActionDriver actionDriver;

	private By AdminTab = By.xpath("//span[text()=\"Admin\"]");
	private By PimTab = By.xpath("//*[text()='PIM']");
	private By LeaveTab = By.xpath("//*[text()='Leave']");
	private By TimeTab = By.xpath("//*[text()='Time']");
	private By RecruitmentTab = By.xpath("//*[text()='Recruitment']");
	private By MyInfoTab = By.xpath("//*[text()='My Info']");
	private By PerformanceTab = By.xpath("//*[text()='Performance']");
	private By DashboardTab = By.xpath("(//*[text()='Dashboard'])[1]");
	private By DirectoryTab = By.xpath("//*[text()='Directory']");
	private By MaintenanceTab = By.xpath("//*[text()='Maintenance']");
	private By ClaimTab = By.xpath("//*[text()='Claim']");
	private By BuzzTab = By.xpath("//*[text()='Buzz']");

	private By AssignLeaveTab = By.xpath("//p[text()='Assign Leave']");
	private By LeaveListTab = By.xpath("//p[text()='Leave List']");
	private By TimeSheetsTab = By.xpath("//p[text()='Timesheets']");
	private By ApplyLeaveTab = By.xpath("//p[text()='Apply Leave']");
	private By MyLeaveTab = By.xpath("//p[text()='My Leave']");
	private By MyTimeSheetTab = By.xpath("//p[text()='My Timesheet']");
	
	//For TC05
	private By EmployeesOnLeaveToday_Setting_Btn = By.xpath("//i[@class=\"oxd-icon bi-gear-fill orangehrm-leave-card-icon\"]");
	private By EmployeesOnLeave_PopUp = By.xpath("//*[@role=\"document\"]");
	private By EmplyoeesOnLeave_CheckBox = By.xpath("//*[@class=\"oxd-switch-input oxd-switch-input--active --label-right\"]");
	private By EmplyoeesOnLeave_Save_Btn = By.xpath("//*[text()=' Save ']");
	//
	private By UserIdBtn = By.xpath("//*[@class=\"oxd-userdropdown-tab\"]");
	private By LogoutBtn = By.xpath("//*[text()=\"Logout\"]");
	private By OrangeHRMLogo = By.xpath("//*[@alt=\"client brand banner\"]");

	public HomePage(WebDriver driver)
	{
		//this.actionDriver = new ActionDriver(driver);
		this.actionDriver = BaseClass.getActionDriver();
	}
	
	
	//Method to verify if admin tab is visible
	public boolean isAdminTabVisible()
	{
	return	actionDriver.isDisplayed(AdminTab);
	}
	
	//Method to verify if Pimtab is visible
	public boolean isPimTabVisible()
	{
		return actionDriver.isDisplayed(PimTab);
	}
	
	public boolean isLeaveTabVisible()
	{
		return actionDriver.isDisplayed(LeaveTab);
	}
	
	public boolean isTimeTabVisible()
	{
		return actionDriver.isDisplayed(TimeTab);
	}
	
	public boolean isRecruitmentTabVisible()
	{
		return actionDriver.isDisplayed(RecruitmentTab);
	}
	
	public boolean isMyInfoTabVisisble()
	{
		return actionDriver.isDisplayed(MyInfoTab);
	}
	
	public boolean isPerformanceTabVisible()
	{
		return actionDriver.isDisplayed(PerformanceTab);
	}
	
	public boolean isDashboardTabVisible()
	{
		return actionDriver.isDisplayed(DashboardTab);
	}
	
	public boolean isDirectoryTabVisible()
	{
		return actionDriver.isDisplayed(DirectoryTab); 
	}
	
	public boolean isMaintenanceTabVisible()
	{
		return actionDriver.isDisplayed(MaintenanceTab); 
	}
	
	public boolean isClaimTabVisible()
	{
		return actionDriver.isDisplayed(ClaimTab); 
	}
	
	public boolean isBuzzTabVisible()
	{
		return actionDriver.isDisplayed(BuzzTab); 
	}
	
	public boolean isAssignLeaveTabVisible()
	{
	    return actionDriver.isDisplayed(AssignLeaveTab);
	}

	public boolean isLeaveListTabVisible()
	{
	    return actionDriver.isDisplayed(LeaveListTab);
	}

	public boolean isTimeSheetsTabVisible()
	{
	    return actionDriver.isDisplayed(TimeSheetsTab);
	}

	public boolean isApplyLeaveTabVisible()
	{
	    return actionDriver.isDisplayed(ApplyLeaveTab);
	}

	public boolean isMyLeaveTabVisible()
	{
	    return actionDriver.isDisplayed(MyLeaveTab);
	}

	public boolean isMyTimeSheetTabVisible()
	{
	    return actionDriver.isDisplayed(MyTimeSheetTab);
	}
	//TC05
	public void clickEmployeeLeaveSettings()
	{
		actionDriver.click(EmployeesOnLeaveToday_Setting_Btn);
	}
	
	public boolean isEmplyoeesLeavePopUpDisplayed()
	{
		return actionDriver.isDisplayed(EmployeesOnLeave_PopUp);
	}
	
	public void enableShowLeavePeriodToggle()
	{
		actionDriver.click(EmplyoeesOnLeave_CheckBox);
	}
	
	public void emplyoeesOnleaveSaveBtn()
	{
		actionDriver.click(EmplyoeesOnLeave_Save_Btn);
	}
	//
	//Method to verify OrangeHRM Logo
	public boolean VerifyOrangeHRMLogo()
	{
		return actionDriver.isDisplayed(OrangeHRMLogo);
	}
	
	//Method to perform logout
	public void logout()
	{
		actionDriver.click(UserIdBtn);
		actionDriver.click(LogoutBtn);
	}
}


