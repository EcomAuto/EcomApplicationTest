package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import utils.ScreenshotUtil;

public class ScreenshotListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String path = ScreenshotUtil.captureScreenshot(driver, testName);
        ScreenshotUtil.captureAndAttachScreenshot(driver, testName);
        System.out.println(" Screenshot captured at: " + path);
    }
}

