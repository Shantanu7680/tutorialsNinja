package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Accountpage {

	WebDriver driver;
	
	
	@FindBy(xpath="//div[@id='content']/h2")
	private WebElement AccountPageTitleText;
	
	
	public Accountpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getAccountPageTitleText() {
		return AccountPageTitleText.getText();
	}
	
}
