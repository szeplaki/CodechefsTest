package org.example.Model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationModel extends BaseModel{

    @FindBy(id="register-username")
    private WebElement userName;

    @FindBy(id="register-email")
    private WebElement userEmail;

    @FindBy(id="register-password")
    private WebElement userPassword;

    @FindBy(id="checky")
    private WebElement termsCheckBox;

    @FindBy(xpath = "//*[@id=\"signup\"]/div[4]/input[4]")
    private WebElement registerBtn;

    @FindBy(xpath = "//*[@id=\"signup\"]/div[1]/p")
    private WebElement errorMessage;

    public void setRegistrationUserName(String userName){
        this.userName.sendKeys(userName);
    }

    public void setRegistrationUserEmail(String userEmail){
        this.userEmail.sendKeys(userEmail);
    }

    public void setRegistrationUserPassword(String userPassword){
        this.userPassword.sendKeys(userPassword);
    }

    public void clickRegistrationBtn(){
        registerBtn.click();
    }

    public void clickTermCheckBox(){
        termsCheckBox.click();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public void registration(String userName, String userEmail, String userPassword){
        setRegistrationUserName(userName);
        setRegistrationUserEmail(userEmail);
        setRegistrationUserPassword(userPassword);
        clickTermCheckBox();
        clickRegistrationBtn();
    }
}
