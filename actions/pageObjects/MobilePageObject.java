package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;

public class MobilePageObject extends AbstractPage {
	public MobilePageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}
	WebDriver driver;
}
