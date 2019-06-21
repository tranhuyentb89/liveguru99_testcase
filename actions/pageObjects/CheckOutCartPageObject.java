package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
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
}
