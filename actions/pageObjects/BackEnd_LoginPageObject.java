package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.backend.HomePageUI;
import liveguru.backend.LoginPageUI;
import liveguru.frontend.AbstractPageUI;

public class BackEnd_LoginPageObject extends AbstractPage {
	public BackEnd_LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public boolean isLoginFormDisplayed() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
	}


}
