package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.base.Base;
import com.tutorialsninja.pageobjects.AccountPage;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.LoginPage;
import com.tutorialsninja.utility.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage= new HomePage(driver);
		//homePage.clickOnMyAccount();
		//loginPage =homePage.selectLoginOption();
		loginPage=homePage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		//loginPage.enterEmailAddress(email);
		//loginPage.enterPassword(password);
		//AccountPage accountPage =loginPage.clickOnLoginButton();
		
		AccountPage accountPage=loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Edit your Account Information option is not displayed");
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		//loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		//loginPage.clickOnLoginButton();
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		//String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning Message is not displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		//loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		//loginPage.enterPassword(prop.getProperty("validPassword"));
		//loginPage.clickOnLoginButton();
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		//loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		//loginPage.clickOnLoginButton();
		
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
	}

	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		loginPage.clickOnLoginButton();
	
		String actualWarningMessage=loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
	}
}
