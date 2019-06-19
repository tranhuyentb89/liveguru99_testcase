package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AccountInformationPageObject;
import pageObjects.RegisterPageObject;

public class PageFactoryManage {
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static AccountInformationPageObject getAccountInfoPage(WebDriver driver) {
		return new AccountInformationPageObject(driver);
	}
	

}
