package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AccountInformationPageObject;
import pageObjects.CheckOutCartPageObject;
import pageObjects.MobilePageObject;
import pageObjects.RegisterPageObject;

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
	

}
