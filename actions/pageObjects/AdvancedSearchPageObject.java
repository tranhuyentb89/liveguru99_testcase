package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;
import liveguru.frontend.AdvancedSearchPageUI;

public class AdvancedSearchPageObject extends AbstractPage {
	public AdvancedSearchPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public String getPriceAfterSaleOff(WebDriver driver, String values) {
		return getTextElement(driver, AbstractPageUI.PRICE_AFTER_SALE, values);
	}

	public AdvancedSearchResultPageObject clickToSearchButton() {
		clickToElement(driver, AdvancedSearchPageUI.SEARCH_BUTTON);
		return PageFactoryManage.getAdvancedSearchResultPage(driver);
	}
}
