package com.automation.generic.library;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.generic.base.BasePage;
import com.automation.generic.logger.LogMessage;
import com.google.common.base.Function;

public class FunctionLibrary extends BasePage{
	public static String currentWindow;
	
	public static void selectFromDropdown(WebElement fieldLoc, String value){
		 Select select=new Select(fieldLoc);
		 select.selectByVisibleText(value);
	}
	
	public static boolean isTextPresent(String text){
		return(driver.getPageSource().contains(text));
	}
	
	public static boolean switchToWindowAndAssert(String windowHeader){
		logger.info(new LogMessage("Switching to window having header-"+windowHeader));
		currentWindow=driver.getWindowHandle();
		boolean newWindowFinder=false;
		
		for(String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
			newWindowFinder=true;
		}
		
		if(!newWindowFinder){
			logger.debug(new LogMessage("New winodw absent"));
			return false;
		}else{
			return (driver.getPageSource().contains(windowHeader));
		}			
		
	}
	
	public static boolean switchToDefaultContent(){
		logger.info(new LogMessage("Switching to parent/default window"));
		try{
			driver.switchTo().window(currentWindow);
			return true;
		}catch(Throwable switchToDefaultContentException){
			logger.debug(new LogMessage("Error switching to deafault window"));
			return false;
		}
		
	}
	
	public static boolean isListEqual(List<String> actual, List<String> expected){
		
		int listCounter=0;
		
		for (String eachItem:actual){
			if(!eachItem.trim().equals(expected.get(listCounter).trim())){
				logger.debug(new LogMessage("List Item mismatch, Actual:"+eachItem+""+"but Expected"+expected.get(listCounter)));
				return false;
			}				
			listCounter++;				
		}
		return true;
	}
	
	public static void hover(By locator){
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
	}
	
	public static void checkCheckBox(By locator){
		
		if(driver.findElement(locator).isSelected())
			logger.info(new LogMessage("Skip-checkbox"));
		else
			driver.findElement(locator).click();
	}
	
	public static void acceptAlert(String alertText){
		
		WebDriverWait wait=new WebDriverWait(driver,10); 
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert currAlert=driver.switchTo().alert();
		
		Assert.assertEquals(currAlert.getText(), alertText);
		
		currAlert.accept();
		
	}
	
	public static void selectAllAndInput(By loc, String value){
		//Actions action=new Actions(driver);
		//action.keyDown(Keys.CONTROL).keyDown(Keys.)
		driver.findElement(loc).sendKeys(Keys.chord(Keys.CONTROL+"a"));
		driver.findElement(loc).sendKeys(value);
	}
	
	public static WebElement fluentWait(final WebDriver driver, final By currElemnt){
		
		/*Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).
				withTimeout(Long.parseLong((String)configProp.get(CONSTANTS.AJAX_ELEMNT_TIMEOUT)), TimeUnit.SECONDS).
				pollingEvery(Long.parseLong((String)configProp.get(CONSTANTS.POLL_INTERVAL)), TimeUnit.SECONDS).
				ignoring(NoSuchElementException.class);
		
		WebElement ajaxEle=wait.until(new Function<WebDriver,WebElement>(){

			public WebElement apply(WebDriver driver) {
		
				return driver.findElement(currElemnt);
			}
			
		});*/
		return driver.findElement(currElemnt);
	}
	
	
}
