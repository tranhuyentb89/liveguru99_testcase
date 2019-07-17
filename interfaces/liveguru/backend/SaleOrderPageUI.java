package liveguru.backend;

public class SaleOrderPageUI {
	public static final String ACTIONS_DROPDOWN_PARENT_XPATH ="//label[text()='Actions']/parent::span//select";
	public static final String ACTIONS_DROPDOWN_CHILD_XPATH ="//label[text()='Actions']/parent::span//select/option";
	public static final String VIEW_DROPDOWN_PARENT_XPATH ="//select[@name='limit']";
	public static final String VIEW_DROPDOWN_CHILD_XPATH ="//select[@name='limit']/option";
	public static final String ROW_OF_TABLE ="//table[@id='sales_order_grid_table']//tbody/tr";
}
