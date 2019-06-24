package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.FrontEnd_AccountInformationPageObject;
import pageObjects.FrontEnd_AdvancedSearchPageObject;
import pageObjects.FrontEnd_AdvancedSearchResultPageObject;
import pageObjects.FrontEnd_CheckOutCartPageObject;
import pageObjects.FrontEnd_HomePageObject;
import pageObjects.FrontEnd_MobilePageObject;
import pageObjects.FrontEnd_RegisterPageObject;
import pageObjects.FrontEnd_TVPageObject;

public class PageFactoryManage {
	public static FrontEnd_RegisterPageObject getRegisterPage(WebDriver driver) {
		return new FrontEnd_RegisterPageObject(driver);
	}

	public static FrontEnd_AccountInformationPageObject getAccountInfoPage(WebDriver driver) {
		return new FrontEnd_AccountInformationPageObject(driver);
	}

	public static FrontEnd_MobilePageObject getMobilePage(WebDriver driver) {
		return new FrontEnd_MobilePageObject(driver);
	}

	public static FrontEnd_CheckOutCartPageObject getCheckOutCartPage(WebDriver driver) {
		return new FrontEnd_CheckOutCartPageObject(driver);
	}

	public static FrontEnd_TVPageObject getTVPage(WebDriver driver) {
		return new FrontEnd_TVPageObject(driver);
	}

	public static FrontEnd_HomePageObject getHomePage(WebDriver driver) {
		return new FrontEnd_HomePageObject(driver);
	}

	public static FrontEnd_AdvancedSearchPageObject getAdvancedSearchPage(WebDriver driver) {
		return new FrontEnd_AdvancedSearchPageObject(driver);
	}

	public static FrontEnd_AdvancedSearchResultPageObject getAdvancedSearchResultPage(WebDriver driver) {
		return new FrontEnd_AdvancedSearchResultPageObject(driver);
	}
	

}
