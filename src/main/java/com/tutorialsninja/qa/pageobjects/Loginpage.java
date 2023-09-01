package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	WebDriver driver;
	
	//this findby is creating page objects under page factory 
	@FindBy(id="input-email")
	private WebElement inputEmailField;
	
	@FindBy(id="input-password")
	private WebElement inputPasswordField;
	
	@FindBy(xpath="//input[@type='submit'][@value='Login']")
	private WebElement loginSubmitButton;
	
	@FindBy(xpath="//*[@id=\"account-login\"]/div[1]")
	private WebElement loginErrorMessage;
	
	//---Constructor creation to initizlie the driver and the elements of this class
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail(String emailText) {
		inputEmailField.sendKeys(emailText);
	}
	
	public void enterPassword(String password) {
		inputPasswordField.sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginSubmitButton.click();
	}
	
	public String getLoginErrorMessageText() {
		return loginErrorMessage.getText();
	}
	
}
