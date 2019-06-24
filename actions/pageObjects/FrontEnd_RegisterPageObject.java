package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import liveguru.frontend.HomePageUI;
import liveguru.frontend.RegisterPageUI;

public class FrontEnd_RegisterPageObject extends AbstractPage{
	public FrontEnd_RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public boolean isRegisterFormDisplayed() {
		waitForElementVisible(driver, HomePageUI.REGISTER_FORM);
		return isControlDisplayed(driver, HomePageUI.REGISTER_FORM);
	}

	public void clickToRegisterButton() {
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public Object getPageTitle() {
		return getTextElement(driver, RegisterPageUI.DASHBOARD_PAGE_TITLE);
	}
}
