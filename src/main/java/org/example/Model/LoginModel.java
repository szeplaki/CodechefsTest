package org.example.Model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModel extends BaseModel {
    @FindBy(xpath = "//*[@id='signup']/div[1]/p")
    private WebElement incorrectLoginMsg;

    public WebElement getLoggedInUserName() {
        return loggedInUserName;
    }

    @FindBy(className = "user")
    private WebElement loggedInUserName;

    public void loginWithProvidedUsernameAndPassword(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        secondLoginButton.click();
    }

    public String getLogoutButtonText() {
        return logoutButton.getText();
    }

    public String getIncorrectLoginMsg() {
        return incorrectLoginMsg.getText();
    }
}
