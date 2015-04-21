package com.automation.imdb.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.imdb.generic.base.BasePage;

public class LoginPage extends BasePage{
	
	private static WebDriver driver;
	public static HashMap<String,String> loginPageObjectSet=null;
	private String pageTitle="FlexNet Login";
	public enum with{
		UserName,ActivationId,EntitlementId;
	}
	public LoginPage(WebDriver driver){
		this.driver=driver;
		loginPageObjectSet=OR.get(CONSTANTS.LOGIN_PAGE_OBJECTS);	
	}
	
	@Override
	public void load(){
		driver.navigate().to(config.getProperty(CONSTANTS.APP_URL));		
	}
	
	@Override
	public void isLoaded(){
		Assert.assertEquals(driver.getTitle().trim(),pageTitle);
	}
}

