package liveguru.frontend;

public class AbstractPageUI {
	public static final String DYNAMIC_TEXTBOX_CHECKBOX ="//input[@id='%s']";
	public static final String ACCOUNT_LINK_AT_HEADER ="//div[@class='page-header-container']//span[text()='Account']";
	public static final String REGISTER_LINK ="//div[@class='page-header-container']//a[text()='%s']";
	public static final String DYNAMIC_MENU_LINK ="//div[@class='block block-account']//a[text()='%s']";
	public static final String DYNAMIC_MENU_TAB ="//ol[@class='nav-primary']//a[text()='%s']";
	public static final String DYNAMIC_PRODUCT_PRICE="//a[text()='%s']/parent::h2/following-sibling::div[@class='price-box']//span[@class='price']";
	public static final String PRICE_AFTER_SALE="//a[text()='%s']/parent::h2/following-sibling::div[@class='price-box']//p[@class='special-price']//span[@class='price']";
	public static final String DYNAMIC_PRODUCT_IMAGE_TO_CLICK ="//a[@class='product-image' and @title='%s']";
	public static final String DYNAMIC_PRODUCT_PRICE_DETAIL ="//span[text()='%s']/parent::div/following-sibling::div[@class='price-info']//span[@class='price']";
	public static final String DYNAMIC_ADD_TO_CART_BUTTON ="//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']//button[@class='button btn-cart']";
	public static final String DYNAMIC_BUTTON_EMPTY_UPDATE_COMPARE_SHARE_WISHLIST="//button[@title='%s']";
	public static final String DYNAMIC_PAGE_TITLE ="//h1[text()='%s']";
	public static final String DYNAMIC_ADD_TO_LINKS="//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']//a[text()='%s']";
	public static final String DYNAMIC_SUCCESS_MESSAGE="//li[@class='success-msg']//span";
	public static final String DYNAMIC_TEXTAREA_BOX="//textarea[@id='%s']";
	public static final String DYNAMIC_RATING_LINK="//div[@class='ratings']//a[text()='%s']";
	public static final String DYNAMIC_PARENT_XPATH="//select[@id='%s']";
	public static final String DYNAMIC_CHILD_XPATH="//select[@id='%s']//option";
	public static final String DYNAMIC_SHIPPING_PAYMENT_BUTTON_CONTINUE ="//div[@id='%s']/button";
	public static final String HOME_PAGE_LOGO ="//a[@class='logo']";
	public static final String PRODUCT_NAME ="//h2[@class='product-name']/a[@title='%s']";
	
	//Xpath of backend part
	public static final String DYNAMIC_BUTTON ="//input[@title='%s']";
	public static final String DYNAMIC_LINK ="//div[@class='nav-bar']//span[text()='%s']";
	public static final String ORDER_CHECKBOX ="//input[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE ="//span[text()='%s']";
	public static final String DYNAMIC_HEADING_BUTTON ="//tr[@class='headings']//span[text()='%s']";
	
}
