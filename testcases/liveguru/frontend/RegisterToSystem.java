package liveguru.frontend;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObjects.AccountInformationPageObject;
import pageObjects.AdvancedSearchPageObject;
import pageObjects.AdvancedSearchResultPageObject;
import pageObjects.CheckOutCartPageObject;
import pageObjects.HomePageObject;
import pageObjects.MobilePageObject;
import pageObjects.RegisterPageObject;
import pageObjects.TVPageObject;

public class RegisterToSystem extends AbstractTest {
	WebDriver driver;
	String firstName, middleName, lastName, emailAddress, password, confirmPassword, priceAtList, priceAtDetail, mobilePageUrl, coupon;
	String quantityMax, tvPageUrl, msgToShareWishList, zipCode, address, city, phoneNumber, orderCode, homePageUrl;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	AccountInformationPageObject accountInfoPage;
	MobilePageObject mobilePage;
	CheckOutCartPageObject checkoutCartPage;
	TVPageObject tvPage;
	AdvancedSearchPageObject advancedSearchPage;
	AdvancedSearchResultPageObject advanceSearchResultPage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser(browserName);
		homePage = new HomePageObject(driver);
		firstName = "huyen";
		middleName = "tran";
		lastName = "thi";
		emailAddress = "tranhuyen" + ramdomNumber() + "@gmail.com";
		password = "123456";
		confirmPassword = "123456";
		coupon = "GURU50";
		quantityMax = "501";
		msgToShareWishList ="Test";
		zipCode ="543432";
		phoneNumber="0982828282";
		address ="ha noi viet nam";
		city = "Ha noi";
	}

	@Test
	public void TC_01_RegisterToSystem() {
		log.info("Register - Step 01 : click to Register button");
		homePage.clickToAccountButton();
		registerPage = homePage.clickToRegisterButton(driver, "Register");

		log.info("Register - Step 02 : Verify Register Page is displayed");
		verifyTrue(registerPage.isRegisterFormDisplayed());

		log.info("Register - Step 03 : Input to all field and click Register button");
		registerPage.inputToDynamicTexboxField(driver, firstName, "firstname");
		registerPage.inputToDynamicTexboxField(driver, middleName, "middlename");
		registerPage.inputToDynamicTexboxField(driver, lastName, "lastname");
		registerPage.inputToDynamicTexboxField(driver, emailAddress, "email_address");
		registerPage.inputToDynamicTexboxField(driver, password, "password");
		registerPage.inputToDynamicTexboxField(driver, confirmPassword, "confirmation");
		registerPage.clickToRegisterButton();
	}

	@Test
	public void TC_02_VerifyInformationCorrectAfterRegistered() {
		log.info("Verify Info - Step 01 : Open Account Info page");
		registerPage.openMultiplePages(driver, "Account Information");
		accountInfoPage = PageFactoryManage.getAccountInfoPage(driver);
		accountInfoPage.sleepInSeconds(30);

		log.info("Verify Info - Step 02 : Verify info is correcttly");
		verifyEquals(accountInfoPage.getValueInTextbox(driver, "value", "firstname"), firstName);
		verifyEquals(accountInfoPage.getValueInTextbox(driver, "value", "middlename"), middleName);
		verifyEquals(accountInfoPage.getValueInTextbox(driver, "value", "lastname"), lastName);
	}

	@Test
	public void TC_03_VerifyCostAtListAndDetailAreEqual() {
		log.info("Verify Cost - Step 01 : Open Mobile Page");
		mobilePage = accountInfoPage.clickToMobileButton("Mobile");
		mobilePageUrl = mobilePage.getCurrentUrl(driver);

		log.info("Verify Info - Step 02 : get Price at the list of product");
		priceAtList = mobilePage.getPriceOfProductAtList(driver, "Sony Xperia");
		mobilePage.clickToProductToViewDetail(driver, "Xperia");

		log.info("Verify Info - Step 03 : get Price at the product detail");
		priceAtDetail = mobilePage.getPriceOfProductAtDetail(driver, "Sony Xperia");

		log.info("Verify Info - Step 04 : Verify price on both is equal");
		verifyEquals(priceAtList, priceAtDetail);
	}

	@Test
	public void TC_05_VerifyDiscount() {

		log.info("Verify Discount - Step 01 : Open Mobile Page");
		mobilePage.openUrl(driver, mobilePageUrl);

		log.info("Verify Discount - Step 02 : Click to Iphone");
		checkoutCartPage = mobilePage.clickToAddToCardButton(driver, "IPhone");

		log.info("Verify Discount - Step 03 : Input to discount textbox");
		checkoutCartPage.inputToDiscountTextBox(coupon);

		log.info("Verify Discount - Step 04 : Click to apply button");
		checkoutCartPage.clickToApplyButton();

		log.info("Verify Discount - Step 05 : Verify discount amount and grand total is correct");
		verifyEquals(checkoutCartPage.getDiscountAmount(), "-$25.00");
		verifyEquals(checkoutCartPage.getGrandTotalAmount(), "$475.00");
//		checkoutCartPage.clearValueOfTextbox(driver, "coupon_code");
//		checkoutCartPage.clickToApplyButton();

	}

	@Test
	public void TC_06_Add500ItemOfProduct() {
		log.info("Verify Quantity Box - Step 01 : Open Mobile Page");
		mobilePage.openUrl(driver, mobilePageUrl);

		log.info("Verify Quantity Box - Step 02 : Click Add To Card Sony Xperia");
		checkoutCartPage = mobilePage.clickToAddToCardButton(driver, "Sony Xperia");

		log.info("Verify Quantity Box - Step 03 : Clear quantity Textbox");
		checkoutCartPage.clearQuantityTextBox();

		log.info("Verify Quantity Box - Step 04 : Click to apply button");
		checkoutCartPage.inputToQuantityTextbox(quantityMax);

		log.info("Verify Quantity Box - Step 05 : Click to update button");
		checkoutCartPage.clickToDynamicButton(driver, "Update");

		log.info("Verify Quantity Box - Step 06 : Verify Error message");
		verifyEquals(checkoutCartPage.getErrorMessage(), "* The maximum quantity allowed for purchase is 500.");

		log.info("Verify Quantity Box - Step 07 : Click to Empty button");
		checkoutCartPage.clickToDynamicButton(driver, "Empty Cart");
		verifyTrue(checkoutCartPage.checkPageTitle(driver, "Shopping Cart is Empty"));
	}

	@Test
	public void TC_07_CompareProduct() {
		log.info("CompareProduct - Step 01 : Open Mobile Page");
		mobilePage.openUrl(driver, mobilePageUrl);

		log.info("CompareProduct - Step 02 : Add Sony Xperia to compare");
		checkoutCartPage.addProductToLinks(driver, "Sony Xperia", "Add to Compare");
		checkoutCartPage.sleepInSeconds(300);

		log.info("CompareProduct - Step 03 : Add IPhone to compare");
		checkoutCartPage.addProductToLinks(driver, "IPhone", "Add to Compare");
		checkoutCartPage.sleepInSeconds(300);

		log.info("CompareProduct - Step 04 : Click to compare button");
		checkoutCartPage.clickToDynamicButton(driver, "Compare");
		checkoutCartPage.switchWindowByTitle(driver, "Products Comparison List - Magento Commerce");

		log.info("CompareProduct - Step 05 : Verify Heading is Compare Products");
		verifyTrue(checkoutCartPage.checkPageTitle(driver, "Compare Products"));

		log.info("CompareProduct - Step 06 : Verify Sony Xperia and IPhone is present");
		verifyEquals(checkoutCartPage.getProductName(driver, "title", "IPhone"), "IPhone");
		verifyEquals(checkoutCartPage.getProductName(driver, "title", "Sony Xperia"), "Sony Xperia");
		
		checkoutCartPage.switchWindowByTitle(driver, "Mobile");
		log.info("CompareProduct - Step 07 : Closed child window");
		checkoutCartPage.closeWindow();
	}
	
	@Test
	public void TC_08_SharedWishListByEmail() {
		log.info("SharedWishListByEmail - Step 01 : Open TV Page");
		tvPage = checkoutCartPage.clickToTVButton("TV");
		tvPageUrl = tvPage.getCurrentUrl(driver);
		
		log.info("SharedWishListByEmail - Step 02 : ADD LG LCD to your wishlist");
		tvPage.addProductToLinks(driver, "LG LCD", "Add to Wishlist");
		
		log.info("SharedWishListByEmail - Step 03 : Verify Product are added into wishlist");
		verifyEquals(tvPage.getSuccessMessage(driver), "LG LCD has been added to your wishlist. Click here to continue shopping.");
		
		log.info("SharedWishListByEmail - Step 04 : Click to share wishlist button");
		tvPage.clickToDynamicButton(driver, "Share Wishlist");
		
		log.info("SharedWishListByEmail - Step 05 : Input into form");
		tvPage.inputToDynamicTextAreaField(driver, emailAddress, "email_address");
		tvPage.inputToDynamicTextAreaField(driver, msgToShareWishList, "message");
		
		log.info("SharedWishListByEmail - Step 04 : Click to share wishlist button");
		tvPage.clickToDynamicButton(driver, "Share Wishlist");
		
		verifyEquals(tvPage.getSuccessMessage(driver), "Your Wishlist has been shared.");
		verifyEquals(checkoutCartPage.getProductName(driver, "title", "LG LCD" ), "LG LCD");
		
	}
	
	//@Test
	public void TC_09_AddYourReview() {
		log.info("AddYourReview - Step 01 : Open TV Page");
		tvPage.openUrl(driver, tvPageUrl);
		tvPage.clickToProductToViewDetail(driver, "Samsung LCD");
		tvPage.clickToRatingLink("Add Your Review");
		tvPage.inputToDynamicTextAreaField(driver,msgToShareWishList , "review_field");
		tvPage.inputToDynamicTexboxField(driver, "kaka", "summary_field");
		tvPage.clickToDynamicButton(driver, "Submit Review");
		verifyEquals(tvPage.getSuccessMessage(driver), "Your review has been accepted for moderation.");

	}
	
	@Test
	public void TC_10_UserAbleToPurchaseProduct() {
		log.info("AddYourReview - Step 01 : Open TV Page");
		tvPage.openUrl(driver, tvPageUrl);
		checkoutCartPage = tvPage.clickToAddToCardButton(driver, "Samsung LCD");
		checkoutCartPage.selectItemInDynamicDropdown(driver, "United States", "country", "country");
		checkoutCartPage.selectItemInDynamicDropdown(driver, "New York", "region_id", "region_id");
		checkoutCartPage.inputToDynamicTexboxField(driver,zipCode , "postcode");
		checkoutCartPage.clickToDynamicButton(driver, "Estimate");
		verifyEquals(checkoutCartPage.getFlateRateAmount(), "$5.00");
		checkoutCartPage.clickToFlateRate();
		checkoutCartPage.clickToDynamicButton(driver, "Update Total");
		checkoutCartPage.sleepInSeconds(5000);
		//verifyEquals(checkoutCartPage.getGrandTotalAmount(), "$705.00");
		checkoutCartPage.clickToDynamicButton(driver, "Proceed to Checkout");
		checkoutCartPage.inputToDynamicTexboxField(driver, address, "billing:street1");
		checkoutCartPage.inputToDynamicTexboxField(driver, city, "billing:city");
		checkoutCartPage.selectItemInDynamicDropdown(driver, "New York", "billing:region_id", "billing:region_id");
		checkoutCartPage.inputToDynamicTexboxField(driver, zipCode, "billing:postcode");
		checkoutCartPage.selectItemInDynamicDropdown(driver, "United States", "billing:country_id", "billing:country_id");
		checkoutCartPage.inputToDynamicTexboxField(driver, phoneNumber, "billing:telephone");
		checkoutCartPage.clickToDynamicButton(driver, "Continue");
		checkoutCartPage.clickToCountinueButtonAtShippingMethod("shipping-method-buttons-container");
		checkoutCartPage.clickToTextboxCheckbox(driver, "p_method_checkmo");
		checkoutCartPage.clickToCountinueButtonAtShippingMethod("payment-buttons-container");
		checkoutCartPage.clickToDynamicButton(driver, "Place Order");
		orderCode = checkoutCartPage.getOrderCode();
		System.out.println(orderCode);
	}
	
	@Test
	public void TC_11_AdvanceSearchFunction() {
		homePage = checkoutCartPage.clickToLogo();
		advancedSearchPage = homePage.clickToAdvancedSearchLink();
		advancedSearchPage.inputToDynamicTexboxField(driver, "1", "price");
		advancedSearchPage.inputToDynamicTexboxField(driver, "150", "price_to");
		advanceSearchResultPage = advancedSearchPage.clickToSearchButton();
		System.out.println(advanceSearchResultPage.getPriceOfProductAtList(driver, "Sony Xperia"));
		System.out.println(advanceSearchResultPage.getPriceAfterSaleOff(driver, "Samsung Galaxy"));

	}

	public int ramdomNumber() {
		Random radom = new Random();
		int number = radom.nextInt(9999999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
