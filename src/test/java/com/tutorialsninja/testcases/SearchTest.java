package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.base.Base;
import com.tutorialsninja.pageobjects.HomePage;
import com.tutorialsninja.pageobjects.SearchPage;

public class SearchTest extends Base{
	
	SearchPage searchPage;
	HomePage homePage;
	
	
	//Search product
	public SearchTest() {
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage= new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=-1)
	public void verifySearchWithValidProduct() {
		searchPage= homePage.searchForProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid Product HP is not displayed in the search result");
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		searchPage=homePage.searchForProduct(dataProp.getProperty("InvalidProduct"));
		Assert.assertEquals(searchPage.retreiveNoProductMessageText(),"abcd", "No Product message in search result is not displayed");
		//Assert.assertEquals(searchPage.retreiveNoProductMessageText(), dataProp.getProperty("noProductTextSearchResult"), "No Product message in search result is not displayed");
	}
	
	@Test(priority=3, dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		searchPage=homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retreiveNoProductMessageText(), dataProp.getProperty("noProductTextSearchResult"), "No Product message in search result is not displayed");
	}
}
