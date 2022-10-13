package org.example.Model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutModel extends BaseModel {
    @FindBy(id = "go-login")
    private WebElement loginButtonOnHomePage;

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    public String getLoginButtonText() {
        return loginButtonOnHomePage.getText();
    }
}
