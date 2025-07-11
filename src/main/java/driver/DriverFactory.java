package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "F:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
            return new ChromeDriver();
        }
        throw new RuntimeException("Browser not supported: " + browser);
    }
}
