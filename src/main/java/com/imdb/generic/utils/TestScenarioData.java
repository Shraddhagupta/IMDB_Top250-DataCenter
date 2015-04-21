package com.imdb.generic.utils;

import java.util.HashMap;
import java.util.Hashtable;

import com.imdb.generic.utils.GetDataFromXml;

public class TestScenarioData {
	
	private int scid;
	private String testcaseid;
	private String nodeName;
	private String attrName;
	public static String testScenarioLocator;
	public TestScenarioData(int scid){
		this.scid=scid;
		this.testScenarioLocator=TestCaseData.testcaseLocator+"//scenario["+(scid)+"]";
	}
	
	public void nodeAttributeValues(String nodeName, String attrName){
		this.nodeName=nodeName;
		this.attrName=attrName;
		GetDataFromXml.fetchNodeAttrValues(nodeName, attrName);
	}
	
	public Hashtable<String, String>[] fetchAllAttributes(String nodeName){
		this.nodeName=nodeName;
		return (GetDataFromXml.returnAttrValuePair(nodeName));
	}
	
	public Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> fetchAllParentnChildAttributes(String parentNode, String childNode){
		this.nodeName=nodeName;
		return (GetDataFromXml.returnParentChildNdeAttrList(parentNode, childNode));
	}
	
	public Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> fetchEachParentnChildAttributes(String parentNode, String childNode){
		this.nodeName=nodeName;
		return (GetDataFromXml.returnEachParentChildNdeAttrList(parentNode, childNode));
	}
	
	public void printScId(){
		System.out.println("scid:-"+scid);
	}
}
