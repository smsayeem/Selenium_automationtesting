package pageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class ShopContentPannel extends BasePage {
	WebDriver driver;

	public ShopContentPannel() throws IOException {
		super();
	}

	By continueShoppingBtn = By.xpath("//button[contains(text(), 'Continue')]");
	By checkoutBtn = By.linkText("PROCEED TO CHECKOUT");

	public WebElement getContinueShoppingBtn() throws IOException {
		this.driver = getDriver();
		// explicit wait - to wait for the checkoutBtn to be click-able
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingBtn));
		return driver.findElement(continueShoppingBtn);
	}

	public WebElement getCheckoutBtn() throws IOException {
		this.driver = getDriver();
		// explicit wait - to wait for the checkoutBtn to be click-able
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
		return driver.findElement(checkoutBtn);
	}
}
