package com.imdb.generic.utils;

import org.openqa.selenium.By;

public class LocatorHandler {

	public enum locatorType {
		CLASS_NAME, CSS_SELECTOR, XPATH, ID, LINK_TEXT, NAME, PARTIAL_LINK_TEXT, TAG_NAME
	}
	
	
	public final locatorType getLocatorType (String locator) {
		if(locator.startsWith("xpath") || locator.startsWith("//") || locator.startsWith("(//") ) {
			return  locatorType.XPATH;
		}else if(locator.startsWith("css=") || locator.startsWith("#") || locator.startsWith(".") ) {
			return locatorType.CSS_SELECTOR;
		}else if(locator.startsWith("name=")) {
			return locatorType.NAME;
		}else if(locator.startsWith("link=")) {
			return locatorType.LINK_TEXT;
		}else if(locator.startsWith("class=")) {
			return locatorType.CLASS_NAME;
		}else if(locator.startsWith("tag=")) {
			return locatorType.TAG_NAME;
		}else if(locator.startsWith("partialLink=")) {
			return locatorType.PARTIAL_LINK_TEXT;
		}else{
			return locatorType.ID;
		}	
	}
	
	public By autoLocator(String locator) {
	        switch (getLocatorType(locator)) {
	            case XPATH:
	                if (locator.startsWith("xpath=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.xpath(locator);
	            case CSS_SELECTOR:
	                if (locator.startsWith("css=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.cssSelector(locator);
	            case ID:
	                if (locator.startsWith("id=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.id(locator);
	            case NAME:
	                if (locator.startsWith("name=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.name(locator);
	            case CLASS_NAME:
	                if (locator.startsWith("class=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.className(locator);
	            case TAG_NAME:
	                if (locator.startsWith("tag=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.tagName(locator);
	            case LINK_TEXT:
	            	if (locator.startsWith("link=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.linkText(locator);
	            case PARTIAL_LINK_TEXT:
	            	if (locator.startsWith("partialLink=")) {
	                    locator = locator.split("=", 2)[1];
	                }
	                return By.partialLinkText(locator);
	            default:
	                if (locator.startsWith("dom=")) {
	                    locator = locator.split("=", 2)[1];
	                    return By.xpath(locator);
	                }
	        }
			return null;	        
	 }
	 
	public static void main(String []a){
		 LocatorHandler loc = new LocatorHandler();
		loc.autoLocator("xpath=(//)");
	}
	
}
