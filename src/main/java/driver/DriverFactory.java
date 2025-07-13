package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import exceptions.DriverNotSupprtedException;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "F:\\ChromeDriver\\138\\chromedriver-win64\\chromedriver.exe");
            return new ChromeDriver();
        }
        throw new DriverNotSupprtedException("Browser not supported: " + browser);
    }
}
