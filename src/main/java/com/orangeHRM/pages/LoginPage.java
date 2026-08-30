package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM.actiondriver.ActionDriver;
import com.orangeHRM.base.BaseClass;

public class LoginPage {
	
	private ActionDriver actionDriver;
	
	//Now define locators using By class
	
	private By Username = By.xpath("//*[@name=\"username\"]");
	private By Password = By.xpath("//*[@name=\"password\"]");
	private By LoginBtn = By.xpath("//*[text()=\" Login \"]");
	private By ErrorMsg = By.xpath("//*[text()=\"Invalid credentials\"]");

	
	public LoginPage(WebDriver driver)
	{
		//this.actionDriver = new ActionDriver(driver);
		this.actionDriver = BaseClass.getActionDriver();
	}
	
	
	//Method to perform login
	public void login(String userName, String password)
	{
		actionDriver.enterText(Username, userName);
		actionDriver.enterText(Password, password);
		actionDriver.click(LoginBtn);
		
	}
	
	//Method to check if error message is displayed
	public boolean isErrorMessageDisplayed()
	{
	return	actionDriver.isDisplayed(ErrorMsg);
	}
	
	//method to get the text from error msg
	public String getErrorMessageText()
	{
		return actionDriver.getText(ErrorMsg);
	}
	
	//Verify if error is correct or not
	public boolean verifyErrorMessage(String expectedError)
	{
	//return	actionDriver.compareText(ErrorMsg, getErrorMessageText());
	    return actionDriver.compareText(ErrorMsg, expectedError);

	}
}
