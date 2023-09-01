package com.tutorialsninja.qa.base;

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class BaseClass {

	public WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	//--Method to load the properties files, example, config.properties, testdata.properties likewise it is used in all framrworks
	public void loadPropertiesFiles() throws IOException {
				
		//---This is for testdata.properties file
		dataProp=new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		FileInputStream fis2 = new FileInputStream(dataPropFile);
		dataProp.load(fis2);
		
		//--This is for config.properties file
		prop = new Properties();		
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
		
		}

	//--This is common method used in baseclass bcoz it will b common to all tests so written in base
	public WebDriver InitializeBrowserAndGetURL(String BrowserName) {

		// here switch case is used instead of if else if statements for convinience
		switch (BrowserName) {

		case "chrome":	driver = new ChromeDriver();	break;
		case "firefox":		driver = new FirefoxDriver();		break;
		case "edge":		driver = new EdgeDriver();		break;
		default:		break;
		
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait_time));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;
		//InitializeBrowserandgetURL method ends here
	}

}
