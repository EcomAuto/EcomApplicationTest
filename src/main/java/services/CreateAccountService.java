package services;

import models.UserModel;
import pageClass.CreateAccountPage;

public class CreateAccountService {

    private final CreateAccountPage createAccountPage;

    public CreateAccountService(CreateAccountPage createAccountPage) {
        this.createAccountPage = createAccountPage;
    }

    public void registerNewUser(UserModel user) {
        createAccountPage.fillAccountForm(user);
        createAccountPage.submitForm();
    }

    public String getSuccessMessage() {
        return createAccountPage.getSuccessMessage();
    }
}
