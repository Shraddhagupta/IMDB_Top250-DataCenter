package com.automation.imdb.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
 


import com.automation.imdb.pages.LoginPage;
import com.imdb.generic.base.BasePage;
import com.imdb.generic.utils.LocatorHandler;

public class LandingPage extends BasePage{
	
	private static WebDriver driver;
	private static LoadableComponent<?> parent;
	 
	private static String unameOrActIdOrEntId;
	private static String pswd;
	private with type;
	private static LocatorHandler lcHandle=new LocatorHandler();
	public static HashMap<String,String> LandingPageObjectSet=null;
	private String pageTitle="Home Page - FlexNet Operations End-User Portal:";
	
	public LandingPage(WebDriver driver){
		this.driver=super.driver;
		LandingPageObjectSet=OR.get(CONSTANTS.LANDING_OBJECT_SHEET_NAME);
	}
	
	public LandingPage(WebDriver driver, LoadableComponent<?> parent,with type,String unameOrActIdOrEntId, String pswd){
		this.driver=driver;
		this.parent=parent;		
		this.type=type;
		this.unameOrActIdOrEntId=unameOrActIdOrEntId;
		this.pswd=pswd;
		header=new HeaderTabs(this.driver);
		PageFactory.initElements(driver, LandingPage.class);
	}
	
	@Override
	public void load(){
		parent.get();
		
		switch(type){
			case UserName:
				driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("WithUserName"))).click();
				driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("UserName"))).sendKeys(unameOrActIdOrEntId);
				driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("Password"))).sendKeys(pswd);
				break;
			case ActivationId:
				driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("withActivationid"))).click();
				driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("ActivationInput"))).sendKeys(unameOrActIdOrEntId);
				break;
			case EntitlementId:
				driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("withEntitlementId"))).click();
				driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("EntitlementInput"))).sendKeys(unameOrActIdOrEntId);
				break;
		}
		
		driver.findElement(lcHandle.autoLocator(LoginPage.loginPageObjectSet.get("LogInBtn"))).click();
	}
	
	@Override
	public void isLoaded(){
		Assert.assertEquals(driver.getTitle().trim(), pageTitle.trim());
	}
	
public LandingPage verifyEntitlementIdUnderRecentEntitlementsTable(String productName, String windowHeader, String actId){
		logger.info(new LogMessage("Verifying entitlementid of activateable entitlment present under recent entitlement table"));
		Assert.assertTrue(driver.findElement(lcHandle.autoLocator(LandingPageObjectSet.get("EntitlementTable_1")+productName+LandingPageObjectSet.get("EntitlementTable_2"))).isDisplayed());
		driver.findElement(lcHandle.autoLocator(LandingPageObjectSet.get("EntitlementTable_1")+productName+LandingPageObjectSet.get("EntitlementTable_2"))).click();
		FunctionLibrary.switchToWindowAndAssert(windowHeader);
		Assert.assertEquals(driver.findElement(lcHandle.autoLocator(LandingPageObjectSet.get("Actid_LineItems_Details_Window"))).getText(), actId);
		FunctionLibrary.switchToDefaultContent();
		
		return this;
	}
}
