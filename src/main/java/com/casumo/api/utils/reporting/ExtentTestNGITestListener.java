package com.casumo.api.utils.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ExtentTestNGITestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance("extent.html");
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();
    ExtentTest parent, subChild;

    ITestContext context;

    @Override
    public synchronized void onStart(ITestContext context) {
        this.context = context;
        parent = extent.createTest(context.getName());
        parentTest.set(parent);

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {

    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentTest child = parentTest.get().createNode(getTestName(result), result.getMethod().getDescription());
        test.set(child);
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentTest child = parentTest.get().createNode(getTestName(result), result.getMethod().getDescription());
        test.set(child);
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    private synchronized String getTestName(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        return testName;

    }
}