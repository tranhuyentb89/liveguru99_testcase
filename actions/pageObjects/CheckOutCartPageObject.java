package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;
import liveguru.frontend.CheckOutCartPageUI;

public class CheckOutCartPageObject extends AbstractPage{
	public CheckOutCartPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	WebDriver driver;
	public void inputToDiscountTextBox(String coupon) {
		sendKeyToElement(driver, CheckOutCartPageUI.DISCOUNT_CODE_TEXTBOX, coupon);
	}
	public void clickToApplyButton() {
		clickToElement(driver, CheckOutCartPageUI.APPLY_DISCOUNT_BUTTON);
	}
	public Object getDiscountAmount() {
		return getTextElement(driver, CheckOutCartPageUI.DISCOUNT_AMOUNT_LABEL);
	}
	public Object getGrandTotalAmount() {
		return getTextElement(driver, CheckOutCartPageUI.GRAND_AMOUNT_LABEL);
	}
	public void clearQuantityTextBox() {
		clearValue(driver, CheckOutCartPageUI.QUANITY_TEXTBOX);
	}
	public void inputToQuantityTextbox(String quantityMax) {
		sendKeyToElement(driver, CheckOutCartPageUI.QUANITY_TEXTBOX, quantityMax);
	}
	public Object getErrorMessage() {
		return getTextElement(driver, CheckOutCartPageUI.ERROR_MESSAGE_LABEL);
	}
	public void closeWindow() {
		String parentWindow = driver.getWindowHandle();
		System.out.println("parent window is " + parentWindow);
		closeAllWithoutParentWindows(driver, parentWindow);
	}
	public TVPageObject clickToTVButton(String fielName) {
		waitForElementVisible(driver, liveguru.frontend.AbstractPageUI.DYNAMIC_MENU_TAB, fielName);
		clickToElement(driver, liveguru.frontend.AbstractPageUI.DYNAMIC_MENU_TAB, fielName);
		return PageFactoryManage.getTVPage(driver);
	}
	public Object getFlateRateAmount() {
		return getTextElement(driver, CheckOutCartPageUI.FLAT_RATE);
	}
	
	public void clickToFlateRate() {
		clickToElement(driver, CheckOutCartPageUI.FLAT_RATE);
	}
	public void clickToCountinueButtonAtShippingMethod(String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_SHIPPING_PAYMENT_BUTTON_CONTINUE, fieldName);
	}
	
	public String getOrderCode() {
		return getTextElement(driver, CheckOutCartPageUI.ORDER_CODE);
	}
	public HomePageObject clickToLogo() {
		clickToElementByJS(driver, AbstractPageUI.HOME_PAGE_LOGO);
		return PageFactoryManage.getHomePage(driver);
	}
}
