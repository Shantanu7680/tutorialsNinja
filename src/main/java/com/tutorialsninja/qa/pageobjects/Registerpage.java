package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registerpage {

	WebDriver driver;
	
	//--Page elements
	@FindBy(xpath="//*[@id=\"content\"]/h1")
	private WebElement registerYourAccountGetText;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailInputField;
	
	@FindBy(id="input-telephone")
	private WebElement inputTelephoneField;
	
	@FindBy(id="input-password")
	private WebElement inputPasswordField;
	
	@FindBy(id="input-confirm")
	private WebElement inputConfirmPasswordField;
	
	@FindBy(xpath="//input[@name='newsletter'][@value=1]")
	private WebElement newsletterYesRadioButton;
	
	@FindBy(xpath="//input[@name='agree'][@value=1]")
	private WebElement privacyPolicyCheckbox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButtonRegisterPage;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement accountCreatedSuccessMessageTitle;
	
	
	//----Page Constructor
	public Registerpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//---Page action methods
	public String registerYourAccountGetText() {
		return registerYourAccountGetText.getText();
	}
	
	public void inputFirstNameinField(String firstname) {
		firstNameField.sendKeys(firstname);
	}

	public void inputLastNameinField(String lastname) {
		lastNameField.sendKeys(lastname);
	}
	
	public void inputEmailInTheField(String emailAdd) {
		emailInputField.sendKeys(emailAdd);
	}
	
	public void inputTelephoneIntheField(String telephone) {
		inputTelephoneField.sendKeys(telephone);
	}
	
	public void inputPasswordInTheField(String password) {
		inputPasswordField.sendKeys(password);
	}
	
	public void inputConfirmPasswordIntheField(String password) {
		inputConfirmPasswordField.sendKeys(password);
	}
	
	public void clickOnNewsletterYesRadioButton() {
		newsletterYesRadioButton.click();
	}
	
	public void clickOnPrivacyPolicyCheckbox() {
		privacyPolicyCheckbox.click();
	}
	
	public void clickOnContinueButtonRegisterPage() {
		continueButtonRegisterPage.click();
	}
	
	public String verifyAccountCreatedSuccessMessage() {
		return accountCreatedSuccessMessageTitle.getText();
	}
}
