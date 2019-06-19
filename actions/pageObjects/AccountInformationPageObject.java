package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class AccountInformationPageObject extends AbstractPage {
	public AccountInformationPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
}
