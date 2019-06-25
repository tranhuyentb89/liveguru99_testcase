package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import liveguru.backend.HomePageUI;

public class BackEnd_HomePageObject extends AbstractPage {
	public BackEnd_HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public void clickToCloseInCommingMessage() {
		waitForElementVisible(driver, HomePageUI.INCOMMING_MASSAGE);
		clickToElementByJS(driver, HomePageUI.INCOMMING_MASSAGE);
	}
}