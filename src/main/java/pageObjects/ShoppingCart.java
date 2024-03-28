package pageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class ShoppingCart extends BasePage {

	public ShoppingCart() throws IOException {
		super();
	}

	public WebDriver driver;

	By havePromo = By.linkText("Have a promo code?");
	By promoTextBox = By.cssSelector("input[name='discount_name']");
	By promoAddBtn = By.cssSelector("form[method='post']  span");
	By proceeToCheckoutBtn = By.linkText("PROCEED TO CHECKOUT");
	By deleteItemOne = By.cssSelector(".cart-items .cart-item:nth-of-type(1) .float-xs-left");
	By deleteItemTwo = By.cssSelector(".cart-items .cart-item:nth-of-type(2) .float-xs-left");
	By totalValue = By.cssSelector(".cart-total .value");

	public WebElement getHavePromo() throws IOException {
		this.driver = getDriver();
		return driver.findElement(havePromo);
	}

	public WebElement getPromoTextBox() throws IOException {
		this.driver = getDriver();
		// explicit wait - to wait for the promoTextBox to be visible
		//waitForElementVisible(promoTextBox, 10);
		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(promoTextBox));
		return driver.findElement(promoTextBox);
	}

	public WebElement getPromoAddBtn() throws IOException {
		this.driver = getDriver();
		return driver.findElement(promoAddBtn);
	}

	public WebElement getProceeToCheckoutBtn() throws IOException {
		this.driver = getDriver();
		return driver.findElement(proceeToCheckoutBtn);
	}

	public WebElement getDeleteItemOne() throws IOException {
		this.driver = getDriver();
		return driver.findElement(deleteItemOne);
	}

	public WebElement getDeleteItemTwo() throws IOException {
		this.driver = getDriver();
		return driver.findElement(deleteItemTwo);
	}

	public WebElement getTotalValue() throws IOException {
		this.driver = getDriver();
		return driver.findElement(totalValue);
	}
}
