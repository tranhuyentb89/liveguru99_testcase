package liveguru.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.Constants;
import liveguru.frontend.RegisterToSystem;
import liveguru.frontend.TVPageUI;
import pageObjects.BackEndSaleOrderPageObject;
import pageObjects.BackEnd_HomePageObject;
import pageObjects.BackEnd_LoginPageObject;
import pageObjects.FrontEnd_TVPageObject;

public class Backend_Testcase extends AbstractTest {
	String username, password, msgToShareWishList, backendUrl;
	WebDriver driver;

	BackEnd_LoginPageObject backendloginPage;
	BackEnd_HomePageObject backendHomePage;
	BackEndSaleOrderPageObject saleOrderPage;
	FrontEnd_TVPageObject tvPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browserName) {
		driver = openBrowser(browserName);
		backendloginPage = new BackEnd_LoginPageObject(driver);
		backendUrl = backendloginPage.getCurrentUrl(driver);
		username = "user01";
		password = "guru99com";
		msgToShareWishList = "Good";
	}

	@Test
	public void TC_01_VerifyInvoiceCanPrint() {
		backendloginPage.inputToDynamicTexboxField(driver, username, "username");
		backendloginPage.inputToDynamicTexboxField(driver, password, "login");
		backendHomePage = backendloginPage.clickToLoginButton(driver, "Login");
		backendHomePage.clickToCloseInCommingMessage();
		saleOrderPage = backendHomePage.clickToSubMenu(driver, "Sales", "Orders");
		saleOrderPage.selectItemInStatusBackEnd(driver, "Canceled");
//		saleOrderPage.clickToDynamicButton(driver, "Search");
//		saleOrderPage.selectFirstOrderToPrint(driver);
//		saleOrderPage.selectItemInActions();
//		saleOrderPage.sleepInSeconds(5000);
//		saleOrderPage.clickToDynamicButton(driver, "Submit");
//		verifyTrue(saleOrderPage.isErrorMessageDisplayed(driver, "There are no printable documents related to selected orders."));
//		saleOrderPage.selectItemInStatusBackEnd(driver, "Complete");
//		saleOrderPage.clickToDynamicButton(driver, "Search");
//		saleOrderPage.selectFirstOrderToPrint(driver);
//		saleOrderPage.selectItemInActions();
//		saleOrderPage.clickToDynamicButton(driver, "Submit");
//		verifyTrue(saleOrderPage.isFileDownloaded("/Downloads", "invoice"));
//		saleOrderPage.sleepInSeconds(50000);
	}
	@Test
	public void TC_02_VerifyProductReviewMechanism() {
		tvPage = saleOrderPage.openReviewFrontEndPage();
		tvPage.clickToProductToViewDetail(driver, "Samsung LCD");
		tvPage.clickToRatingLink("Add Your Review");
		tvPage.inputToDynamicTextAreaField(driver,msgToShareWishList , "review_field");
		tvPage.inputToDynamicTexboxField(driver, "kaka", "summary_field");
		tvPage.inputToDynamicTexboxField(driver, "huyen", "nickname_field");
		tvPage.clickToDynamicButton(driver, "Submit Review");
		backendHomePage = tvPage.openBackEndPage(driver);
		backendHomePage.sleepInSeconds(5000);

	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
