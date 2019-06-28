package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryManage;
import liveguru.backend.SaleOrderPageUI;
import liveguru.frontend.AbstractPageUI;

public class BackEndSaleOrderPageObject extends AbstractPage{
	public BackEndSaleOrderPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public void selectItemInActions() {
		selectCustomDropdown(driver, SaleOrderPageUI.ACTIONS_DROPDOWN_PARENT_XPATH, SaleOrderPageUI.ACTIONS_DROPDOWN_CHILD_XPATH, "Print Invoices");
	}

	public boolean isErrorMessageDisplayed(WebDriver driver, String dynamicValue) {
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE_LINK_TEXT , dynamicValue);
	}

}
