package com.tutorialsninja.listener;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.tutorialsninja.utility.ExtentReporter;
import com.tutorialsninja.utility.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	public void onStart(ITestContext context) {
		extentReport=ExtentReporter.generateExtentReport();
	}
	
	public void onTestStart(ITestResult result) {
		testName=result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName+" Started Executing");
	}

	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, testName + " got successfully executed");

	}

	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
		
	
		String destinationScreenshotPath= Utilities.captureScreenshot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL, testName +" got failed");
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got Skipped");

	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport =System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
