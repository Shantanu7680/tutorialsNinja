package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;  
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	
		ExtentReports extentReport;
		ExtentTest extentTest;
		
	@Override
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+" test started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName()+"test got executed succesfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
	
	try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String screenShotDestinationPath=Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(screenShotDestinationPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+" test got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"test got skipped");
	
	}

	
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		//---creating code below to open the extentreport automatically on test completion
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReportFile = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//--- automatic extent report open code ends here
	}
	
	

}
