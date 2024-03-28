package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverInstance {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	//Properties prop;
	//String PROJECT_DIR = System.getProperty("user.dir");
	//String CONFIG_PROPERTIES_PATH = PROJECT_DIR + "\\src\\main\\java\\resources\\config.properties";

	public static WebDriver getDriver() {
		if (driver.get() == null) {
			try {
				driver.set(createDriver());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return driver.get();
	}

	public static WebDriver createDriver() throws IOException {

		WebDriver driver = null;
		Properties prop = new Properties();
		FileInputStream data = new FileInputStream(BasePage.CONFIG_PROPERTIES_PATH);
		prop.load(data);

		String browser = prop.getProperty("browser");
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static void cleanupDriver() {
		driver.get().quit();
		driver.remove();
	}

}
