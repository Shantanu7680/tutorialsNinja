package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pageobjects.Homepage;
import com.tutorialsninja.qa.pageobjects.Registerpage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends BaseClass {

	public WebDriver driver;

	@BeforeMethod
	public void setup() throws IOException {
		
		//we loading config file here with ref to method in baseclass so we can use its properties
		loadPropertiesFiles();
		driver = InitializeBrowserAndGetURL(prop.getProperty("browserName"));
		Homepage homepage = new Homepage(driver);//---Homepage initialized to get the methods
		homepage.clickOnMyAccount();
		homepage.clickOnRegisterOption();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test
	public void verifyRegisterButtonWorking() {

		Registerpage registerpage = new Registerpage(driver);//--registerpage initizlied to get the methods
		String actualTitle = registerpage.registerYourAccountGetText();
		Assert.assertEquals(actualTitle, "Register Account", "Navigated to the wrong page");
		driver.quit();
		
	}

	
	@Test
	public void verifyRegisterFormByGivingAllValidData() throws InterruptedException {

		Registerpage registerpage = new Registerpage(driver);
		registerpage.inputFirstNameinField(dataProp.getProperty("validFirstname"));
		registerpage.inputLastNameinField(dataProp.getProperty("validLastname"));
		registerpage.inputEmailInTheField(Utilities.generateEmailwithTimeStamp());
		registerpage.inputTelephoneIntheField(dataProp.getProperty("validTelephone"));
		registerpage.inputPasswordInTheField(dataProp.getProperty("validPassword"));
		registerpage.inputConfirmPasswordIntheField(dataProp.getProperty("validPassword"));
		registerpage.clickOnNewsletterYesRadioButton();
		registerpage.clickOnPrivacyPolicyCheckbox();
		registerpage.clickOnContinueButtonRegisterPage();
		Thread.sleep(1000);		
		String ActualTitle = registerpage.verifyAccountCreatedSuccessMessage();

		Assert.assertEquals(ActualTitle, "Your Account Has Been Created!", "Not Registered");

	}

	

}
