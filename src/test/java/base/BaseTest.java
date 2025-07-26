package base;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import configuration.ConfigReader;
import driver.DriverFactory;
import io.qameta.allure.Allure;
import logsUtil.CleanupUtil;
import logsUtil.LogUtil;
import util.TestStepTracker;
import utils.AllureCleaner;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger;
    
    public WebDriver getDriver() {
        return driver;
    }
    
    private static final ThreadLocal<String> logFilePathThreadLocal = new ThreadLocal<>();
    
    @BeforeTest
    public void cleanAllureFolder() {
        AllureCleaner.cleanAllureResults();
    }
    
    @BeforeMethod
    public void setUp(ITestResult result) {
    	CleanupUtil.deleteOldLogs();
    	TestStepTracker.reset();
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        logger = LogUtil.getTestCaseLogger(className, methodName);

        logger.info("==== Starting Test: " + className + "." + methodName + " ====");
        driver = DriverFactory.getDriver(ConfigReader.get("browser"));
        driver.manage().window().maximize();
        logger.info("Browser launched and navigated to homepage.");
    }



    @AfterMethod
    public void tearDown(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }

        logger.info("==== Finished Test: " + className + "." + methodName + " ====\n");
    }



}
