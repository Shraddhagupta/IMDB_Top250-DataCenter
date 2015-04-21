package com.imdb.generic.utils;
/*
 * Utility file containing most of the reusable methods which a Test uses
 * 
 * @author Krushna
 * 
 * @date 22.12.2014
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.imdb.generic.base.BasePage;
 

public class TestUtil extends BasePage{

	
	
	public static Hashtable<String,HashMap<String,String>> getObjectSet(String objectFilePath) throws FileNotFoundException,IOException{
		
		try{
			FileInputStream file = new FileInputStream(objectFilePath);
			Hashtable<String,HashMap<String,String>> objectSet=new Hashtable<String,HashMap<String,String>>();        
			HashMap<String,String> sheetObjects = null;

			if (file != null) {
				
				XSSFWorkbook wb = new XSSFWorkbook(file);

				for(int sheetCounter=0;sheetCounter<wb.getNumberOfSheets();sheetCounter++){

					XSSFSheet sheet=wb.getSheetAt(sheetCounter);
					sheetObjects=new HashMap<String,String>();
					int rowsize = sheet.getLastRowNum();

					for (int i = 1; i <= rowsize; i++) {
						XSSFRow rowi = sheet.getRow(i);
						XSSFCell cellij = rowi.getCell(0);
						String strCellvalue0 = cellij.getStringCellValue();
						XSSFCell celli = rowi.getCell(1);
						String strCellvalue = celli.getStringCellValue();
						sheetObjects.put(strCellvalue0, strCellvalue);
					}
					objectSet.put(wb.getSheetAt(sheetCounter).getSheetName(), sheetObjects);
					sheetObjects=null;
				}
			}
			
			return objectSet;
		}catch(FileNotFoundException fileAbsentException){
			 
			Assert.assertTrue("File Absent:-"+fileAbsentException.getMessage(),false);
			return null;
		}catch(IOException ioException){
			 
			Assert.assertTrue("File Absent"+ioException.getMessage(),false);
			return null;
		}catch(Exception getObjectSetException){
	 
			Assert.assertTrue("Error with getObject Method"+getObjectSetException.getMessage(),false);
			return null;
		}
		
	}
 
	
	public static void main(String[] args){
		String command="cmd /c D://test.bat \"/d D:\\v11.9_demo\\i86_n3\" a b";
		String[] commands2={"cmd","/c","Start","D:\\test.bat"};
	}
}
