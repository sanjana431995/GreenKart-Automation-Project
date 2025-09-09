package GreenKart.Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import GreenKart.BaseTest.BaseTest;
import GreenKart.PageObjects.CartPage;
import GreenKart.PageObjects.VegHomePage;

public class HomePageTest extends BaseTest{
	public VegHomePage veghomepage;
	@Test
	public void addVegToCart() throws InterruptedException {
		VegHomePage veghomepage=new  VegHomePage(driver);
		//veghomepage.addProductToCart();
		veghomepage.proceedtocheckout();
		CartPage cartpage=new CartPage(driver);
		String msg=cartpage.selectCheckbox();
		boolean result=msg.startsWith("Thank you,");
		Assert.assertTrue(result);
	}
	
@Test//(groups= {"regression"})
public void withValidCode() throws InterruptedException {
	String promo="rahulshettyacademy";
	VegHomePage veghomepage=new  VegHomePage(driver);
	veghomepage.proceedtocheckout();
	CartPage cartpage=new CartPage(driver);
	String isApply=cartpage.validApplyCode(promo);
	Assert.assertEquals(isApply, "Code applied ..!");
	
	
}
//@Test
//public void rateAfterDiscount() throws InterruptedException {
//	withValidCode();
//	
//}
		
	}

