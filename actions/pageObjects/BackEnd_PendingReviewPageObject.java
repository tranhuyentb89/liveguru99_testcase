package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class BackEnd_PendingReviewPageObject extends AbstractPage {
	WebDriver driver;
	public BackEnd_PendingReviewPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

}
