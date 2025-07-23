package pageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import wait.WaitUtil;

public abstract class BasePage {
    protected WebDriver driver;
    protected WaitUtil waitUtil;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
