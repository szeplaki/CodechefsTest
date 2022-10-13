package org.example.Model;

import org.example.FileReader;
import org.example.WebDriverService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseModel {
    protected final WebDriver webDriver;
    protected final WebDriverWait driverWait;


    @FindBy(id="login-username")
    protected WebElement usernameField;
    @FindBy(id="login-password")
    protected WebElement passwordField;
    @FindBy(id="go-login")
    protected WebElement firstLoginButton;
    @FindBy(id="submit-login")
    protected WebElement secondLoginButton;
    @FindBy(id = "logout")
    protected WebElement logoutButton;

    public BaseModel() {
        this.webDriver = WebDriverService.getInstance().getWebDriver();
        driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        PageFactory.initElements(webDriver, this);
    }

    protected void clickFirstLoginButton() {
        firstLoginButton.click();
    }

    protected void setUsername(String username) {
        this.usernameField.sendKeys(username);
    }

    protected void setPassword(String password) {
        this.passwordField.sendKeys(password);
    }

    protected void clickOnSecondLoginButton() {
        secondLoginButton.click();
    }

    /**
     * The webDriver waits until the selected webElement is visible, or 15 sec. maximum duration.
     *
     * @param type the type of the element you are looking for: id, xpath, css or className.
     * @param id   the selected element's selector.
     */
    public void waitUntilWebElementIsVisible(String type, String id) {
        switch (type) {
            case "id":
                driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
            case "xpath":
                driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
                break;
            case "css":
                driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(id)));
                break;
            case "className":
                driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(id)));
                break;
        }
    }

    /**
     * The webDriver waits until the selected webElement is clickable, or 15 sec. maximum duration.
     *
     * @param type type the type of the element you are looking for: id, xpath, css or className.
     * @param id   the selected element's selector.
     */
    public void waitUntilWebElementIsClickable(String type, String id) {
        switch (type) {
            case "id":
                driverWait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
                break;
            case "xpath":
                driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(id)));
                break;
            case "css":
                driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(id)));
                break;
            case "className":
                driverWait.until(ExpectedConditions.elementToBeClickable(By.className(id)));
                break;
        }
    }

    public void doLogin() {
        webDriver.navigate().to(FileReader.getValueByKeyFromConfigProperties("codechefs.baseurl") + "/login");
        webDriver.manage().window().maximize();

        clickFirstLoginButton();
        setUsername(FileReader.getValueByKeyFromConfigProperties("codechefs.username"));
        setPassword(FileReader.getValueByKeyFromConfigProperties("codechefs.password"));
        clickOnSecondLoginButton();
    }

    public void openUrlWithSpecificPathAndMaximizeWindowSize(String path) {
        webDriver.get(FileReader.getValueByKeyFromConfigProperties("codechefs.baseurl") + path);
        webDriver.manage().window().maximize();
    }

    public void waitUntilLoggedIn() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
    }

    public void getLoginPage() {
        webDriver.get(FileReader.getValueByKeyFromConfigProperties("codechefs.baseurl") + "/login");
        webDriver.manage().window().maximize();
        waitUntilWebElementIsClickable("xpath", "//*[@id='signup']/div[4]/input[1]");
    }

    public void openBaseUrl() {
        webDriver.get(FileReader.getValueByKeyFromConfigProperties("codechefs.baseurl"));
        webDriver.manage().window().maximize();
    }
}
