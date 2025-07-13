package listeners;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class ScreenshotListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("Allure result directory: " + System.getProperty("allure.results.directory"));
        captureScreenshotIfDriverExists(result, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        captureScreenshotIfDriverExists(result, "SKIPPED");
    }

    private void captureScreenshotIfDriverExists(ITestResult result, String status) {
        String testName = result.getMethod().getMethodName();
        
        WebDriver driver = null;
        Object instance = result.getInstance();
        if (instance instanceof BaseTest) {
            driver = ((BaseTest) instance).getDriver();
        }

        if (driver != null) {
            String path = ScreenshotUtil.captureScreenshot(driver, testName);
            ScreenshotUtil.captureAndAttachScreenshot(driver, testName);
            System.out.println("[" + status + "] Screenshot saved at: " + path);
        } else {
            System.out.println("[" + status + "] WebDriver is null in listener, screenshot not taken.");
        }
    }
}
