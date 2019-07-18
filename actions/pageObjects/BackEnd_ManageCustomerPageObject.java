package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class BackEnd_ManageCustomerPageObject extends AbstractPage{
	public BackEnd_ManageCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	WebDriver driver;
}
