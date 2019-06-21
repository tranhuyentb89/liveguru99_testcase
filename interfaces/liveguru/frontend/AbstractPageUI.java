package liveguru.frontend;

public class AbstractPageUI {
	public static final String DYNAMIC_TEXTBOX ="//input[@id='%s']";
	public static final String ACCOUNT_LINK_AT_HEADER ="//div[@class='page-header-container']//span[text()='Account']";
	public static final String REGISTER_LINK ="//div[@class='page-header-container']//a[text()='%s']";
	public static final String DYNAMIC_MENU_LINK ="//div[@class='block block-account']//a[text()='%s']";
	public static final String DYNAMIC_MENU_TAB ="//ol[@class='nav-primary']//a[text()='%s']";
	public static final String DYNAMIC_PRODUCT_PRICE="//a[text()='%s']/parent::h2/following-sibling::div[@class='price-box']/span[@id='%s']/span";
	public static final String DYNAMIC_PRODUCT_IMAGE_TO_CLICK ="//a[text()='%s']/parent::h2/parent::div/preceding-sibling::a[@title='%s']";
	public static final String DYNAMIC_PRODUCT_PRICE_DETAIL ="//span[text()='%s']/parent::div/following-sibling::div[@class='price-info']//span[@class='price']";
	public static final String DYNAMIC_ADD_TO_CART_BUTTON ="//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']//button[@class='button btn-cart']";
}
