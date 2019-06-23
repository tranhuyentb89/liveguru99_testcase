package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import liveguru.frontend.AbstractPageUI;

public class AdvancedSearchResultPageObject extends AbstractPage{
	public AdvancedSearchResultPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public String getPriceAfterSaleOff(WebDriver driver, String values) {
		return getTextElement(driver, AbstractPageUI.PRICE_AFTER_SALE, values);
	}

}
