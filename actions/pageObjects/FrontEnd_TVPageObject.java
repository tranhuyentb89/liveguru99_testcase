package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;

public class FrontEnd_TVPageObject extends AbstractPage{
	public FrontEnd_TVPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public void clickToRatingLink(String values) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_RATING_LINK, values);
	}

	public BackEnd_HomePageObject openBackEndPage(WebDriver driver) {
		driver.get(Constants.BACK_END_URL);
		return PageFactoryManage.getBackendHomePage(driver);
	}

}
