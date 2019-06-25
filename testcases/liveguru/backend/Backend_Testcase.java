package liveguru.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.BackEnd_LoginPageObject;

public class Backend_Testcase extends AbstractTest {
	String username, password;
	WebDriver driver;
	
	BackEnd_LoginPageObject loginPage;
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openBrowser(browserName);
		loginPage = new BackEnd_LoginPageObject(driver);
		username ="user01";
		password ="guru99com";
	}
	
	@Test
	public void TC_01_VerifyInvoiceCanPrint() {
		loginPage.inputToDynamicTexboxField(driver, username, "username");
		loginPage.inputToDynamicTexboxField(driver, password, "login");
		loginPage.clickToLoginButton(driver, "Login");
		loginPage.clickToCloseInCommingMessage();
	}

}
