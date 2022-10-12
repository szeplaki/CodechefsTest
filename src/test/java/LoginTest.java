import org.example.FileReader;
import org.example.Model.LoginModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private LoginModel loginModel;

    @BeforeEach
    public void setProperties() {
        loginModel = new LoginModel();
        loginModel.getLoginPage();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void successfulLogin() {
        loginModel.loginWithProvidedUsernameAndPassword(FileReader.getValueByKeyFromConfigProperties("codechefs.username"),
                FileReader.getValueByKeyFromConfigProperties("codechefs.password"));

        loginModel.waitUntilWebElementIsVisible("className", "user");
        Assertions.assertEquals(FileReader.getValueByKeyFromConfigProperties("codechefs.username"), loginModel.getLoggedInUserName().getText());
    }

    @Test
    public void loginWithInvalidUsername() {
        loginModel.loginWithProvidedUsernameAndPassword("whatever",
                FileReader.getValueByKeyFromConfigProperties("codechefs.password"));

        loginModel.waitUntilWebElementIsVisible("xpath", "//*[@id='signup']/div[1]/p");
        Assertions.assertEquals(loginModel.getIncorrectLoginMsg(), "username or password incorrect");

        loginModel.doLogin();
    }

    @Test
    public void loginWithInvalidPassword() {
        loginModel.loginWithProvidedUsernameAndPassword(FileReader.getValueByKeyFromConfigProperties("codechefs.username"),
                "whatever");

        loginModel.waitUntilWebElementIsVisible("xpath", "//*[@id='signup']/div[1]/p");
        Assertions.assertEquals(loginModel.getIncorrectLoginMsg(), "username or password incorrect");

        loginModel.doLogin();
    }
}
