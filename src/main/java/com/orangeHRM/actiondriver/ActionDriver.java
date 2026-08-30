package com.orangeHRM.actiondriver;

import java.time.Duration;
import org.apache.logging.log4j.core.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.utilities.ExtentManager;

import org.openqa.selenium.JavascriptExecutor;

// ActionDriver Setup & Create Reusable Methods

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger Logger = BaseClass.Logger;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitwait = Integer.parseInt(BaseClass.getProp().getProperty("explicitwait"));
		// new WebDriverWait(driver, Duration.ofSeconds(30)); // because of this line in
		// console it will always show this.wait is null

		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitwait));
		Logger.info("WebDriver Instance Is Created");
	}

	// Lets add different reusable methods
	// Method to click an element -> Click Method
	public void click(By by) {
		String elementDescription = getElementDescription(by);
		try {
			applyBorder(by,"green");
			waitForElemenetToBeClickable(by);
			driver.findElement(by).click();
			ExtentManager.logStep("Clicked an element: " +elementDescription);
			Logger.info("Clicked an element-->" +elementDescription);
		} catch (Exception e) {
			applyBorder(by,"red");
			Logger.error("Unable to click an element:" + e.getMessage());
			ExtentManager.logFailure(BaseClass.getDriver(), "Unable to click an element:", elementDescription+"Unable to click");
			Logger.error("Unable to click an element:");
		}
	}

	// Method to enter text into an input field -> Sendkeys method
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			applyBorder(by,"green");

			// driver.findElement(by).clear();
			// driver.findElement(by).sendKeys(value);
			// here we optimize our enterText method & reduce the code commented code is
			// also right
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
			Logger.info("Entered Text "+getElementDescription(by)+"-->" + value);
		} catch (Exception e) {
			applyBorder(by,"red");

			Logger.error("Unable to enter the value:" + e.getMessage());
		}
	}

	// Method to get text from an input field ->getText Method
	public String getText(By by)

	{
		try {
			waitForElementToBeVisible(by);
			applyBorder(by,"green");

			return driver.findElement(by).getText();
		} catch (Exception e) {
			applyBorder(by,"red");

			Logger.error("Unable to get the text:" + e.getMessage());
			return "";
		}
	}

	// Method to compare two text
	public boolean compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.equals(actualText)) {
				applyBorder(by,"green");

				Logger.info("Text are Matching:" + actualText + "equals" + expectedText);
				ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Compare Text", "Text Verified Successfully!"+actualText+ " equals " +expectedText);
				return true;
			} else {
				applyBorder(by,"red");

				Logger.error("Text are not Matching:" + actualText + "not equals" + expectedText);
				ExtentManager.logFailure(BaseClass.getDriver(), "Text Comparison Failed!", "Text Comparision Failed!"+actualText+ " not equals " +expectedText);

				return false;
			}

		} catch (Exception e) {
			applyBorder(by,"red");

			Logger.error("Unable to compare Texts:" + e.getMessage());
		}
		return false;

	}

	/*
	 * // Method to check if an element is displayed ->isDisplayed method public
	 * boolean isDisplayed(By by) { try { waitForElementToBeVisible(by); boolean
	 * isDisplayed = driver.findElement(by).isDisplayed(); if (isDisplayed)
	 * 
	 * { System.out.println("Element is visible"); return isDisplayed;
	 * 
	 * } else { return isDisplayed; } } catch (Exception e) {
	 * System.out.println("Element is not displayed:" + e.getMessage()); return
	 * false; } }
	 */ // isdisplayed method code is very lengthy so we reduce the code in bellow
		// method

	// Method to check if an element is displayed ->isDisplayed method
	public boolean isDisplayed(By by) {

		try {
			waitForElementToBeVisible(by);
			applyBorder(by,"green");

			Logger.info("Element is displayed" +getElementDescription(by));
			ExtentManager.logStep("Element is displayed" +getElementDescription(by));
			ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Element is displayed", "Element is displayed: "+getElementDescription(by));
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			applyBorder(by,"red");

			Logger.error("Element is not displayed: " + e.getMessage());
		ExtentManager.logFailure(BaseClass.getDriver(), "Element is not displayed: " ,"Element is not displayed "+getElementDescription(by));
			return false;
		}

	}

//	//Wait for the Page to load
//	public void waitForPageLoad(int timeOutInSec)
//	{
//		wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver))
//		.executeScript("return document.readyState").equals("complete"));
//		System.out.println("Page loaded successfully");
//	}

	// Scroll to an element method
	public void scrollToElement(By by) {
		try {
			applyBorder(by,"green");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("argument[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			applyBorder(by,"red");

			Logger.error("Unable to locate element:" + e.getMessage());
		}
	}

	// Wait for element to be click-able
	private void waitForElemenetToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			Logger.error("element is not clicable:" + e.getMessage());
		}
	}

	// Wait for Element to be visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			Logger.error("Element is not Visible:" + e.getMessage());
		}
	}

	// Method to get the description of an element using By locator
	public String getElementDescription(By locator) {
		// check for null driver or locator to avoid NullPointer Exception
		if (driver == null)
			return "driver is null";

		if (locator == null)
			return "Locator is null";

		try {
			// find the element using the locator
			WebElement element = driver.findElement(locator);

			// get Element Attributes
			String name = element.getDomAttribute("name");
			String id = element.getDomAttribute("id");
			String text = element.getText();
			String className = element.getDomAttribute("class");

			// Return the description based on element attributes
			if (isNotEmpty(name)) {
				return "Element with name: " + name;
			} else if (isNotEmpty(id)) {
				return "Element with id: " + id;
			} else if (isNotEmpty(text)) {
				return "Element with text: " + truncate(text, 50);

			}
		} catch (Exception e) {
		Logger.error("Unable to describe the element" +e.getMessage());
		}
		return "Unable to describe the element";

	}

	// Utility method to check a string is not null or empty
	private boolean isNotEmpty(String value) {
		return value != null && !value.isEmpty();
	}

	// Utility method to truncate long string
	private String truncate(String value, int maxLength) {
		if (value == null || value.length() <= maxLength) {
			return value;
		}
		return value.substring(0, maxLength) + "...";

	}
	
	//Utility Method to Border an element
	public void applyBorder(By by, String color)
	{
		try {
			//Locate the element
			WebElement element = driver.findElement(by);
			//Apply the border
			String script = "arguments[0].style.border='3px solid "+color+"'";
			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript(script, element);
			Logger.info("Applied the border with color "+color+ " to element " +getElementDescription(by));
		} catch (Exception e) {
			Logger.warn("Failed to apply the border to an element: "+getElementDescription(by), e);
		}
	}
}
