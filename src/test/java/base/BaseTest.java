package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import configuration.ConfigReader;
import driver.DriverFactory;

public class BaseTest {
    protected WebDriver driver;
    
    public WebDriver getDriver() {
        return driver;
    }
    
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver(ConfigReader.get("browser"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        if (driver != null) {
        	Thread.sleep(2000);
            driver.quit();
        }
    }
}
