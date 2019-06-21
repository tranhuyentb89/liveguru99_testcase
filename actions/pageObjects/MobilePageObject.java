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
	public String getPriceOfProductAtList() {
		return getTextElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_PRICE, "Sony Xperia", "product-price-1");
	}
	public void clickToProductToViewDetail() {
		clickToElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_IMAGE_TO_CLICK, "Sony Xperia", "Xperia");
	}
	public String getPriceOfProductAtDetail() {
		return getTextElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_PRICE_DETAIL, "Sony Xperia");
	}
	public CheckOutCartPageObject clickToAddToCardButton(WebDriver driver, String values) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_ADD_TO_CART_BUTTON, values);
		return PageFactoryManage.getCheckOutCartPage(driver);
	}
}
