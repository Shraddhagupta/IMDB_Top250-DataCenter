package com.automation.imdb.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.imdb.generic.base.BasePage;
 


public class MovieDataBase extends BasePage{
 
	public MovieDataBase(){
		//super.configurePortal(Portal.Enterprise);
	}
	
	@BeforeClass(alwaysRun=true)
	public void setup(){
		 
		super.configureSite();
		super.envsetup();
	}

	@Test(enabled=true )
	public static void storeDbData(Hashtable<Hashtable<String,String>,Hashtable<String,String>[]> activateEntTestDataSet){
		  
		
		try{			
			 
		
			//Visit landing Page
			/*iAmAtLoginPage=new LoginPage(driver);
			iAmAtLandingPage=new LandingPage(driver,iAmAtLoginPage, with.EntitlementId, entId, null);
			iAmAtLandingPage.get();
			
			//Activate Entitlements
			iAmAtActivateableEntPage=(ActivateableEntitlementsPage) iAmAtLandingPage.header.navigateMeTo(subMenuItems.ActivateableEntitlements.under(menuItems.LicenseActivation), iAmAtLandingPage);
			//iAmAtActivateableEntPage.activateEntitlements(actId, actlicenseHeader, trustedActActionHeader, addHostHeader, compositeValue, fullfillmentCount, licSummaryHeader);
			iAmAtActivateableEntPage.verifyEntitlementIdOfEachActivatableItems(actId, lineItemDetailsWindowHeader, entId);*/
			
			Assert.assertTrue(true);
				
		}catch(Exception e){
			System.out.println(e.getMessage());
			Assert.assertTrue(false);
		}
		
	}
	
	 
	
	
	/*
	 * @return testcase dataset
	 */
	@DataProvider
	public Object[][] getLoginData(Method caller){
		return null;
		 
		 
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		 
		super.tearDown();
	}
}
