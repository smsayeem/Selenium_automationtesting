package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BasePage;
import base.ExtentManager;

//Note: we need to add this listener in the test class to make it functional

public class Listener extends BasePage implements ITestListener {
	// ctrl + ITestListener

	public Listener() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public synchronized void onStart(ITestContext context) {
		ExtentManager.getReport();
		ExtentManager.createTest(context.getName(), context.getName());
	}

	public synchronized void onTestFailure(ITestResult result) {
		ExtentManager.getTest().fail(result.getThrowable());
		try {
			System.out.println("Test failed: " + result.getName());
			takeSnapShot(result.getMethod().getMethodName());
			ExtentManager.attachImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void onFinish(ITestContext context) {
		ExtentManager.flushReport();
	}

}
