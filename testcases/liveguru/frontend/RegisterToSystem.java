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
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
public class RegisterToSystem extends AbstractTest{
	WebDriver driver;
	String firstName, middleName, lastName, emailAddress, password, confirmPassword;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	AccountInformationPageObject accountInfoPage;
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser(browserName);
		homePage = new HomePageObject(driver);
		firstName ="huyen";
		middleName ="tran";
		lastName ="thi";
		emailAddress = "tranhuyen"+ ramdomNumber() +"@gmail.com";
		password ="123456";
		confirmPassword = "123456";
	}
	
	@Test
	public void TC_01_RegisterToSystem() {
		homePage.clickToAccountButton();
		registerPage = homePage.clickToRegisterButton(driver, "Register");
		log.info("Register - Step 01: Verify register page displayed");
		verifyTrue(registerPage.isRegisterFormDisplayed());
		registerPage.inputToDynamicField(driver, firstName, "firstname");
		registerPage.inputToDynamicField(driver, middleName, "middlename");
		registerPage.inputToDynamicField(driver, lastName, "lastname");
		registerPage.inputToDynamicField(driver, emailAddress, "email_address");
		registerPage.inputToDynamicField(driver, password, "password");
		registerPage.inputToDynamicField(driver, confirmPassword, "confirmation");
		registerPage.clickToRegisterButton();
	}
	
	@Test
	public void TC_02_VerifyInformationCorrectAfterRegistered() {
		registerPage.openMultiplePages(driver, "Account Information");
		accountInfoPage = PageFactoryManage.getAccountInfoPage(driver);
		accountInfoPage.sleepInSeconds(30);
		verifyEquals(accountInfoPage.getValueInTextbox(driver, "value", "firstname"), firstName);
		verifyEquals(accountInfoPage.getValueInTextbox(driver, "value","middlename"), middleName);
		verifyEquals(accountInfoPage.getValueInTextbox(driver, "value","lastname"), lastName);
	}
	
	@Test
	public void TC_03_VerifyCostAtListAndDetailAreEqual() {
		
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
