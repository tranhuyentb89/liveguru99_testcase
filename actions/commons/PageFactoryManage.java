package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AccountInformationPageObject;
import pageObjects.AdvancedSearchPageObject;
import pageObjects.AdvancedSearchResultPageObject;
import pageObjects.CheckOutCartPageObject;
import pageObjects.HomePageObject;
import pageObjects.MobilePageObject;
import pageObjects.RegisterPageObject;
import pageObjects.TVPageObject;

public class PageFactoryManage {
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static AccountInformationPageObject getAccountInfoPage(WebDriver driver) {
		return new AccountInformationPageObject(driver);
	}

	public static MobilePageObject getMobilePage(WebDriver driver) {
		return new MobilePageObject(driver);
	}

	public static CheckOutCartPageObject getCheckOutCartPage(WebDriver driver) {
		return new CheckOutCartPageObject(driver);
	}

	public static TVPageObject getTVPage(WebDriver driver) {
		return new TVPageObject(driver);
	}

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static AdvancedSearchPageObject getAdvancedSearchPage(WebDriver driver) {
		return new AdvancedSearchPageObject(driver);
	}

	public static AdvancedSearchResultPageObject getAdvancedSearchResultPage(WebDriver driver) {
		return new AdvancedSearchResultPageObject(driver);
	}
	

}
