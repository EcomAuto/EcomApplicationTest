package factory;

import org.openqa.selenium.WebDriver;
import pageClass.CreateAccountPage;
import pageClass.LoginPage;

public class PageFactoryManager {

    private final WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public CreateAccountPage getCreateAccountPage() {
        return new CreateAccountPage(driver);
    }
    
    public LoginPage getLoginPage() {
        return new LoginPage(driver);
    }

}
