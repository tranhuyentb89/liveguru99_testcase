package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import liveguru.backend.HomePageUI;
import liveguru.frontend.AbstractPageUI;

public class BackEnd_LoginPageObject extends AbstractPage {
	public BackEnd_LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public void clickToLoginButton(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON , fieldName);
	}

	public void clickToCloseInCommingMessage() {
		waitForElementVisible(driver, HomePageUI.INCOMMING_MASSAGE);
		clickToElementByJS(driver, HomePageUI.INCOMMING_MASSAGE);
	}
}
