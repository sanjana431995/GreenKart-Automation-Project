package GreenKart.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenKart.AbsctractUtils.Utils;

public class CartPage extends Utils {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[text()='Place Order']")
	WebElement placeorder;

	@FindBy(css = "option[value='India']")
	WebElement selectcountry;

	@FindBy(xpath = "//div/select")
	WebElement dropdown;

	@FindBy(tagName = "option")
	List<WebElement> option;
	
	
	@FindBy(css = ".chkAgree")
	WebElement checkbox;
	
	
	@FindBy(xpath = "//button[text()='Proceed']")
	WebElement proceedbtn;
	
	@FindBy(xpath = "//div[@class='wrapperTwo']")
	WebElement thankyoutext;
	
	public WebElement placeOrder() {

		placeorder.click();
		dropdown.click();
		// List<WebElement>
		// country=option.stream().filter(opt->opt.getText().equalsIgnoreCase("india"));
		//waitForElementToAppearForVeggies(option);
		for (int i = 0; i < option.size(); i++) {
			String country = option.get(i).getText();
			if (country.equalsIgnoreCase("india")) {
				selectcountry.click();
				
			}
		}
		return selectcountry;
	}
	
	@FindBy(css = ".errorAlert")
	WebElement erroralert;
	
	public String placeOrderWithoutSelection() {

		placeorder.click();
		dropdown.click();
	
		for (int i = 0; i < option.size(); i++) {
			String country = option.get(i).getText();
			if (country.equalsIgnoreCase("india")) {
				selectcountry.click();
				proceedbtn.click();
			}
		}
		return erroralert.getText();
	}

	public String selectCheckbox() {

		placeOrder();
		//Boolean result=checkbox.isSelected();
		 checkbox.click();
		 proceedbtn.click();
		return thankyoutext.getText();
	}
	@FindBy(css = ".promoCode")
	WebElement promocode;
	@FindBy(css = ".promoBtn")
	WebElement promoBtn;
	@FindBy(css = ".promoInfo")
	WebElement promoInfo;
	
	public String validApplyCode(String promo) {
		promocode.sendKeys(promo);
		promoBtn.click();
		return promoInfo.getText();
		
	}
	
	
	
	

}
