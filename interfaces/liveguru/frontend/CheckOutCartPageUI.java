package liveguru.frontend;

public class CheckOutCartPageUI {
	public static final String DISCOUNT_CODE_TEXTBOX ="//input[@id='coupon_code']";
	public static final String APPLY_DISCOUNT_BUTTON="//div[@class='discount']//button[@type='button']";
	public static final String DISCOUNT_AMOUNT_LABEL ="//td[contains(text(),'Discount (GURU50) ')]//following-sibling::td/span";
	public static final String GRAND_AMOUNT_LABEL ="//strong[contains(text(),'Grand Total')]//parent::td/following-sibling::td//span";
	public static final String QUANITY_TEXTBOX ="//input[@class='input-text qty']";
	public static final String UPDATE_BUTTON="//button[@class='button btn-update']";
	public static final String ERROR_MESSAGE_LABEL="//table[@id='shopping-cart-table']//p";
	public static final String FLAT_RATE ="//form[@id='co-shipping-method-form']//span";
	public static final String ORDER_CODE ="//p[contains(text(),'Your order # is:')]/a";
}
