package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class FrontEnd_MobilePageObject extends AbstractPage {
	public FrontEnd_MobilePageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}
	WebDriver driver;
}
