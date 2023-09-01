package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pageobjects.Homepage;

public class AddtoCart extends BaseClass {
	
	
	public WebDriver driver;
	Homepage homepage;
	
	//------this setup method is used for every testclass
	@BeforeMethod
	public void setup() throws IOException {
		
		loadPropertiesFiles();
		driver = InitializeBrowserAndGetURL(prop.getProperty("browserName"));
		
	}
	
	//--------this teardown method is also used for every testclass
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	public void verifyAddToCartSuccessMessage() {
		
		homepage=new Homepage(driver);
		homepage.addMacbookToCart();
		String ActualSuccessMessage = homepage.verifyaddToCartSuccesMessage();
		Assert.assertEquals(ActualSuccessMessage, "Success: You have added MacBook to your shopping cart!");
		//there is a typo in the successmessage so this test fails
	}
	
	@Test
	public void verifyItemAddedtoCart() throws InterruptedException {
		
		homepage=new Homepage(driver);
		homepage.addMacbookToCart();
		Thread.sleep(2000);
		homepage.clickOnTheCartButton();
		Thread.sleep(3000);
		String ActualAddedItem = homepage.getTheTextOfAddedItemInCart();
		Assert.assertEquals(ActualAddedItem, "MacBook");
	}
	
	@Test
	public void verifyItemRemoveFromCart() throws InterruptedException {
	
		homepage=new Homepage(driver);
		homepage.addMacbookToCart();
		Thread.sleep(1000);
		homepage.clickOnTheCartButton();
		homepage.clickOntheItemRemoveButtonInCart();
		Thread.sleep(100);
		homepage.clickOnTheCartButton();
		Thread.sleep(1000);
		String AfterRemoveMessage = homepage.getTextofEmptyShoppingCartMessage();
		Assert.assertEquals(AfterRemoveMessage, "Your shopping cart is empty!");
		
	}
	
}
