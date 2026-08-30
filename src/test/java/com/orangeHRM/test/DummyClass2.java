package com.orangeHRM.test;

import org.testng.annotations.Test;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.utilities.ExtentManager;

public class DummyClass2 extends BaseClass {
	@Test
	public void dummyTest()
	{
		//ExtentManager.startTest("Dummy Test 2");--> This has been implemented in TestListner

		String title = getDriver().getTitle();
		ExtentManager.logStep("Verifying the Title");

		assert title.equals("OrangeHRM"): "Test Failed - Title is not Matching";

		
		System.out.println("Test Passed - Title is Matching");
		ExtentManager.logStep("Validation Successfull");


	}

}
