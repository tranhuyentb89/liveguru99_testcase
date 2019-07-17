package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryManage;
import liveguru.backend.SaleOrderPageUI;
import liveguru.frontend.AbstractPageUI;

public class BackEndSalePageObject extends AbstractPage{
	public BackEndSalePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public void selectItemInActions() {
		selectCustomDropdown(driver, SaleOrderPageUI.ACTIONS_DROPDOWN_PARENT_XPATH, SaleOrderPageUI.ACTIONS_DROPDOWN_CHILD_XPATH, "Print Invoices");
	}

	public boolean isErrorMessageDisplayed(WebDriver driver, String dynamicValue) {
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE_LINK_TEXT_HEADER , dynamicValue);
	}

	public void selectInViewNumber(String viewNumber) {
		selectCustomDropdown(driver, SaleOrderPageUI.VIEW_DROPDOWN_PARENT_XPATH	, SaleOrderPageUI.VIEW_DROPDOWN_CHILD_XPATH, viewNumber);
		sleepInSeconds(1000);
	}
	
	public int countRowOfTable() {
		List<WebElement> element = driver.findElements(By.xpath(SaleOrderPageUI.ROW_OF_TABLE));
		return element.size();
	}

}
