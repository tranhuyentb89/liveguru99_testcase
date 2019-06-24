package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;

public class FrontEnd_MobilePageObject extends AbstractPage {
	public FrontEnd_MobilePageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}
	WebDriver driver;
}
