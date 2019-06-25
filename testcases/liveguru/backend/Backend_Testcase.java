package liveguru.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.BackEnd_HomePageObject;
import pageObjects.BackEnd_LoginPageObject;

public class Backend_Testcase extends AbstractTest {
	String username, password;
	WebDriver driver;

	BackEnd_LoginPageObject backendloginPage;
	BackEnd_HomePageObject backendHomePage;

	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browserName) {
		driver = openBrowser(browserName);
		backendloginPage = new BackEnd_LoginPageObject(driver);
		username = "user01";
		password = "guru99com";
	}

	@Test
	public void TC_01_VerifyInvoiceCanPrint() {
		backendloginPage.inputToDynamicTexboxField(driver, username, "username");
		backendloginPage.inputToDynamicTexboxField(driver, password, "login");
		backendHomePage = backendloginPage.clickToLoginButton(driver, "Login");
		backendHomePage.clickToCloseInCommingMessage();
		backendHomePage.clickToSubMenu(driver, "Sales", "Orders");
		backendHomePage.selectItemInStatusBackEnd(driver, "Canceled");
		backendHomePage.clickToDynamicButton(driver, "Search");
		backendHomePage.sleepInSeconds(5000);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
