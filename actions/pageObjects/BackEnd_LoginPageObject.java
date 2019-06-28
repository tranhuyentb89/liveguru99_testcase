package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageFactoryManage;
import liveguru.backend.HomePageUI;
import liveguru.frontend.AbstractPageUI;

public class BackEnd_LoginPageObject extends AbstractPage {
	public BackEnd_LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;


}
