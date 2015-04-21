package com.automation.generic.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.automation.generic.base.BasePage;
import com.automation.generic.utils.TestScenarioData;
import com.automation.generic.base.BasePage;

public class GetDataFromXml extends BasePage{
	public static DocumentBuilderFactory dbfactory;
	public static DocumentBuilder docbuilder;
	public static Document testDoc;
	public static int currentNodeIteratorVal=0;
	public static enum xmlheirarchy{
		Entitlement,Prouct,featureBundle,Feature;
	};
	public static void xmloperations(){
	
		if(xmlFile.exists()){
			try{				
				
				System.out.println(testDoc.getDocumentElement().getNodeName());
				NodeList nlist=testDoc.getElementsByTagName("testcase");
				System.out.println(nlist.item(0).getAttributes().getNamedItem("name").toString().split("=")[1]);
			}catch(Throwable t){
				System.out.println(t.getMessage());
			}
			
		}
	}
	
	public static int childElementCount(String parentElementName, String childElementName){
		try{
			dbfactory=DocumentBuilderFactory.newInstance();
			docbuilder=dbfactory.newDocumentBuilder();
			testDoc=docbuilder.parse(xmlFile);

			XPath xpath=XPathFactory.newInstance().newXPath();
			NodeList nodelist=(NodeList) xpath.evaluate("//*[@id='"+parentElementName+"']//"+childElementName, testDoc, XPathConstants.NODESET);
			
			for (int i=0;i<nodelist.getLength();i++){
				
				Node runnableFeatureNode=(Node) xpath.evaluate("//*[@id='"+parentElementName+"']//"+childElementName+"["+(i+1)+"]", testDoc, XPathConstants.NODE);
				try{
					if(runnableFeatureNode.getAttributes().getNamedItem("enabled").toString().split("=")[1].replace("\"", "").equalsIgnoreCase("true")){
						BasePage.runnableScenarioIndexList.add((i+1));
					}
				}catch(NullPointerException e){
					
				}
				
			}
			
			return (BasePage.runnableScenarioIndexList.size());
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
		
	}
	
	public static List<String>[] getAllChildNodeNames(String parentNodeName){
		try{
			dbfactory=DocumentBuilderFactory.newInstance();
			docbuilder=dbfactory.newDocumentBuilder();
			testDoc=docbuilder.parse(xmlFile);
			List<String> values[];
;
			XPath xpath=XPathFactory.newInstance().newXPath();
			NodeList nodelist=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName, testDoc, XPathConstants.NODESET);
			values=new List[nodelist.getLength()];
			for (int i=0;i<nodelist.getLength();i++){
				values[i]=new ArrayList<String>();
				Node runnableFeatureNode=(Node) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName+"["+(i+1)+"]", testDoc, XPathConstants.NODE);
				
				values[i].add(runnableFeatureNode.getNodeName());
				
				while(runnableFeatureNode!=null){
					if(runnableFeatureNode.getChildNodes().getLength()>1){
						values[i].add(runnableFeatureNode.getChildNodes().item(1).getNodeName());
						runnableFeatureNode=runnableFeatureNode.getChildNodes().item(1);
					}else{
						break;
					}
				}
			}
				
			return (values);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	/*public static List<String>[][] getAllHeirarchyChildNodeNames(String parentNodeName){
		try{
			dbfactory=DocumentBuilderFactory.newInstance();
			docbuilder=dbfactory.newDocumentBuilder();
			testDoc=docbuilder.parse(xmlFile);
			List<String> values[][];
			Node currentNode=null;
;
			XPath xpath=XPathFactory.newInstance().newXPath();
			NodeList nodelist=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName, testDoc, XPathConstants.NODESET);
			values=new List[nodelist.getLength()][];
			for (int i=0;i<nodelist.getLength();i++){
				Node runnableFeatureNode=(Node) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName+"["+(i+1)+"]", testDoc, XPathConstants.NODE);
				
				
			
					values[i]=new List[runnableFeatureNode.get.getChildNodes().getLength()];
					for(int childNodeIt=0;childNodeIt<runnableFeatureNode.getChildNodes().getLength();childNodeIt++){
						values[i][childNodeIt]=new ArrayList<String>();
						values[i][childNodeIt].add(runnableFeatureNode.getNodeName());
						int j=0;
						currentNode=runnableFeatureNode.getChildNodes().item(childNodeIt);
						while(currentNode!=null){
							values[i][childNodeIt].add(currentNode.getNodeName());
							j++;
							currentNode=runnableFeatureNode.getChildNodes().item(j);
						
						}
						
					}
				
			}
				
			return (values);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}*/
	
	public static String[] fetchNodeAttrValues(String nodeName, String attrName){		
		
		try {
			XPath xpath=XPathFactory.newInstance().newXPath();
			NodeList nodelist=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+nodeName, testDoc, XPathConstants.NODESET);
			String[] attrValues=new String[nodelist.getLength()];
			
			for(int i=0;i<nodelist.getLength();i++){
				Node node=(Node) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+nodeName+"["+(i+1)+"]", testDoc, XPathConstants.NODE);
				try{
					attrValues[i]=node.getAttributes().getNamedItem(attrName).toString().split("=")[1].replace("\"", "");
				}catch(NullPointerException e){
					
				}
			}
			
			return attrValues;
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Hashtable<String, String>[] returnAttrValuePair(String nodeName){
		
		try{
			int nodeAttrCount;
			XPath xpath=XPathFactory.newInstance().newXPath();
			NodeList nodelist=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+nodeName, testDoc, XPathConstants.NODESET);
			
			Hashtable<String, String>[] attrValuePair=new Hashtable[nodelist.getLength()];
			
			for(int nodeiterator=0;nodeiterator<nodelist.getLength();nodeiterator++){
				nodeAttrCount=nodelist.item(nodeiterator).getAttributes().getLength();
				attrValuePair[nodeiterator]=new Hashtable<String, String>();
				for(int attrIterator=0; attrIterator<nodeAttrCount; attrIterator++){
					attrValuePair[nodeiterator].put(nodelist.item(nodeiterator).getAttributes().item(attrIterator).toString().split("=")[0], nodelist.item(nodeiterator).getAttributes().item(attrIterator).toString().split("=")[1].replace("\"", ""));
				}				
			}
			
			return attrValuePair;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> returnParentChildNdeAttrList(String parentNodeName, String childNodeName){
		
		try{
			int nodeAttrCount, childAttrCount;
			XPath xpath=XPathFactory.newInstance().newXPath();
			Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> parentChildAttributes=new Hashtable<Hashtable<String, String>, Hashtable<String, String>[]>();
			Hashtable<String, String> key;
			Hashtable<String, String>[] childAttrList;
			NodeList parentNodeList=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName, testDoc, XPathConstants.NODESET);
			currentNodeIteratorVal=parentNodeList.getLength();
			for(int nodeiterator=0;nodeiterator<parentNodeList.getLength();nodeiterator++){
				
				key=new Hashtable<String, String>();				
				nodeAttrCount=parentNodeList.item(nodeiterator).getAttributes().getLength();				
				for(int attrIterator=0; attrIterator<nodeAttrCount; attrIterator++){
					key.put(parentNodeList.item(nodeiterator).getAttributes().item(attrIterator).toString().split("=")[0], parentNodeList.item(nodeiterator).getAttributes().item(attrIterator).toString().split("=")[1].replace("\"", ""));
				}	
				
				NodeList childNodeList=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName+"["+(nodeiterator+1)+"]"+"//"+childNodeName, testDoc, XPathConstants.NODESET);
				childAttrList=new Hashtable[childNodeList.getLength()];				
				for(int childNodeiterator=0;childNodeiterator<childNodeList.getLength();childNodeiterator++){
					childAttrCount=childNodeList.item(childNodeiterator).getAttributes().getLength();
					childAttrList[childNodeiterator]=new Hashtable<String, String>();
					
					for(int attrIterator=0; attrIterator<childAttrCount; attrIterator++){
						childAttrList[childNodeiterator].put(childNodeList.item(childNodeiterator).getAttributes().item(attrIterator).toString().split("=")[0], childNodeList.item(childNodeiterator).getAttributes().item(attrIterator).toString().split("=")[1].replace("\"", ""));
					}
				}
					
				parentChildAttributes.put(key, childAttrList);
			}
			
			return parentChildAttributes;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> returnEachParentChildNdeAttrList(String parentNodeName, String childNodeName){
		
		try{
			int nodeAttrCount, childAttrCount;
			XPath xpath=XPathFactory.newInstance().newXPath();
			Hashtable<Hashtable<String, String>, Hashtable<String, String>[]> parentChildAttributes=new Hashtable<Hashtable<String, String>, Hashtable<String, String>[]>();
			Hashtable<String, String> key;
			Hashtable<String, String>[] childAttrList;
			int nodeiterator=TestCaseData.nodeIteratorRef;
			
			key=new Hashtable<String, String>();	
			try{
				NodeList parentNodeList=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName, testDoc, XPathConstants.NODESET);
				currentNodeIteratorVal=parentNodeList.getLength();
							
					nodeAttrCount=parentNodeList.item(nodeiterator).getAttributes().getLength();				
					for(int attrIterator=0; attrIterator<nodeAttrCount; attrIterator++){
						key.put(parentNodeList.item(nodeiterator).getAttributes().item(attrIterator).toString().split("=")[0], parentNodeList.item(nodeiterator).getAttributes().item(attrIterator).toString().split("=")[1].replace("\"", ""));
					}	
			}catch(Exception e){
				key.put("", "");
			}
			
				
				NodeList childNodeList=(NodeList) xpath.evaluate(TestScenarioData.testScenarioLocator+"//"+parentNodeName+"["+(nodeiterator+1)+"]"+"//"+childNodeName, testDoc, XPathConstants.NODESET);
				childAttrList=new Hashtable[childNodeList.getLength()];				
				for(int childNodeiterator=0;childNodeiterator<childNodeList.getLength();childNodeiterator++){
					childAttrCount=childNodeList.item(childNodeiterator).getAttributes().getLength();
					childAttrList[childNodeiterator]=new Hashtable<String, String>();
					
					for(int attrIterator=0; attrIterator<childAttrCount; attrIterator++){
						childAttrList[childNodeiterator].put(childNodeList.item(childNodeiterator).getAttributes().item(attrIterator).toString().split("=")[0], childNodeList.item(childNodeiterator).getAttributes().item(attrIterator).toString().split("=")[1].replace("\"", ""));
					}
				}
					
				parentChildAttributes.put(key, childAttrList);
			
			
			return parentChildAttributes;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

}
