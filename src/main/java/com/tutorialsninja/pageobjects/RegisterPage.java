package com.tutorialsninja.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;

	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String confPasswordText) {
		confirmPasswordField.sendKeys(confPasswordText);
	}
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	public void selectYesNewsletterOption() {
		yesNewsLetterOption.click();
	}*/
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(passwordText);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(passwordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	
	
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	public String retrieveFirstNameWarning() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	public String retrieveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retrieveEmailWarning() {
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}
	public String retrieveTelephoneWarning() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}
	public String retrievePasswordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}

}




