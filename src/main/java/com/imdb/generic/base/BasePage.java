package com.imdb.generic.base;

/*
 * Base(Parent) page class which every test and other utils needs to extend on order to use the resources of it
 * 
 * @author Krushna
 * 
 * 23.12.2014
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

 
 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.imdb.generic.utils.TestUtil;
import com.imdb.generic.utils.Constants;
import com.imdb.generic.utils.LocatorHandler;
 

public class BasePage<T extends BasePage<T>> extends LoadableComponent<T>{

	public static FileInputStream configRef;
	public static Properties config;
	public static Hashtable<String,HashMap<String,String>> OR;
	public static Properties logHandler;
	public static WebDriver driver;
	//protected static EventFiringWebDriver driver=null;
	public static Constants CONSTANTS=new Constants();
	 
	public static List<String> dataSetHeaders=new ArrayList<String>();
	public static String dataTableKey="KEY_Index";
	  
	protected static Object[][]  genericDataSet=null;
	 
	 
	 
	public static LocatorHandler lcHandle=new LocatorHandler();
	public static File xmlFile=null;
	public static List<Integer> runnableScenarioIndexList=new ArrayList<Integer>();
	public static int scenarioIterator=0;
	public enum Portal{
		Producer,Enterprise;
	}


	protected void configureSite( ) {
		
				
		try{	
			 
			 
			 
					configRef=new FileInputStream(".\\src\\main\\java\\com\\automation\\producer\\config\\config.properties");
					config=new Properties();
					config.load(configRef);		
				 
					//OR=TestUtil.getObjectSet(config.getProperty("object_repo_path"));					
					xmlFile=new File(config.getProperty("xmltestdatapath"));
		 
			
		 
					
		}catch(Exception configurePortal){
			 
			Assert.assertTrue("Error configuring portal"+configurePortal.getMessage(),false);
		}

	}

	
	/*
	 * @desc Used to setup the webdriver initialization etc which are needed before test run
	 * 
	 */
	/**
	 * 
	 */
	public void envsetup(){

		try{
			 

			if(config.get(CONSTANTS.BROWSER).equals("FF")){				
				driver = new FirefoxDriver();				
			}else if(config.get(CONSTANTS.BROWSER).equals("IE")){				
				File input = new File(".\\Exe\\IEDriverServer_Win32_2.42.0.exe");
				System.setProperty("webdriver.ie.driver", input.getAbsolutePath());
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				caps.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);				
			}else if(config.get(CONSTANTS.BROWSER).equals("Chrome")){				
				driver = new ChromeDriver();
			}

//			driver=new EventFiringWebDriver(driver_web);
//			WebdriverListener rc=new WebdriverListener();
//			driver.register(rc);
	 
			
			driver.manage().timeouts().implicitlyWait(Long.parseLong(config.get(CONSTANTS.IMPLICIT_WAIT).toString()), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Long.parseLong(config.get(CONSTANTS.PAGELOAD_WAIT).toString()), TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(Long.parseLong(config.get(CONSTANTS.SCRIPT_TIMEOUT).toString()), TimeUnit.SECONDS);

			 

		}catch(Throwable setupException){
			//APP_LOGS.error("Exception:"+""+setupException.getMessage());
		}

	}

	/*
	 * tearDown()
	 */
	
	public void tearDown(){

		//APP_LOGS.info("tearDown process in progress........");
		driver.close();
		driver.quit();
		 
	}

	//Unit Test Code
	public static void main(String args[]){
		BasePage basePage=new BasePage();
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

}
