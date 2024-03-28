package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class OrderFormShippingMethod extends BasePage {
	WebDriver driver;

	public OrderFormShippingMethod() throws IOException {
		super();
	}

	By deliveryMsgTextbox = By.cssSelector("textarea#delivery_message");
	By continueBtn = By.cssSelector("[name='confirmDeliveryOption']");

	public WebElement getDeliveryMsgTextbox() throws IOException {
		this.driver = getDriver();
		return driver.findElement(deliveryMsgTextbox);
	}

	public WebElement getContinueBtn() throws IOException {
		this.driver = getDriver();
		return driver.findElement(continueBtn);
	}

}
