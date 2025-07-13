package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.out.println("WebDriver is null, cannot capture screenshot.");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);

            Files.createDirectories(destFile.getParentFile().toPath());
            Files.copy(srcFile.toPath(), destFile.toPath());

            return destFile.getAbsolutePath();

        } catch (Exception e) {
            System.out.println("Failed to capture or save screenshot: " + e.getMessage());
            return null;
        }
    }

    public static void captureAndAttachScreenshot(WebDriver driver, String testName) {
        try {
            if (driver != null) {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(testName + " - Screenshot", new ByteArrayInputStream(screenshotBytes));
            } else {
                System.out.println("WebDriver is null, cannot attach screenshot.");
            }
        } catch (Exception e) {
            System.out.println("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }
}
