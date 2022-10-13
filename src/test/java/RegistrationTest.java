import org.example.FileReader;
import org.example.Model.LoginModel;
import org.example.Model.RegistrationModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationTest {
    private RegistrationModel registrationModel;
    private LoginModel loginModel;

    String testUserName = "autoTest1";
    String testUserEmail = "autotest1@gmail.com";
    String testUserPassword = "MikkaMakka";

    @BeforeEach
    public void setProperties() {
        registrationModel = new RegistrationModel();
        loginModel = new LoginModel();
        registrationModel.openUrlWithSpecificPathAndMaximizeWindowSize("/register");
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void successfulLRegistration(){
        registrationModel.registration(testUserName, testUserEmail, testUserPassword);
        loginModel.getLoginPage();
        loginModel.loginWithProvidedUsernameAndPassword(testUserName,testUserPassword);

        loginModel.waitUntilWebElementIsVisible("className", "user");
        Assertions.assertEquals(testUserName, loginModel.getLoggedInUserName().getText());
    }

    @Test
    public void registrationWithAnExistingUser(){
        registrationModel.registration(FileReader.getValueByKeyFromConfigProperties("codechefs.username"),
                FileReader.getValueByKeyFromConfigProperties("codechefs.email"),
                FileReader.getValueByKeyFromConfigProperties("codechefs.password"));

        registrationModel.waitUntilWebElementIsClickable("xpath", "//*[@id=\"signup\"]/div[1]/p");
        Assertions.assertEquals(registrationModel.getErrorMessage(), "Account already exists");
    }
}
