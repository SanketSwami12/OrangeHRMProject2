package com.orangeHRM.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.utilities.ExtentManager;
import com.orangeHRM.utilities.RetryAnalyzer;

public class TestListener implements ITestListener, IAnnotationTransformer{
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

	// Triggered when a test starts
	@Override
	public void onTestStart(ITestResult result) {
//		// TODO Auto-generated method stub
//		String testName = result.getMethod().getMethodName();
//	
//		//Start logging in Extent Reports
//		ExtentManager.startTest(testName);
//		ExtentManager.logStep("Test Started: "+testName);
		 String testName = result.getMethod().getMethodName();

		    System.out.println("Listener: onTestStart() -> " + testName);

		    ExtentManager.startTest(testName);

		    System.out.println("ExtentTest after startTest() -> "
		            + ExtentManager.getTest());

		    ExtentManager.logStep("Test Started: " + testName);
	}
	
	//Triggered when Test succeeds
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
		ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Test End: ", testName+ "- ✓ Test Passed");
	}
	
	//Triggered when a Test Fails
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
		String failureMessage =result.getThrowable().getMessage();
		ExtentManager.logStep(failureMessage);
		ExtentManager.logFailure(BaseClass.getDriver(), "Test Failed!", "Test End: "+testName+"- X Test Failed");
	}

	//Triggered when a Test skips
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
		ExtentManager.logSkip("Test Skipped"+testName);
	}
	
	//Triggered when a suit starts
	@Override
	public void onStart(ITestContext context) {
		
	//Initialize the Extent Reports
		ExtentManager.getReprter();
		
	}
	
	//Triggered when the suites ends
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//Flush the extent report
		ExtentManager.endTest();
	}

}
