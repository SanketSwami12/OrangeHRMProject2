package com.orangeHRM.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

// When we run any test case or suite so due to some reasons test case or suite getting failed may be network issue
// or timing issue or may be environment issue so to overcome this we use RetryAnalyzer TestNg annotation
// here in utility package we create one retry analyzer class & we implements IRetryAnalyzer class in it
//& in the code retry count is 0 to 2
//so if test case is failed so it will retry 2 times so this is the logic behind this class
//We can achieve this in multiple way first using listeners & 2nd apply retry analyzer parameters in @Test annotation 
//Ex:- 	//@Test (retryAnalyzer = RetryAnalyzer.class)
//public class TestListener implements ITestListener, IAnnotationTransformer{
//public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
//annotation.setRetryAnalyzer(RetryAnalyzer.class);



public class RetryAnalyzer implements IRetryAnalyzer{

	private int retryCount = 0; //Number of retries
	private static final int maxRetryCount = 2; // Maximum no of retries
	
	@Override
	public boolean retry(ITestResult result) {
		if(retryCount < maxRetryCount)
		{
			retryCount++;
			return true; //Retry the test
		}
		return false;
	}

}
