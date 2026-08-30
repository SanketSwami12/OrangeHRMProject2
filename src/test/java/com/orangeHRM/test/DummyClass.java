package com.orangeHRM.test;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.utilities.ExtentManager;

public class DummyClass extends BaseClass {
	@Test
	public void dummyTest() {
		//ExtentManager.startTest("Dummy Test"); --> This has been implemented in TestListner

		String title = getDriver().getTitle();
		ExtentManager.logStep("Verifying the Title");

		assert title.equals("OrangeHRM") : "Test Failed - Title is not Matching";

		System.out.println("Test Passed - Title is Matching");
	//	ExtentManager.logSkip("This case is skipped");
		throw new SkipException("Skipping the Test as part of Testing");

	}

}
