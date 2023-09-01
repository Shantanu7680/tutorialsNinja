package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	WebDriver driver;
	
	//-- these are called as objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginButtonOption;
	
	@FindBy(linkText="Register")
	private WebElement registerButtonOption;
	
	@FindBy(xpath="//button[@onclick=\"cart.add('43');\"]")
	private WebElement macbookAddToCartButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement addToCartSuccessMessage;
	
	@FindBy(xpath="//button[@type='button'][contains(@class,'dropdown-toggle')]")
	private WebElement cartButton;
	
	@FindBy(xpath="//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr/td[2]/a")
	private WebElement cartAddedItem;
	
	@FindBy(xpath="//button[@type='button'][@title='Remove']")
	private WebElement itemRemoveButtonCart;
	
	@FindBy(xpath="//*[@id=\"cart\"]/ul/li/p")
	private WebElement emptyShoppingCartMessage;
	
	
	//--Constructor to initialize
	public Homepage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//-- creating methods or actions 
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public void clickOnLoginOption() {
		loginButtonOption.click();
	}
	
	public void clickOnRegisterOption() {
		registerButtonOption.click();
	}
	
	public void addMacbookToCart() {
		macbookAddToCartButton.click();
	}
	
	public String verifyaddToCartSuccesMessage() {
		return addToCartSuccessMessage.getText();
	}
	
	public void clickOnTheCartButton() {
		cartButton.click();
	}
	
	public String getTheTextOfAddedItemInCart() {
		return cartAddedItem.getText();
	}
	
	public void clickOntheItemRemoveButtonInCart() {
		itemRemoveButtonCart.click();
	}
	
	public String getTextofEmptyShoppingCartMessage() {
		return emptyShoppingCartMessage.getText();
	}
}
