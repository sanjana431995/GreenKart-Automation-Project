package GreenKart.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import GreenKart.BaseTest.BaseTest;
import GreenKart.PageObjects.CartPage;
import GreenKart.PageObjects.VegHomePage;

public class NegativeTest  extends BaseTest{

	@Test(groups= {"regression"})//(dependsOnMethods="getMessageOfTerms")
	public void wrongMsg() throws InterruptedException {
			VegHomePage veghomepage=new  VegHomePage(driver);
			//veghomepage.addProductToCart();
			veghomepage.proceedtocheckout();
			CartPage cartpage=new CartPage(driver);
			String msg=cartpage.selectCheckbox();
			boolean result=msg.endsWith("Thank you,");
			Assert.assertFalse(result);
		}
	@Test(retryAnalyzer=Retry.class)//(dependsOnMethods="getMessageOfTerms")
	public void withInvalidCode() throws InterruptedException {
		String promo="sanju123";
		VegHomePage veghomepage=new  VegHomePage(driver);
		veghomepage.proceedtocheckout();
		CartPage cartpage=new CartPage(driver);
		String isApply=cartpage.validApplyCode(promo);
		Assert.assertEquals(isApply, "Invalid code ..!");
		
}
	@Test
	public void getMessageOfTerms() throws InterruptedException {
		//HomePageTest homepagetest = new HomePageTest();
		//homepagetest.withValidCode();
		String promo="rahulshettyacademy";
		VegHomePage veghomepage=new  VegHomePage(driver);
		veghomepage.proceedtocheckout();
		CartPage cartpage=new CartPage(driver);
		String isApply=cartpage.validApplyCode(promo);
		Assert.assertEquals(isApply, "Code applied ..!");
		String errorMesaageOnTerms=cartpage.placeOrderWithoutSelection();
		Assert.assertEquals(errorMesaageOnTerms, "Please accept Terms & Conditions - Required");
	}
}
