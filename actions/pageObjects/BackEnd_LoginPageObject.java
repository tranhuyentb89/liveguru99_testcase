package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.backend.HomePageUI;
import liveguru.frontend.AbstractPageUI;

public class BackEnd_LoginPageObject extends AbstractPage {
	public BackEnd_LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public BackEnd_HomePageObject clickToLoginButton(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON , fieldName);
		return PageFactoryManage.getBackendHomePage(driver);
	}

}
