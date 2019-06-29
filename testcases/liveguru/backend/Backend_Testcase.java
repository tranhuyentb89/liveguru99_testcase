package liveguru.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManage;
import liveguru.frontend.AbstractPageUI;
import pageObjects.BackEndSalePageObject;
import pageObjects.BackEnd_HomePageObject;
import pageObjects.BackEnd_LoginPageObject;
import pageObjects.BackEnd_PendingReviewPageObject;
import pageObjects.FrontEnd_TVPageObject;

public class Backend_Testcase extends AbstractTest {
	String username, password, msgToShareWishList, backendUrl, nickNameBackend, summaryOfReviewBackend, reviewDetailBackend;
	WebDriver driver;

	BackEnd_LoginPageObject backendloginPage;
	BackEnd_HomePageObject backendHomePage;
	BackEndSalePageObject salePage;
	FrontEnd_TVPageObject tvPage;
	BackEnd_PendingReviewPageObject pendingReviewPage;

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

	//@Test
	public void TC_01_VerifyInvoiceCanPrint() {
		log.info("TC 01 - Verify Invoice: Step 01 :Input to username/ password to login");
		backendloginPage.inputToDynamicTexboxField(driver, username, "username");
		backendloginPage.inputToDynamicTexboxField(driver, password, "login");
		
		log.info("TC 01 - Verify Invoice:  Step 02 Click to login button");
		backendHomePage = backendloginPage.clickToLoginButton(driver, "Login");
		
		log.info("TC 01 - Verify Invoice:  Step 03 - Click to close incomming msg");
		backendHomePage.clickToCloseInCommingMessage();
		
		log.info("TC 01 - Verify Invoice: Step 04: Open Order page");
		backendHomePage.openMultiplePage(driver, "Sales", "Orders");
		salePage = PageFactoryManage.getBackendSalePage(driver);
		
		log.info("TC 01 - Verify Invoice: Step 05: Select canceled invoice");
		salePage.selectItemInStatusBackEnd(driver, "Canceled");
		
		log.info("TC 01 - Verify Invoice: Step 06: click Search button");
		salePage.clickToDynamicButton(driver, "Search");
		
		log.info("TC 01 - Verify Invoice: Input to username/ password to login");
		salePage.selectFirstItemInList(driver, AbstractPageUI.DYNAMIC_CHECKBOX, "order_ids");
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
	//@Test
	public void TC_02_VerifyProductReviewMechanism() {
		log.info("TC 01 -VerifyProductReviewMechanism: Open Detail product page");
		tvPage = (FrontEnd_TVPageObject) salePage.openReviewFrontEndPage(driver);
		tvPage.clickToProductToViewDetail(driver, "Samsung LCD");
		
		log.info("TC 02 -VerifyProductReviewMechanism: Click to Add your review button");
		tvPage.clickToRatingLink("Add Your Review");
		
		log.info("TC 02 -VerifyProductReviewMechanism: Input to all field and click submit");
		tvPage.inputToDynamicTextAreaField(driver,msgToShareWishList , "review_field");
		tvPage.inputToDynamicTexboxField(driver, "Test input to summary field", "summary_field");
		tvPage.inputToDynamicTexboxField(driver, "huyen", "nickname_field");
		tvPage.clickToDynamicButton(driver, "Submit Review");
		
		log.info("TC 03 -VerifyProductReviewMechanism: Open backend page");
		backendHomePage = (BackEnd_HomePageObject) tvPage.openBackEndPage(driver);
		backendHomePage.sleepInSeconds(5);
		
		log.info("TC 04 -VerifyProductReviewMechanism: Click to Pending review submenu");
		backendHomePage.clickToSubOfSubMenu(driver, "Catalog", "Reviews and Ratings", "Customer Reviews", "Pending Reviews");
		pendingReviewPage = PageFactoryManage.getPendingReviewPage(driver);
		
		log.info("TC 05 -VerifyProductReviewMechanism: Sort By ID");
		pendingReviewPage.clickToHeadingButton(driver, "ID");
		
		log.info("TC 05 -VerifyProductReviewMechanism: Click to Edit button");
		pendingReviewPage.clickToButtonInActionColumn(driver, "Edit");
		nickNameBackend = pendingReviewPage.getValueOffAttributeInTextbox(driver, "value", "nickname");
		summaryOfReviewBackend = pendingReviewPage.getValueOffAttributeInTextbox(driver, "value", "title").toUpperCase();
		reviewDetailBackend = pendingReviewPage.getTextInAreaBox(driver, "detail");
		
		System.out.println(summaryOfReviewBackend);
		
		System.out.println("Day la summary of review backend :"+summaryOfReviewBackend);

		log.info("TC 05 -VerifyProductReviewMechanism: Change status from Pending to Approved");
		pendingReviewPage.selectItemInDynamicDropdown(driver, "Approved", "status_id", "status_id");

		log.info("TC 06 -VerifyProductReviewMechanism: Click To Save Reviews button");
		pendingReviewPage.clickToDynamicButtonBackEndPage(driver, "Save Review");
		
		log.info("TC 07 -VerifyProductReviewMechanism: Go to frontend page");
		tvPage = (FrontEnd_TVPageObject) pendingReviewPage.openReviewFrontEndPage(driver);
		tvPage.clickToProductToViewDetail(driver, "Samsung LCD");
		tvPage.clickToLinkText(driver, "Reviews");
		
		log.info("TC 08 -VerifyProductReviewMechanism: Verify Reivews comment is shown");
		verifyTrue(tvPage.getTextInReviewTab(driver, summaryOfReviewBackend));
	}

	@Test
	public void TC_03_VerifySortWorkingCorrectly() {
		backendloginPage.openUrl(driver, backendUrl);
		if(backendloginPage.isLoginFormDisplayed()) {
			log.info("TC 01 - Verify Invoice: Step 01 :Input to username/ password to login");
			backendloginPage.inputToDynamicTexboxField(driver, username, "username");
			backendloginPage.inputToDynamicTexboxField(driver, password, "login");
			
			log.info("TC 01 - Verify Invoice:  Step 02 Click to login button");
			backendHomePage = backendloginPage.clickToLoginButton(driver, "Login");
			
			log.info("TC 01 - Verify Invoice:  Step 03 - Click to close incomming msg");
			backendHomePage.clickToCloseInCommingMessage();
		}
		log.info("TC 01 - Verify Invoice: Step 04: Open Sale Invoice Page");
		backendHomePage.openMultiplePage(driver, "Sales", "Invoices");
		salePage = PageFactoryManage.getBackendSalePage(driver);
		

	}
	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
