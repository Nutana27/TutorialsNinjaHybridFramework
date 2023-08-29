package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.base.Base;
import com.tutorialsninja.pageobjects.AccountSuccessPage;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.RegisterPage;
import com.tutorialsninja.utility.Utilities;


public class RegisterTest extends Base{
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAccountWithMandatoryFields() throws Exception {
		
		/*registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();*/
		Thread.sleep(1000);
		accountSuccessPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retreiveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page is not displayed");
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() throws Exception {
		Thread.sleep(1000);
		accountSuccessPage=registerPage.registerWithAllMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retreiveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page is not displayed");
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		registerPage.registerWithAllMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate Email address is not displayed ");
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
	
		registerPage.clickOnContinueButton();
		
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy Warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(),dataProp.getProperty("firstNameWarning"), "First Name warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveLastNameWarning(),dataProp.getProperty("lastNameWarning"), "Last Name warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveEmailWarning(),dataProp.getProperty("EmailWarning"), "E-Mail  warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveTelephoneWarning(),dataProp.getProperty("telephoneWarning"), "Telephone warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrievePasswordWarning(),dataProp.getProperty("passwordWarning"), "Password warning message is not displayed");

	}
}
