package listeners;

import base.BaseTest;
import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class ScreenshotListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String logPath = "logs/" + className + "_" + methodName + ".log";

        File logFile = new File(logPath);
        if (logFile.exists()) {
            try (FileInputStream fis = new FileInputStream(logFile)) {
                Allure.addAttachment("Log - " + methodName, fis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
			captureScreenshotIfDriverExists(result, "FAILED");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
			captureScreenshotIfDriverExists(result, "SKIPPED");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    private void captureScreenshotIfDriverExists(ITestResult result, String status) throws FileNotFoundException {
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
