package GreenKart.PageObjects;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GreenKart.AbsctractUtils.Utils;

public class VegHomePage extends Utils {

	WebDriver driver;

	public VegHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "h4.product-name")
	List<WebElement> veggies;

	@FindBy(xpath = "//div[@class='product-action']/button")
	List<WebElement> addtocart;

	@FindBy(css = ".increment")
	WebElement increment;

	@FindBy(css = ".cart-icon")
	WebElement bag;

	@FindBy(xpath = "//button[text()='PROCEED TO CHECKOUT']")
	WebElement checkout;

//.product-name
	@FindBy(xpath = "//p[@class='product-name']")
	List<WebElement> productname;
	public String[] itemsNeeded = { "Broccoli", "Potato", "Cucumber", "Beetroot" };

	public String[] addProductToCart() throws InterruptedException {

		// String[] itemsNeeded = { "Broccoli", "Potato", "Cucumber", "Beetroot" };
		int j = 0;
		waitForElementToAppearForVeggies(veggies);
		// Thread.sleep(3000);
		for (int i = 0; i < veggies.size(); i++) {
			String[] veg = veggies.get(i).getText().split("-");
			String formattedresult = veg[0].trim();
			List<String> neededVeggies = Arrays.asList(itemsNeeded);
			if (formattedresult.equalsIgnoreCase("Potato")) {
				for (int k = 0; k < 2; k++) {
					increment.click();
				}
			}

			if (neededVeggies.contains(formattedresult)) {
				addtocart.get(i).click();
				j++;
				if (j == itemsNeeded.length) {
					break;
				}
			}

		}

		return itemsNeeded;

	}

	public WebElement clickButtons() {
		bag.click();
		// Boolean boo=checkout.
		checkout.click();
		return checkout;

	}

	public List<WebElement> proceedtocheckout() throws InterruptedException {
		addProductToCart();
		clickButtons();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(productname));
		// System.out.println(productname);
		for (int i = 0; i < productname.size(); i++) {
			String products = productname.get(i).getText();
			System.out.println(products);
		}

		return productname;

	}

}
