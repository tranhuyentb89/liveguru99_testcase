package commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import liveguru.backend.HomePageUI;
import liveguru.frontend.AbstractPageUI;
import pageObjects.BackEnd_HomePageObject;
import pageObjects.FrontEnd_AdvancedSearchResultPageObject;
import pageObjects.FrontEnd_CheckOutCartPageObject;

public class AbstractPage {
	WebDriverWait explicit;
	By byLocator;
	long shortTimeout = 5;
	long longTimeout = 30;

	/* WEB BROWSER */
	public void openUrl(WebDriver driver, String Url) {
		driver.get(Url);
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		explicit.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendkeysAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String gettextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/* WEB ELEMENT */
	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();

	}

	public void clickToElement(WebDriver driver, String locator) {
		// highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String valueToSendkeys, String... values) {
		locator = String.format(locator, (Object[]) values);
		// highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(valueToSendkeys);
	}

	public void selectItemInHtmlDropdown(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInHtmlDropdown(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInHtmlDropdown(WebDriver driver, String locator, String value, String... fieldName) {
		locator = String.format(locator, (Object[]) fieldName);
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public void selectItemInCustomDropdown(WebDriver driver, String scrollXpath, String parentXpath, String childXpath, String expectedValue) throws Exception {
		// scroll toi element (cha)
		JavascriptExecutor javascript;
		javascript = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		javascript.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollXpath)));
		Thread.sleep(1000);

		// click vao dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();
		Thread.sleep(1000);

		// get tat ca cac item trong dropdown vao 1 list elements
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));

		// wait de tat ca cac phan tu trong dropdown duoc hien thi
		wait.until(ExpectedConditions.visibilityOfAllElements(childList));

		// dung vong lap for duyet qua tung phan tu sau do gettext
		for (WebElement child : childList) {
			String textItem = child.getText().trim();

			// neu actual text = expected text thi click vao phan tu do va break ra khoi
			// vong lap
			if (textItem.equals(expectedValue)) {
				// scroll den expected item de click
				javascript.executeScript("arguments[0].scrollIntoView(true);", child);
				Thread.sleep(1000);
				child.click();
				break;
			}
		}

	}

	public void selectCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String valueExpected) {
		JavascriptExecutor javascript;
		javascript = (JavascriptExecutor) driver;
		// click de mo dropdownlist
		WebElement parent = driver.findElement(By.xpath(parentXpath));
		javascript.executeScript("arguments[0].click()", parent);

		// wait cho cac item duoc hien thi
		List<WebElement> child = driver.findElements(By.xpath(childXpath));
		WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(child));

		// get text tat ca cac item ra va kiem tra bang gia tri mong muon hay ko
		for (WebElement childItem : child) {
			if (childItem.getText().equals(valueExpected)) {
				// scroll den item can chon
				javascript.executeScript("arguments[0].scrollIntoView(true);", childItem);

				// click vao item nay
				childItem.click();
				break;
			}
		}
	}

	public void selectDynamicCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String valueExpected, String... values) {
		parentXpath = String.format(parentXpath, (Object[]) values);
		childXpath = String.format(childXpath, (Object[]) values);
		JavascriptExecutor javascript;
		javascript = (JavascriptExecutor) driver;
		// click de mo dropdownlist
		WebElement parent = driver.findElement(By.xpath(parentXpath));
		javascript.executeScript("arguments[0].click()", parent);

		// wait cho cac item duoc hien thi
		List<WebElement> child = driver.findElements(By.xpath(childXpath));
		WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(child));

		// get text tat ca cac item ra va kiem tra bang gia tri mong muon hay ko
		for (WebElement childItem : child) {
			if (childItem.getText().equals(valueExpected)) {
				// scroll den item can chon
				javascript.executeScript("arguments[0].scrollIntoView(true);", childItem);

				// click vao item nay
				childItem.click();
				break;
			}
		}
	}

	public void selectItemInDynamicDropdown(WebDriver driver, String valueExpected, String parent, String child) {
		selectDynamicCustomDropdown(driver, AbstractPageUI.DYNAMIC_PARENT_XPATH, AbstractPageUI.DYNAMIC_CHILD_XPATH, valueExpected, parent, child);
	}

	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAttributeInElement(WebDriver driver, String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getAttributeInElement(WebDriver driver, String locator, String attributeName, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextInElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		List<WebElement> element = driver.findElements(By.xpath(locator));
		return element.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		// highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		// highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isEnable(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void doubleClickToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element);
	}

	public void hoverToElement(WebDriver driver, String locator) {

		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element);
	}

	public void hoverToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.sendKeys(element, key).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.sendKeys(element, key);
	}

	public void switchToChildWindowByID(WebDriver driver, String parent) throws Exception {
		// get ra tat ca cac tab dang co
		Set<String> allWindows = driver.getWindowHandles();
		Thread.sleep(3000);
		// dung vong for duyet qua tung cua so
		for (String runWindow : allWindows) {
			// kiem tra neu Id cua cua so nao khac voi parentId thi switch qua
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToIframe(WebDriver driver, String locator) {
		WebElement iframe = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(iframe);
	}

	public void backToTopWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		By byLocator = By.xpath(locator);
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitToElementPresent(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		By byLocator = By.xpath(locator);
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		// Date date = new Date();
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator) {
		// Date date = new Date();
		// System.out.println("Start time to check control undisplayed " +
		// date.toString());
		overideTimeout(driver, Constants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		// System.out.println("Element size = : " + elements.size());
		if (elements.size() == 0) {
			// System.out.println("Element khong co trong DOM");
			// System.out.println("End time to check control undisplayed = " + new
			// Date().toString());
			overideTimeout(driver, Constants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			// System.out.println("Element co trong DOM nhung ko visible");
			// System.out.println("End time to check control undisplayed = " + new
			// Date().toString());
			overideTimeout(driver, Constants.LONG_TIMEOUT);
			return true;
		} else {
			// System.out.println("Element co trong dom nhung visible");
			// System.out.println("End time to check control undisplayed = " + new
			// Date().toString());
			overideTimeout(driver, Constants.LONG_TIMEOUT);
			return false;
		}
	}

	public void overideTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	// UPLOAD
	public void upload1File(WebDriver driver, String locator, String filePath) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath);
	}

	public void uploadmultiFile(WebDriver driver, String locator, String filePath01, String filePath02, String filePath03) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath01 + "\n" + filePath02 + "\n" + filePath03);
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute(locator);
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", "border:3px solid red; border-style:dashed;");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", originalStyle);

	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public Object clickToElementByJS(WebDriver driver, String xpathName) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		// highlightElement(driver, xpathName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object clickToElementByJS(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		// highlightElement(driver, xpathName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object sendkeyToElementByJS(WebDriver driver, String xpathName, String value) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object removeAttributeInDOM(WebDriver driver, String xpathName, String attribute) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public Object removeAttributeInDOM(WebDriver driver, String locator, String attribute, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public Object scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object scrollToElementByJS(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public boolean isImageDisplayed(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	public Object navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}

	public void waitForElementPresence(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitForElementClickAble(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.elementToBeClickable(byLocator));

	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	}

	public void waitForAlertPresence(WebDriver driver) {
		explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.alertIsPresent());
	}

	// open page
	// public ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
	// waitToElementVisible(driver,
	// com.bankguru.customers.AbstractPageUI.CHANGE_PASSWORD_LINK);
	// clickToElement(driver, AbstractPageUI.CHANGE_PASSWORD_LINK);
	// return PageFactoryManage.getChangPasswordPage(driver);
	// }
	//
	// public DepositPageObject openDepositPage(WebDriver driver) {
	// waitToElementVisible(driver, AbstractPageUI.DEPOSIT_LINK);
	// clickToElement(driver, AbstractPageUI.DEPOSIT_LINK);
	// return PageFactoryManage.getDepositPage(driver);
	// }
	//
	// public NewAccountPageObject openNewAccountPage(WebDriver driver) {
	// waitToElementVisible(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
	// clickToElement(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
	// return PageFactoryManage.getNewAccountPage(driver);
	// }
	//
	// public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
	// waitToElementVisible(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
	// clickToElement(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
	// return PageFactoryManage.getNewCustomerPage(driver);
	// }

	public AbstractPage openMultiplePage(WebDriver driver, String navBar, String subMenu) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, navBar);
		hoverToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu);
		switch (subMenu) {
		case "Orders":
			return PageFactoryManage.getBackendSalePage(driver);
		case "Pending Reviews":
			return PageFactoryManage.getPendingReviewPage(driver);
		default:
			return PageFactoryManage.getHomePage(driver);
		}
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		// highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void openMultiplePages(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
	}

	// public void pressTab(WebDriver driver, String fieldName) {
	// sendKeyboardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON,
	// Keys.TAB, fieldName);
	// }

	// public void pressSpace(WebDriver driver, String fieldName) {
	// sendKeyboardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON,
	// Keys.SPACE, fieldName);
	// }
	//
	public void inputToDynamicTexboxField(WebDriver driver, String value, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_CHECKBOX, fieldName);
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_CHECKBOX, value, fieldName);
	}

	public void inputToDynamicTextAreaField(WebDriver driver, String value, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BOX, fieldName);
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BOX, value, fieldName);
	}

	public String getTextInAreaBox(WebDriver driver, String fielName) {
		return getTextElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BOX, fielName);
	}
	public String getValueOffAttributeInTextbox(WebDriver driver, String attributeName, String fieldName) {
		return getAttributeInElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_CHECKBOX, attributeName, fieldName);
	}
	public void clearValue(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
	}

	public void clearValue(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
	}

	public void clearValueOfTextbox(WebDriver driver, String fieldName) {
		clearValue(driver, AbstractPageUI.DYNAMIC_TEXTBOX_CHECKBOX, fieldName);
	}
	//
	// public void removeAttribute(WebDriver driver, String attributeToRemoved,
	// String fieldName) {
	// removeAttributeInDOM(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON,
	// attributeToRemoved, fieldName);
	// }
	//
	// public void selectFromDropdown(WebDriver driver, String value, String
	// fieldName) {
	// selectItemInHtmlDropdown(driver, AbstractPageUI.DYNAMIC_DROPDOWN, value,
	// fieldName);
	// }

	public boolean checkPageTitle(WebDriver driver, String fieldName) {
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_TITLE, fieldName);
	}

	public void addProductToLinks(WebDriver driver, String fieldName01, String fieldName02) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_ADD_TO_LINKS, fieldName01, fieldName02);
	}

	public String getSuccessMessage(WebDriver driver) {
		return getTextElement(driver, AbstractPageUI.DYNAMIC_SUCCESS_MESSAGE);
	}

	public FrontEnd_AdvancedSearchResultPageObject clickToDynamicButton(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON_TAG_NAME, fieldName);
		sleepInSeconds(5000);
		return PageFactoryManage.getAdvancedSearchResultPage(driver);
	}
	
	public BackEnd_HomePageObject clickToLoginButton(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON_INPUT_TAG_NAME, fieldName);
		return PageFactoryManage.getBackendHomePage(driver);
	}
	
	public void clickToDynamicButtonBackEndPage(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON_TAG_NAME, fieldName);
	}


	public Object getProductName(WebDriver driver, String attributeName, String productName) {
		return getAttributeInElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_IMAGE_TO_CLICK, attributeName, productName);
	}

	public void clickToProductToViewDetail(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_IMAGE_TO_CLICK, fieldName);
	}

	public FrontEnd_CheckOutCartPageObject clickToAddToCardButton(WebDriver driver, String values) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_ADD_TO_CART_BUTTON, values);
		return PageFactoryManage.getCheckOutCartPage(driver);
	}

	public void clickToTextboxCheckbox(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_CHECKBOX, fieldName);
	}

	public String getPriceOfProductAtList(WebDriver driver, String fieldName) {
		return getTextElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_PRICE, fieldName);
	}

	public String getPriceOfProductAtDetail(WebDriver driver, String fieldName) {
		return getTextElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_PRICE_DETAIL, fieldName);
	}

	public String getProductName(WebDriver driver, String fieldName) {
		return getTextElement(driver, AbstractPageUI.PRODUCT_NAME, fieldName);
	}

	public AbstractPage clickToSubMenu(WebDriver driver, String navBar, String subMenu) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, navBar);
		hoverToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu);
		return PageFactoryManage.getBackendSalePage(driver);
	}

	public void selectItemInStatusBackEnd(WebDriver driver, String valueExpected) {
		selectCustomDropdown(driver, HomePageUI.DROPDOWN_PARENT_STATUS, HomePageUI.DROPDOWN_CHILD_OF_STATUS, valueExpected);
	}

	public void selectFirstItemInList(WebDriver driver, String locator, String...dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		List<WebElement> element = driver.findElements(By.xpath(locator));
		element.get(0).click();
		sleepInSeconds(5000);
	}
	
	public void selectFirstItemInDynamicList(WebDriver driver, String fieldName) {
		selectFirstItemInList(driver, AbstractPageUI.DYNAMIC_CHECKBOX, fieldName);
	}
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName)) {
				// File has been found, it can now be deleted:
				return true;
			}
		}
		return false;
	}

	public void openNewWindowInNewTab(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(Constants.BACK_END_URL);

	}

	public void clickToHeadingButton(WebDriver driver, String dynamicValue) {
		doubleClickToElement(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE_LINK_TEXT_HEADER, dynamicValue);
		//clickToElement(driver, AbstractPageUI.DYNAMIC_HEADING_BUTTON, dynamicValue);
	}

	public void clickToSubOfSubMenu(WebDriver driver, String navMenu, String subMenu, String subMenu01, String subMenu02) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, navMenu);
		hoverToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu);
		hoverToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu01);
		hoverToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu02);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, subMenu02);
		sleepInSeconds(5000);
	}
	
	public void clickToButtonInActionColumn(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_ACTION, fieldName);
	}
	
	public AbstractPage openBackEndPage(WebDriver driver) {
		driver.get(Constants.BACK_END_URL);
		return PageFactoryManage.getBackendHomePage(driver);
	}
	
	public AbstractPage openReviewFrontEndPage(WebDriver driver) {
		driver.get(Constants.FRONT_END_REVIEW_PRODUCT_URL);
		return PageFactoryManage.getTVPage(driver);
	}

	public void clickToLinkText(WebDriver driver, String fieldName) {
		clickToElement(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE_LINK_TEXT_HEADER, fieldName);
	}
	
	public boolean getTextInReviewTab(WebDriver driver, String expectedValue) {
		List<WebElement> element = driver.findElements(By.xpath("//div[@id='customer-reviews']//a"));
		for(WebElement child : element) {
			String getChildText = child.getText().trim().toUpperCase();
			System.out.println("Huyen:" + getChildText);
			if(getChildText.equals(expectedValue)) {
				System.out.println(getChildText);
				return true;
			}
		}
		return false;
	}
	
	public boolean sortOrNot(ArrayList<String> dropdownValues) {
		System.out.println("number of value" + dropdownValues.size());
		for(int i= 0 ; i < dropdownValues.size()-1; i++) {
			int temp = dropdownValues.get(i).compareTo(dropdownValues.get(i+1));
			if(temp > 1) {
				System.out.println("i values" + i);
				return false;
			}
		}
		return true;
	}
	
	public void columnIsSortOrNot(WebDriver driver, String locator, String columnNumber, String...dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		columnNumber = String.format(columnNumber, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> dropdownValues = driver.findElements(By.xpath(columnNumber));
		ArrayList<String> listValue = new ArrayList<>();
		for(WebElement value: dropdownValues) {
			System.out.println("value are " + value.getText());
			listValue.add(value.getText());
		}
		boolean sorted = sortOrNot(listValue);
		assertEquals(true , sorted);
	}
	
	public void checkSortedIsWorking(WebDriver driver) {
		columnIsSortOrNot(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE_LINK_TEXT_HEADER, AbstractPageUI.INVOICE_NUMBER_COLUMN, "Invoice #", "2");
	}
}
