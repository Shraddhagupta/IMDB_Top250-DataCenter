package com.imdb.generic.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;

import com.imdb.generic.base.BasePage;
 


public class TestCaseData {
	
	private static String testcaseid;
	private String scenarioid;
	private static int runnableScenarioCount=0;
	public static String testcaseLocator;
	public static List<String> values[]=null;;
	public static int nodeIteratorRef=0;
	public static Object[][] entitlementChannelPartnerDataSet=null;
	public static Object[][] entitlementLineItemDataSet=null;
	public static Object[][] lineItemProductDataSet=null;
	public static Object[][] productFeatureDataSet=null;
	public static Object[][] bulkEntitlementProductDataSet=null;
	public static Object[][] channelPartnerUserDataSet=null;
	public static Object[][] bulkEntitlementChannelPartnerDataSet=null;
	public static Object[][] organizationsData=null;
	public static Object[][] channelPartnerGroupDataSet=null;
	public static Object[][] partnerUserOrgGroupDataSet=null;
	
	
	public static Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> parentChildAttrList[]=null;
	 public TestCaseData(String tcid){
		this.testcaseid=tcid;
		this.testcaseLocator="//testcase[@id='"+tcid+"']";
	}
	 
	 public Object[][] getMeTestScObjects(){
		 runnableScenarioCount=GetDataFromXml.childElementCount(testcaseid, "scenario");
		 TestScenarioData scenario[]=new TestScenarioData[runnableScenarioCount];
		 Object[][] testscobjects=new Object[runnableScenarioCount][1];
		 
		 for(int i=0;i<runnableScenarioCount;i++){
			 testscobjects[i][0]=scenario[i];
			 
		 }
		 
		 return testscobjects;
	 }
	 
	 public Object[][] getMeNodeObjects(String nodeName){
		 runnableScenarioCount=GetDataFromXml.childElementCount(testcaseid, "scenario");
		 TestScenarioData scenario[]=new TestScenarioData[runnableScenarioCount];
		 Object[][][] testscobjects=new Object[runnableScenarioCount][][];
		 Object[][] testSuperSet=null;
		 int objectCounter=0;
		 int combinedSize=0;
		 int copyLocator=0;
		 
		 for(int i=0;i<runnableScenarioCount;i++){
			 scenario[i]=new TestScenarioData(BasePage.runnableScenarioIndexList.get(i));
			 Hashtable<String, String>[] featureList=scenario[i].fetchAllAttributes(nodeName);
			 testscobjects[i]=new Object[featureList.length][1];
			 combinedSize+=featureList.length;
			 for(int j=0;j<featureList.length;j++){
				 testscobjects[i][j][0]=featureList[j];
				 objectCounter++;
			 }
			
			 
		 }
		 
		 testSuperSet=new Object[combinedSize][1];
		 
		 for(int i=0;i<runnableScenarioCount;i++){
			 System.arraycopy(testscobjects[i], 0, testSuperSet, copyLocator, testscobjects[i].length);
			 copyLocator+=testscobjects[i].length;
		 }
		
		 
		 return testSuperSet;
	 }
	 
		public Object[][] getEntitlementObjects(){
			entitlementChannelPartnerDataSet=null;
			entitlementLineItemDataSet=null;
			lineItemProductDataSet=null;
			productFeatureDataSet=null;
			channelPartnerUserDataSet=null;
			
			entitlementChannelPartnerDataSet=getMeParentChildNodeObjects("entitlement", "channelpartner");
			entitlementLineItemDataSet=getMeParentChildNodeObjects("entitlement", "lineitem");
			lineItemProductDataSet=getMeParentChildNodeObjects("lineitem", "product");
			productFeatureDataSet=getMeParentChildNodeObjects("product", "feature");
			channelPartnerUserDataSet=getMeParentChildNodeObjects("channelpartner", "user");
			
			return entitlementLineItemDataSet;
		}
		
		public Object[][] getEntitlementAndOrgObjectsData(){
			entitlementChannelPartnerDataSet=null;
			entitlementLineItemDataSet=null;
			lineItemProductDataSet=null;
			productFeatureDataSet=null;
			channelPartnerGroupDataSet=null;
			organizationsData=null;
			
			entitlementChannelPartnerDataSet=getMeParentChildNodeObjects("entitlement", "channelpartner");
			entitlementLineItemDataSet=getMeParentChildNodeObjects("entitlement", "lineitem");
			lineItemProductDataSet=getMeParentChildNodeObjects("lineitem", "product");
			productFeatureDataSet=getMeParentChildNodeObjects("product", "feature");
			channelPartnerGroupDataSet=getMeParentChildNodeObjects("channelpartner", "group");
			organizationsData=getMeParentChildNodeObjects("organizations", "organization");
			
			return entitlementLineItemDataSet;
		}
		
		
		public Object[][] getBulkEntitlementObjects(){
			
			productFeatureDataSet=null;
			channelPartnerUserDataSet=null;
			bulkEntitlementChannelPartnerDataSet=null;
			bulkEntitlementProductDataSet=null;
			
			bulkEntitlementChannelPartnerDataSet=getMeParentChildNodeObjects("bulkentitlement", "channelpartner");
			channelPartnerUserDataSet=getMeParentChildNodeObjects("channelpartner", "user");
			bulkEntitlementProductDataSet=getMeParentChildNodeObjects("bulkentitlement", "product");
			productFeatureDataSet=getMeParentChildNodeObjects("product", "feature");
			
			return bulkEntitlementProductDataSet;
		}
		
		public Object[][] getProductObjects(){
			
			productFeatureDataSet=null;
			productFeatureDataSet=getMeParentChildNodeObjects("product", "feature");
			
			 
			return productFeatureDataSet;
		}
		
		public Object[][] getUserObjects(){
			return(getMeParentChildNodeObjects("user", "organization"));
		}
		
	 public Object[][] getMeParentChildNodeObjects(String parentNode, String childNode){
		 try{
			 
			 runnableScenarioCount=GetDataFromXml.childElementCount(testcaseid, "scenario");
			 TestScenarioData scenario[]=new TestScenarioData[runnableScenarioCount];
			 parentChildAttrList=new Hashtable[runnableScenarioCount];
			 Object[][] testscobjects=new Object[runnableScenarioCount][1];
			 Object[][] keyNodes[]=new Object[runnableScenarioCount][][];
			 Object[][] testSuperSet;
			 int objectCounter=0;
			 int combinedSize=0;
			 int copyLocator=0;
			 
			 for(int i=0;i<runnableScenarioCount;i++){
				 scenario[i]=new TestScenarioData(BasePage.runnableScenarioIndexList.get(i));
				 parentChildAttrList[i]=scenario[i].fetchAllParentnChildAttributes(parentNode, childNode);
				 keyNodes[i]=new Object[parentChildAttrList[i].size()][1];
				 int j=0;
				 for(final Hashtable<String, String> key:parentChildAttrList[i].keySet()){
					 Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> temp=new Hashtable<Hashtable<String, String>, Hashtable<String, String>[]>();
					 temp.put(key, parentChildAttrList[i].get(key));
					 keyNodes[i][j][0]=temp;
					 j++;
				 }
				 combinedSize+=parentChildAttrList[i].keySet().size();
			 }
			 		 
			 testSuperSet=new Object[combinedSize][1];
			 
			 for(int i=0;i<runnableScenarioCount;i++){
				 for(int j=0;j<parentChildAttrList[i].keySet().size();j++){
					 //testscobjects[i][j]=keyNodes[i][j][0];
					 testSuperSet[copyLocator][0]=keyNodes[i][j][0];
					 copyLocator++;
				 }
				 /*System.arraycopy(testscobjects[i], 0, testSuperSet, copyLocator, testscobjects[i].length);
				 copyLocator+=testscobjects[i].length;*/
			 }
			 
			 BasePage.runnableScenarioIndexList=new ArrayList<Integer>();
			 return testSuperSet; 
		 }catch(Exception getMeParentChildNodeObjectsException){
			  
			 Assert.assertTrue(false, "Error in getMeParentChildNodeObjects: "+ getMeParentChildNodeObjectsException.getMessage());
			 return null;
		 }
		 
	 }
	 
	 public Object[][] getAllObjectHeirarchy(String parentNode){
		
		 Object[][][] objects;
		 runnableScenarioCount=GetDataFromXml.childElementCount(testcaseid, "scenario");
		 TestScenarioData scenario[]=new TestScenarioData[runnableScenarioCount];
		 int combinedSize=0,copyLocator=0; 
		 Object[][] relatingDatas;
		 Object[][] testDataSuperSet=null;
		 int counter=0;
		 List<Hashtable<Hashtable<String, String>,Hashtable<String, String>[]>> eachEntityParser[]=null;
		 relatingDatas=new Object[runnableScenarioCount][];
		 for(int i=0;i<runnableScenarioCount;i++){
			 scenario[i]=new TestScenarioData(BasePage.runnableScenarioIndexList.get(i));
			 values=GetDataFromXml.getAllChildNodeNames(parentNode);
			 relatingDatas[i]=new Object[values.length];
			 eachEntityParser=new  ArrayList[values.length];
			 for(int j=0;j<values.length;j++){
				 nodeIteratorRef=j;
				 objects=new Object[values[j].size()][][]; 
				 eachEntityParser[j]=new ArrayList<Hashtable<Hashtable<String, String>,Hashtable<String, String>[]>>();
				 for(int k=0;k<values[j].size()-1;k++){	
					 if(k>=1){
						 eachEntityParser[j].add(scenario[i].fetchAllParentnChildAttributes(values[j].get(k-1)+"//"+values[j].get(k),values[j].get(k+1)));
					 }else{
						 eachEntityParser[j].add(scenario[i].fetchEachParentnChildAttributes(values[j].get(k),values[j].get(k+1)));
					 }
					
				 }
				 relatingDatas[i][j]=eachEntityParser[j];
			 }
			 combinedSize+=values.length;
		 }
		 
		 testDataSuperSet=new Object[combinedSize][1];
		 for(int i=0;i<runnableScenarioCount;i++){
			 for(int j=0;j<values.length;j++){
				 testDataSuperSet[counter][0]=relatingDatas[i][j];
				 counter++;
			 }
			
			 
		 }

		 return testDataSuperSet;
	 }
	 
	 public Object[][] returnParentChildNodeObjects(String parentNode, String childNode){
		 runnableScenarioCount=GetDataFromXml.childElementCount(testcaseid, "scenario");
		 TestScenarioData scenario[]=new TestScenarioData[runnableScenarioCount];
		 parentChildAttrList=new Hashtable[runnableScenarioCount];
		 Object[][] testscobjects=new Object[runnableScenarioCount][1];
		 Object[][] keyNodes[]=new Object[runnableScenarioCount][][];
		 Object[][] testSuperSet;
		 int objectCounter=0;
		 int combinedSize=0;
		 int copyLocator=0;
		 
		 for(int i=0;i<runnableScenarioCount;i++){
			 scenario[i]=new TestScenarioData(BasePage.runnableScenarioIndexList.get(i));
			 parentChildAttrList[i]=scenario[i].fetchAllParentnChildAttributes(parentNode, childNode);
			 keyNodes[i]=new Object[parentChildAttrList[i].size()][1];
			 int j=0;
			 for(final Hashtable<String, String> key:parentChildAttrList[i].keySet()){
				 Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> temp=new Hashtable<Hashtable<String, String>, Hashtable<String, String>[]>();
				 temp.put(key, parentChildAttrList[i].get(key));
				 keyNodes[i][j][0]=temp;
				 j++;
			 }
			 combinedSize+=parentChildAttrList[i].keySet().size();
		 }
		 		 
		 testSuperSet=new Object[combinedSize][1];
		 
		 for(int i=0;i<runnableScenarioCount;i++){
			 for(int j=0;j<parentChildAttrList[i].keySet().size();j++){
				 //testscobjects[i][j]=keyNodes[i][j][0];
				 testSuperSet[copyLocator][0]=keyNodes[i][j][0];
				 copyLocator++;
			 }
			 /*System.arraycopy(testscobjects[i], 0, testSuperSet, copyLocator, testscobjects[i].length);
			 copyLocator+=testscobjects[i].length;*/
		 }
		
		 
		 return testSuperSet;
	 }

	public Object[][] getPartnerUserObjects() {
		
		partnerUserOrgGroupDataSet=null;
		
		return	partnerUserOrgGroupDataSet=getMeParentChildNodeObjects("organization","users");
				
	}
	 
}
