package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pageobjects.Accountpage;
import com.tutorialsninja.qa.pageobjects.Homepage;
import com.tutorialsninja.qa.pageobjects.Loginpage;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends BaseClass{

	public WebDriver driver;
	
	//---------------------------
	@BeforeMethod
	public void setup() throws IOException {
		
		loadPropertiesFiles();
		driver = InitializeBrowserAndGetURL(prop.getProperty("browserName"));
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.clickOnLoginOption();

	}

	//---------------------------
	@AfterMethod
	public void tearDown() {
	
		driver.quit();
	}

	//-----------------------
	@Test(priority=1)
	public void verifyLoginWithoutPassingAnyData() throws InterruptedException {

		Loginpage loginpage = new Loginpage(driver);
		loginpage.clickLoginButton();
		Thread.sleep(3000);
		String ActualMessage = loginpage.getLoginErrorMessageText();
		Assert.assertEquals(ActualMessage, "Warning: No match for E-Mail Address and/or Password.", "No Error Message");
	}

	//-----------------------
	@Test(priority=2,dataProvider="validLoginData")
	public void verifyLoginByGivingValidDataofRegisteredUser(String email,String password) throws InterruptedException {

		Loginpage loginpage = new Loginpage(driver);//---initializing login page elements
		loginpage.enterEmail(email);
		loginpage.enterPassword(password);
		loginpage.clickLoginButton();
		Thread.sleep(3000);
		Accountpage accountpage = new Accountpage(driver);//--intialize accountpage
		String ActualTitletoBePresent = accountpage.getAccountPageTitleText();
		Assert.assertEquals(ActualTitletoBePresent, "My Account", "Expected page not found");
	}
	
	@DataProvider(name="validLoginData")
	public Object[][] supplyTestData() {
		
		Object[][] data= Utilities.getTestDataFromExcel("LoginData");
		
		return data;
		
	}

}
