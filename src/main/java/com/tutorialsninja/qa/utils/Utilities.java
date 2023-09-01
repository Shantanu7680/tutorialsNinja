package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	public static final int implicit_wait_time=10;
	public static final int page_load_timeout=5;
	
	//--this is method used to generate new email everytime using datestamp
	public static String generateEmailwithTimeStamp() {

		Date date = new Date();
		String EmailDate = date.toString().replace(" ", "_").replace(":", "_");
		return "sk" + EmailDate + "@gmail.com";
	}
	
	
	//----Method to retrieve data from excel file-- Important code
	public static Object[][] getTestDataFromExcel(String sheetname) {
				
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\LoginTestData.xlsx");
		XSSFWorkbook workbook = null;
		
		try {
		FileInputStream fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetname);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object [][] data = new Object [rows][cols];
		
		for(int i=0;i<rows;i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j =0;j<cols;j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING :
					data[i][j] = cell.getStringCellValue();break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());break;
				case BOOLEAN :
					data[i][j] = cell.getBooleanCellValue();break;
				default:
					break;
				}
			}
		}
		
		return data;
	}
	//---Excel data retrieve method ends here
	
	public static String captureScreenshot(WebDriver driver,String testName) {
		
		TakesScreenshot screenshot = null;
		
		
		screenshot = (TakesScreenshot)driver;
		
		File srcScreenShot=screenshot.getScreenshotAs(OutputType.FILE);
		String screenShotDestinationPath=System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".png";
		
		try {
			FileUtils.copyFile(srcScreenShot, new File(screenShotDestinationPath));
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		return screenShotDestinationPath;
		
	}
	
	
	
}
