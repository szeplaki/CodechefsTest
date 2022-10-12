package org.example.Model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutModel extends BaseModel {
    @FindBy(xpath = "//*[@id='root']/div/div[1]/nav/a[5]")
    private WebElement loginButtonOnHomePage;

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    public String getLoginButtonText() {
        return loginButtonOnHomePage.getText();
    }
}
