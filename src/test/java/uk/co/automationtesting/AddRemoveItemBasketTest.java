package uk.co.automationtesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class AddRemoveItemBasketTest extends Hooks {
	/*
	 * ->this class extends to [Hooks] class that contains BeforeTest and AfterTest
	 * setup. ->Hooks class also extended to BasePage class providing browser driver
	 * and url. ->BasePage class use getDriver() method to get browser driver from
	 * WebDriverInstance class
	 */
	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void addRemoveItem() throws IOException {
		ExtentManager.log("...Starting AddRemoveItemBasketTest...");

		// Page 1: Homepage
		Homepage home = new Homepage();
		home.getCookie().click();
		home.getTestStoreLink().click();

		// Page 2: ShopHomePage
		ShopHomePage shopHome = new ShopHomePage();
		ExtentManager.pass("Have successfully reached the ShopHomePage");
		shopHome.getProdOne().click();

		// Page 3: ShopProductPage
		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Reached the ShopProductPage");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");

		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added product to basket");

		// Page 4: ShopContentPannel
		ShopContentPannel cPannel = new ShopContentPannel();
		cPannel.getContinueShoppingBtn().click();

		// navigate back to the [ShopProductPage]
		shopProd.getHomepageLink().click();

		// navigate back to the [ShopHomePage]
		shopHome.getProdThree().click();

		// navigate to the [ShopProductPage]
		shopProd.getAddToCartBtn().click();

		// navigate to the [ShopContentPannel]
		cPannel.getCheckoutBtn().click();

		// Page 5: ShoppingCart
		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		// explicit wait for the 'DeleteItemTwo' button to be removed/invisible
		waitForElementInvisible(cart.getDeleteItemTwo(), 10);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));

		// Print the total amount to console
		System.out.println(cart.getTotalValue().getText());

		try {
			// using an assertion to make sure that the total amount is what we are
			// expecting
			Assert.assertEquals(cart.getTotalValue().getText(), "$45.24");
			ExtentManager.pass("The toal amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("Cart did not match the expected amount, it found " + cart.getTotalValue().getText()
					+ " expected $45.24");
			ExtentManager.fail("The total amount did not match the expected amount.");
		}

	}

}
