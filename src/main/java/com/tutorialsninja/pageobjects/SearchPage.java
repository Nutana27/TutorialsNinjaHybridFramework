package com.tutorialsninja.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean displayStatusOfHPValidProduct() {
		Boolean dispalyStatus=validHPProduct.isDisplayed();
		return dispalyStatus;
	}
	
	public String retreiveNoProductMessageText() {
		String noProductMessageText=noProductMessage.getText();
		return noProductMessageText;
	}
}
