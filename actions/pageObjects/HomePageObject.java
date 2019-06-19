package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;
import liveguru.frontend.homePageUI;

public class HomePageObject extends AbstractPage{
	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
	public RegisterPageObject clickToRegisterButton(WebDriver driver2, String fieldName) {
		clickToElement(driver, AbstractPageUI.REGISTER_LINK, fieldName);
		return PageFactoryManage.getRegisterPage(driver);
	}
	
	public void clickToAccountButton() {
		clickToElement(driver, homePageUI.ACCOUNT_LINK);
	}

}
