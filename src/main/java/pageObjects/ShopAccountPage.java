package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopAccountPage extends BasePage {

	public ShopAccountPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;

	By heading = By.xpath("//section[@id='main']//h1[1]");
	By signoutBtn = By.cssSelector(".hidden-sm-down.logout");

	public WebElement getHeading() throws IOException {
		this.driver = getDriver();
		return driver.findElement(heading);
	}

	public WebElement getSignoutBtn() throws IOException {
		this.driver = getDriver();
		return driver.findElement(signoutBtn);
	}

}
