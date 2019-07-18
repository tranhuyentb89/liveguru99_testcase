package liveguru.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObjects.BackEndSalePageObject;
import pageObjects.BackEnd_HomePageObject;
import pageObjects.BackEnd_LoginPageObject;
import pageObjects.BackEnd_ManageCustomerPageObject;
import pageObjects.BackEnd_PendingReviewPageObject;
import pageObjects.FrontEnd_TVPageObject;

public class Backend_Testcase extends AbstractTest {
	String username, password, msgToShareWishList, backendUrl, nickNameBackend, summaryOfReviewBackend, reviewDetailBackend, searchID, searchName,
			searchEmail, searchTelephone, searchZip, searchCountry, searchState;
	WebDriver driver;

	BackEnd_LoginPageObject backendloginPage;
	BackEnd_HomePageObject backendHomePage;
	BackEndSalePageObject salePage;
	FrontEnd_TVPageObject tvPage;
	BackEnd_PendingReviewPageObject pendingReviewPage;
	BackEnd_ManageCustomerPageObject manageCustomerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browserName) {
		driver = openBrowser(browserName);
		backendloginPage = new BackEnd_LoginPageObject(driver);
		backendUrl = backendloginPage.getCurrentUrl(driver);
		username = "user01";
		password = "guru99com";
		msgToShareWishList = "Good";
		searchID = "31389";
		searchName = "Automation FC";
		searchEmail = "automationfc.vn@gmail.com";
		searchTelephone = "0123654789";
		searchZip = "550000";
		searchCountry = "Vietnam";
		searchState = "Cam Le";
	}

	// @Test
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
		salePage.selectFirstItemInDynamicList(driver, "order_ids");
		salePage.selectItemInActions();
		salePage.sleepInSeconds(5000);
		salePage.clickToDynamicButton(driver, "Submit");
		verifyTrue(salePage.isErrorMessageDisplayed(driver, "There are no printable documents related to selected orders."));
		salePage.selectItemInStatusBackEnd(driver, "Complete");
		salePage.clickToDynamicButton(driver, "Search");
		salePage.selectFirstItemInDynamicList(driver, "order_ids");
		salePage.selectItemInActions();
		salePage.clickToDynamicButton(driver, "Submit");
		salePage.sleepInSeconds(50000);
	}

	// @Test
	public void TC_02_VerifyProductReviewMechanism() {
		log.info("TC 01 -VerifyProductReviewMechanism: Open Detail product page");
		tvPage = (FrontEnd_TVPageObject) salePage.openReviewFrontEndPage(driver);
		tvPage.clickToProductToViewDetail(driver, "Samsung LCD");

		log.info("TC 02 -VerifyProductReviewMechanism: Click to Add your review button");
		tvPage.clickToRatingLink("Add Your Review");

		log.info("TC 02 -VerifyProductReviewMechanism: Input to all field and click submit");
		tvPage.inputToDynamicTextAreaField(driver, msgToShareWishList, "review_field");
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

		System.out.println("Day la summary of review backend :" + summaryOfReviewBackend);

		log.info("TC 05 -VerifyProductReviewMechanism: Change status from Pending to Approved");
		pendingReviewPage.selectItemInDynamicDropdown(driver, "Approved", "status_id");

		log.info("TC 06 -VerifyProductReviewMechanism: Click To Save Reviews button");
		pendingReviewPage.clickToDynamicButtonBackEndPage(driver, "Save Review");

		log.info("TC 07 -VerifyProductReviewMechanism: Go to frontend page");
		tvPage = (FrontEnd_TVPageObject) pendingReviewPage.openReviewFrontEndPage(driver);
		tvPage.clickToProductToViewDetail(driver, "Samsung LCD");
		tvPage.clickToLinkText(driver, "Reviews");

		log.info("TC 08 -VerifyProductReviewMechanism: Verify Reivews comment is shown");
		verifyTrue(tvPage.getTextInReviewTab(driver, summaryOfReviewBackend));
	}

	// @Test
	public void TC_03_VerifySortWorkingCorrectly() {
		backendloginPage.openUrl(driver, backendUrl);
		if (backendloginPage.isLoginFormDisplayed()) {
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
		salePage.clickToDynamicSortButton(driver, "Invoice #");
		salePage.sleepInSeconds(3000);
		salePage.checkSortedIsWorking(driver, "2");
		salePage.sleepInSeconds(3000);
		salePage.clickToDynamicSortButton(driver, "Order #");
		salePage.sleepInSeconds(3000);
		salePage.checkSortedIsWorking(driver, "4");

	}

	// @Test
	public void TC_04_VerifyPagingWorkingCorrectly() {
		backendloginPage.openUrl(driver, backendUrl);
		if (backendloginPage.isLoginFormDisplayed()) {
			log.info("TC 01 - Verify Invoice: Step 01 :Input to username/ password to login");
			backendloginPage.inputToDynamicTexboxField(driver, username, "username");
			backendloginPage.inputToDynamicTexboxField(driver, password, "login");

			log.info("TC 01 - Verify Invoice:  Step 02 Click to login button");
			backendHomePage = backendloginPage.clickToLoginButton(driver, "Login");

			log.info("TC 01 - Verify Invoice:  Step 03 - Click to close incomming msg");
			backendHomePage.clickToCloseInCommingMessage();
		}
		backendloginPage.openMultiplePage(driver, "Sales", "Orders");
		salePage = PageFactoryManage.getBackendSalePage(driver);
		salePage.selectInViewNumber("20");
		verifyEquals(salePage.countRowOfTable(), 20);
		salePage.selectInViewNumber("30");
		verifyEquals(salePage.countRowOfTable(), 30);
		salePage.selectInViewNumber("50");
		verifyEquals(salePage.countRowOfTable(), 50);
		salePage.selectInViewNumber("100");
		verifyEquals(salePage.countRowOfTable(), 100);
		salePage.selectInViewNumber("200");
		verifyEquals(salePage.countRowOfTable(), 200);

	}

	@Test
	public void TC_05_VerifySearchFunction() {
		backendloginPage.openUrl(driver, backendUrl);
		if (backendloginPage.isLoginFormDisplayed()) {
			log.info("TC 01 - Verify Invoice: Step 01 :Input to username/ password to login");
			backendloginPage.inputToDynamicTexboxField(driver, username, "username");
			backendloginPage.inputToDynamicTexboxField(driver, password, "login");

			log.info("TC 01 - Verify Invoice:  Step 02 Click to login button");
			backendHomePage = backendloginPage.clickToLoginButton(driver, "Login");

			log.info("TC 01 - Verify Invoice:  Step 03 - Click to close incomming msg");
			backendHomePage.clickToCloseInCommingMessage();
		}
		
		log.info("TC_05 : Open Manage Customer page");
		backendloginPage.openMultiplePage(driver, "Customers", "Manage Customers");
		manageCustomerPage = PageFactoryManage.getManageCustomerPage(driver);
		
		log.info("TC_05: Input to searchField");
		manageCustomerPage.inputToDynamicTextboxBackend(driver, searchID, "entity_id[to]");
		manageCustomerPage.inputToDynamicTextboxBackend(driver, searchName, "name");
		manageCustomerPage.inputToDynamicTextboxBackend(driver, searchEmail, "email");
		manageCustomerPage.inputToDynamicTextboxBackend(driver, searchTelephone, "Telephone");
		manageCustomerPage.inputToDynamicTextboxBackend(driver, searchZip, "billing_postcode");
		manageCustomerPage.selectItemInDynamicDropdown(driver, searchCountry, "customerGrid_filter_billing_country_id");
		manageCustomerPage.inputToDynamicTextboxBackend(driver, searchState, "billing_region");
		
		log.info("TC_05: Press Enter");
		manageCustomerPage.pressEnter(driver, "billing_region");
		manageCustomerPage.sleepInSeconds(2000);
		
		log.info("TC_05: Verify ID is mapping with input key");
		verifyEquals(manageCustomerPage.backendGetTextInTable(driver,"2"), searchID);
		
		log.info("TC_05: Verify Name is mapping with input key");
		verifyEquals(manageCustomerPage.backendGetTextInTable(driver,"3"), searchName);

		log.info("TC_05: Verify Email is mapping with input key");
		verifyEquals(manageCustomerPage.backendGetTextInTable(driver,"4"), searchEmail);

		log.info("TC_05: Verify Phone is mapping with input key");
		verifyEquals(manageCustomerPage.backendGetTextInTable(driver,"6"), searchTelephone);

		log.info("TC_05: Verify Zip is mapping with input key");
		verifyEquals(manageCustomerPage.backendGetTextInTable(driver,"7"), searchZip);

		log.info("TC_05: Verify Country is mapping with input key");
		verifyEquals(manageCustomerPage.backendGetTextInTable(driver,"8"), searchCountry);

		log.info("TC_05: Verify State is mapping with input key");
		verifyEquals(manageCustomerPage.backendGetTextInTable(driver,"9"), searchState);

	}

	@Test
	public void TC_06_SelectCheckboxFunction() {
		log.info("TC_06: STep 01: Open backendPage");
		backendloginPage.openUrl(driver, backendUrl);
		
		log.info("TC_06: Step 02: If login form is display => login");
		if (backendloginPage.isLoginFormDisplayed()) {
			log.info("TC 01 - Verify Invoice: Step 01 :Input to username/ password to login");
			backendloginPage.inputToDynamicTexboxField(driver, username, "username");
			backendloginPage.inputToDynamicTexboxField(driver, password, "login");

			log.info("TC 01 - Verify Invoice:  Step 02 Click to login button");
			backendHomePage = backendloginPage.clickToLoginButton(driver, "Login");

			log.info("TC 01 - Verify Invoice:  Step 03 - Click to close incomming msg");
			backendHomePage.clickToCloseInCommingMessage();
		}
		
		log.info("TC_06 -  Step 03: Open Sale Order Page");
		backendloginPage.openMultiplePage(driver, "Sales", "Orders");
		salePage = PageFactoryManage.getBackendSalePage(driver);
		salePage.clickToButtonInActionColumn(driver, "Select Visible");
	}
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
