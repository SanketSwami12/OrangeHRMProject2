package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.pages.HomePage;
import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.utilities.DataProviders;
import com.orangeHRM.utilities.ExtentManager;

public class LoginPageTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages()
	{
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	@Test(dataProvider="validLoginData", dataProviderClass=DataProviders.class)
	public void verifyValidLoginTest(String username, String password)
	{
		//ExtentManager.startTest("Valid Login Test");--> This has been implemented in TestListner
		ExtentManager.logStep("Navigating to Login page entering username and password");

		loginPage.login(username, password);
		ExtentManager.logStep("Verifying Admin tab is visible or not");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin tab is visible after successful login");
		ExtentManager.logStep("Validation Successfull");

		homePage.logout();
		ExtentManager.logStep("Logged out Successfully");

		staticWait(2);
	}

	@Test(dataProvider="inValidLoginData", dataProviderClass=DataProviders.class)
	public void inValidLoginTest(String username, String password)
	{
	//	ExtentManager.startTest("In-Valid Login Test"); --> This has been implemented in TestListner
		ExtentManager.logStep("Navigating to Login page entering username and password");

		loginPage.login(username, password);
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage));
		ExtentManager.logStep("Validation Successfull");
		ExtentManager.logStep("Logged out Successfully");

	}


}

