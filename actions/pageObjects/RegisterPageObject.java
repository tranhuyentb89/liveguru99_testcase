package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import liveguru.frontend.homePageUI;
import liveguru.frontend.registerPageUI;

public class RegisterPageObject extends AbstractPage{
	public RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public boolean isRegisterFormDisplayed() {
		waitForElementVisible(driver, homePageUI.REGISTER_FORM);
		return isControlDisplayed(driver, homePageUI.REGISTER_FORM);
	}

	public void clickToRegisterButton() {
		clickToElement(driver, registerPageUI.REGISTER_BUTTON);
	}

	public Object getPageTitle() {
		return getTextElement(driver, registerPageUI.DASHBOARD_PAGE_TITLE);
	}
}
