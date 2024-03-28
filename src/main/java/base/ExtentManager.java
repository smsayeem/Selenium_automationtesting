package base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends BasePage {
	public static ExtentReports extentReport;
	public static String extentReportsPrefix;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public ExtentManager() throws IOException {
		super();
	}

	public static ExtentReports getReport() {
		if (extentReport == null) {
			setupExtentReport("extent-reports");
		}
		return extentReport;
	}

	public static ExtentReports setupExtentReport(String testName) {
		extentReport = new ExtentReports();
		String fileName = System.getProperty("user.dir") + "/extent_reports/" + extendReportsPrefix_Name(testName) + ".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
		extentReport.attachReporter(spark);


		extentReport.setSystemInfo("Test Engineer", "Sadot Sayeem");
		spark.config().setReportName("Regression Test");
		spark.config().setDocumentTitle("Test Result");
		spark.config().setTheme(Theme.DARK);
		return extentReport;
	}

	// to generate report name with date and time
	public static String extendReportsPrefix_Name(String testName) {
		String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		extentReportsPrefix = testName + "_" + date;
		return extentReportsPrefix;
	}

	public static void flushReport() {
		extentReport.flush();
	}

	public synchronized static ExtentTest getTest() {
		return extentTest.get();
	}

	public synchronized static ExtentTest createTest(String name, String description) {
		ExtentTest test = extentReport.createTest(name, description);
		extentTest.set(test);
		return getTest();
	}

	public synchronized static void log(String message) {
		getTest().info(message);
	}

	public synchronized static void pass(String message) {
		getTest().pass(message);
	}

	public synchronized static void fail(String message) {
		getTest().fail(message);
	}

	public synchronized static void attachImage() {
		getTest().addScreenCaptureFromPath(getScreenShotDestinationPath());
	}

}
