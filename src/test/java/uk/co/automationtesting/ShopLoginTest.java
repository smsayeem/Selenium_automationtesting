package uk.co.automationtesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopAccountPage;
import pageObjects.ShopHomePage;
import pageObjects.ShopLoginPage;

@Listeners(resources.Listener.class)

public class ShopLoginTest extends Hooks{

	public ShopLoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void login() throws IOException, InterruptedException {
		ExtentManager.log("Starting ShopLogin Test...");
		FileInputStream workbookLocation = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\credentials.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		/****************************************************************************
		 * Excel Spreadsheet Layout Reminder (teaching purposes only)
		 * 
		 * |Row=0 -->| Email Address (Cell 0) Password (Cell 1) *
		 * -------------------------------------------------------------------- 
		 * |Row=1 -->| test@test.com (Cell 0) test123 (Cell 1) 
		 * |Row=2 -->| john.smith@test.com (Cell 0) test123 (Cell 1)
		 * |Row=3 -->| lucy.jones@test.com (Cell 0) catlover1 (Cell 1) 
		 * |Row=4 -->| martin.brian@test.com (Cell 0) ilovepasta5 (Cell 1) 
		 ****************************************************************************/
		
		// row 0 is the table header
		Row r1 = sheet.getRow(1);
		Row r2 = sheet.getRow(2);
		
		Cell c0r1 = r1.getCell(0);
		Cell c1r1 = r1.getCell(1);
		Cell c0r2 = r2.getCell(0);
		Cell c1r2 = r2.getCell(1);
		
		
		String email1 = c0r1.toString();
		String password1 = c1r1.toString();
		String email2 = c0r2.toString();
		String password2 = c1r2.toString();
		
		System.out.println("Email 1= "+email1);
		System.out.println("Password 1= "+password1);
		System.out.println("Email 2= "+email2);
		System.out.println("Password 2= "+password2);
		
		
		// Page 1: Homepage
		Homepage home = new Homepage();
		System.out.println("Reached Home page");
		home.getCookie().click();
		home.getTestStoreLink().click();
	
		// Page 2: ShopHomePage
		ShopHomePage shopHome = new ShopHomePage();
		ExtentManager.pass("Have successfully reached the ShopHomePage");
		System.out.println("Reached Shophome page");
		shopHome.getLoginBtn().click();
		System.out.println("login btn clicked");
		
		// Page 3: ShopLoginPage
		ShopLoginPage login = new ShopLoginPage();
		System.out.println("Reached Login page");
		ExtentManager.pass("Have successfully reached the Login Page");
		login.getEmail().sendKeys(email1);
		login.getPassword().sendKeys(password1);
		login.getSubmitBtn().click();
		
		// Page 4: ShopAccountPage
		ShopAccountPage account = new ShopAccountPage();
		System.out.println("Reached Account page");
		try {
			account.getSignoutBtn().click();
			ExtentManager.pass("User has successfully signed in");
			
		}catch(Exception e) {
			ExtentManager.fail("User could not sign in");
			Assert.fail();
		}
		/*
		shopHome.getLoginBtn().click();
		System.out.println("Reached Login page again for second attempt");
		login.getEmail().sendKeys(email2);
		login.getPassword().sendKeys(password2);
		login.getSubmitBtn().click();
		try {
			account.getSignoutBtn().click();
			ExtentManager.pass("User has successfully signed in");
			
		}catch(Exception e) {
			ExtentManager.fail("User could not sign in");
			Assert.fail();
		}
		*/
		
	}
	
	
	
	
	
}
