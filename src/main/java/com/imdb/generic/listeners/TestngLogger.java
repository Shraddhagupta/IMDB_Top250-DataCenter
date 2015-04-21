package com.imdb.generic.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.imdb.generic.base.BasePage;
 


public class TestngLogger extends BasePage implements ITestListener{

	public void onTestStart(ITestResult result) {
		System.out.println("Test Started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Run Completed");
				
		if(Boolean.valueOf((String) config.get("selenium.success.screenshots"))){
			
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			
			String passedScreenShotName=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date())+".png";
			String userDir=System.getProperty("user.dir")+"/Screenshots/";
			
			//Capture Screenshot
					File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
					try {
						FileUtils.copyFile(srcFile, new File(userDir+passedScreenShotName));
					} catch (IOException FileCopyException) {
						 
					}
					
					//Link screenshots to ReportNG
					Reporter.log("<a href=\""+"file:///"+ userDir + passedScreenShotName +"\"><img src=\"file:///" + userDir 
							+ passedScreenShotName + "\" alt=\"\""+ "height='100' width='100'/> "+"<br />"); 

					Reporter.setCurrentTestResult(null); 
		}
		
	}


	/*
	 * (1) Captures failure Screenshots
	 * 
	 * (2) Links them to reportNG reports
	 * 
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult result) {

		 
		
		if(Boolean.valueOf((String) config.get("selenium.failure.screenshots"))){
			
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			
			String failureImageName=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date())+".png";
			String userDir=System.getProperty("user.dir")+"/Screenshots/";
			
			//Capture Screenshot
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
			try {
				FileUtils.copyFile(srcFile, new File(userDir+failureImageName));
			} catch (IOException FileCopyException) {
			 
			}
			
			//Link screenshots to ReportNG
			Reporter.log("<a href=\""+"file:///"+ userDir + failureImageName +"\"><img src=\"file:///" + userDir 
					+ failureImageName + "\" alt=\"\""+ "height='100' width='100'/> "+"<br />"); 

			Reporter.setCurrentTestResult(null); 

		}
		
	}

	public void onTestSkipped(ITestResult result) {
		

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}