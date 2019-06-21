package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;

public class AccountInformationPageObject extends AbstractPage {
	public AccountInformationPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public MobilePageObject clickToMobileButton(String fielName) {
		waitForElementVisible(driver, liveguru.frontend.AbstractPageUI.DYNAMIC_MENU_TAB, fielName);
		clickToElement(driver, liveguru.frontend.AbstractPageUI.DYNAMIC_MENU_TAB, fielName);
		return PageFactoryManage.getMobilePage(driver);
	}
}
