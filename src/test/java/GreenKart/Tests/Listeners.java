package GreenKart.Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import GreenKart.BaseTest.BaseTest;
import GreenKart.PageObjects.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportsNG.getReportObject();
ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//thread safe it create uniq thread for each veriable
	@Override
	public void onTestStart(ITestResult result) {
		// System.out.println("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// System.out.println("Test Passed: " + result.getName());

		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println("Test Failed: " + result.getName());
		test.fail(result.getThrowable());
		String filepath = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			filepath = takeSceenshots(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
	}



	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// You can leave this empty if not used
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Execution Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("Test Execution Finished: " + context.getName());
		extent.flush();
	}
}
