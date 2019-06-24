package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;

public class FrontEnd_AdvancedSearchResultPageObject extends AbstractPage{
	public FrontEnd_AdvancedSearchResultPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public String getPriceAfterSaleOff(WebDriver driver, String values) {
		return getTextElement(driver, AbstractPageUI.PRICE_AFTER_SALE, values);
	}
	
	public FrontEnd_AdvancedSearchPageObject backToAdvanceSearchPage() {
		back(driver);
		return PageFactoryManage.getAdvancedSearchPage(driver);
	}

}
