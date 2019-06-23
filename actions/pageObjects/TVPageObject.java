package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import liveguru.frontend.AbstractPageUI;

public class TVPageObject extends AbstractPage{
	public TVPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public void clickToRatingLink(String values) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_RATING_LINK, values);
	}
}
