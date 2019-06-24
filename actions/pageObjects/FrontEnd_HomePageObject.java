package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;
import liveguru.frontend.HomePageUI;

public class FrontEnd_HomePageObject extends AbstractPage{
	public FrontEnd_HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
	public FrontEnd_RegisterPageObject clickToRegisterButton(WebDriver driver2, String fieldName) {
		clickToElement(driver, AbstractPageUI.REGISTER_LINK, fieldName);
		return PageFactoryManage.getRegisterPage(driver);
	}
	
	public void clickToAccountButton() {
		clickToElement(driver, HomePageUI.ACCOUNT_LINK);
	}

	public FrontEnd_AdvancedSearchPageObject clickToAdvancedSearchLink() {
		clickToElement(driver, HomePageUI.ADVANCED_SEARCH_LINK);
		return PageFactoryManage.getAdvancedSearchPage(driver);
	}

}
