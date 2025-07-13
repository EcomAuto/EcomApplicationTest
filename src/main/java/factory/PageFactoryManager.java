package factory;

import org.openqa.selenium.WebDriver;
import pageClass.CreateAccountPage;

public class PageFactoryManager {

    private final WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public CreateAccountPage getCreateAccountPage() {
        return new CreateAccountPage(driver);
    }

}
