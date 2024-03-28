package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersonalInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPannel;
import pageObjects.ShopHomePage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listener.class)

public class OrderCompleteTest extends Hooks {
	/*
	 * ->this class extends to [Hooks] class that contains BeforeTest and AfterTest
	 * setup. ->Hooks class also extended to BasePage class providing browser driver
	 * and url. ->BasePage class use getDriver() method to get browser driver from
	 * WebDriverInstance class
	 */

	public OrderCompleteTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void endToEndTest() throws IOException {
		ExtentManager.log("...Starting OrderCompleteTest...");
		// Page 1: Homepage
		Homepage home = new Homepage();
		home.getCookie().click();
		home.getTestStoreLink().click();

		// Page 2: ShopHomePage
		ShopHomePage shopHome = new ShopHomePage();
		ExtentManager.pass("Have successfully reached the ShopHomePage");
		shopHome.getProdOne().click();
		ExtentManager.pass("Have successfully clicked on product");

		// Page 3: ShopProductPage
		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Have successfully reached the ShopProductPage");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");

		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added product to basket");

		// Page 4: ShopContentPannel
		ShopContentPannel cPannel = new ShopContentPannel();
		cPannel.getCheckoutBtn().click();

		// Page 5: ShoppingCart
		ShoppingCart cart = new ShoppingCart();
		ExtentManager.pass("Have successfully reached the ShoppingCart");
		cart.getHavePromo().click();
		ExtentManager.pass("Have successfully selected promo button");		
		// explicit wait for the 'promoTextBox' to be visible
		waitForElementVisible(cart.getPromoTextBox(), 10);
		cart.getPromoTextBox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceeToCheckoutBtn().click();
		ExtentManager.pass("Have successfully selected checkout button");

		// Page 6: OrderFormPersonalInfo
		OrderFormPersonalInfo pInfo = new OrderFormPersonalInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("Sam");
		pInfo.getLastnameField().sendKeys("Smith");
		pInfo.getEmailField().sendKeys("samsmith@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered customer details");

		// Page 7: OrderFormDelivery
		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Georges Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered delivery details");

		// Page 8: OrderFormShippingMethod
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("Please ship as quickly as possible. Thank you!");
		shipMethod.getContinueBtn().click();
		ExtentManager.pass("Have successfully selected shipping method");

		// Page 9: OrderFormPayment
		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		ExtentManager.pass("Have successfully placed order");
	}
}
