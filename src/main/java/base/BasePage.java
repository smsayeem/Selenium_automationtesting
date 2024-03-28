package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class BasePage {

	Properties prop;
	static String PROJECT_DIR = System.getProperty("user.dir");
	static String CONFIG_PROPERTIES_PATH = PROJECT_DIR + "\\src\\main\\java\\resources\\config.properties";
	static String SCREENSHOT_DIR = PROJECT_DIR + "\\target\\screenshots\\";
	static String screenShotDestinationPath;

	// constructor
	public BasePage() throws IOException {
		prop = new Properties();
		FileInputStream data = new FileInputStream(CONFIG_PROPERTIES_PATH);
		prop.load(data);
	}

	public static WebDriver getDriver() throws IOException {
		return WebDriverInstance.getDriver();
	}

	public String getUrl() throws IOException {
		return prop.getProperty("url");
	}

	// this method take snapshot and save in the directory
	public void takeSnapShot(String name) throws IOException {
		File src_File = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		screenShotDestinationPath = SCREENSHOT_DIR + timestamp() + ".png";
		try {
			FileUtils.copyFile(src_File, new File(screenShotDestinationPath));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getScreenShotDestinationPath() {
		return screenShotDestinationPath;
	}

	// generate timestamp to create unique file name for the snapshot files
	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	public static void waitForElementInvisible(WebElement element, int timer) throws IOException {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timer));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitForElementVisible(WebElement element, int timer) throws IOException {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timer));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
