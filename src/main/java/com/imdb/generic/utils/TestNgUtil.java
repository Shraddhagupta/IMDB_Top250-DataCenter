package com.imdb.generic.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.SkipException;
import org.testng.annotations.ITestAnnotation;

import com.imdb.generic.base.BasePage;

public class TestNgUtil extends BasePage implements IAnnotationTransformer{

	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		
			annotation.setAlwaysRun(true);		
		}
			
		
	}
	

