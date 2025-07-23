package services;

import models.UserModel;
import pageClass.CreateAccountPage;
import pageClass.LoginPage;

public class LoginAccountService {
	
	private final CreateAccountPage createAccountPage;
	private final LoginPage loginPage;

    public LoginAccountService(CreateAccountPage createAccountPage, LoginPage loginPage) {
        this.createAccountPage = createAccountPage;
        this.loginPage = loginPage;
    }

    public void validateLogin(UserModel user) {
        createAccountPage.fillAccountForm(user);
        createAccountPage.submitForm();
        createAccountPage.logOutAfterAccountCreation();
        loginPage.login(user.getEmail(), user.getPassword());
    }

    public String getWelcomeMessage() {
        return loginPage.getWelcomeMessage();
    }

}
