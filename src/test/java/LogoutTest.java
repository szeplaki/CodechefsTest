import org.example.Model.LogoutModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogoutTest {
    private LogoutModel logoutModel;

    @BeforeEach
    public void setProperties() {
        logoutModel = new LogoutModel();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void testLogout() {
        logoutModel.doLogin();

        logoutModel.waitUntilWebElementIsClickable("id", "logout");
        logoutModel.clickOnLogoutButton();

        logoutModel.openBaseUrl();
        logoutModel.waitUntilWebElementIsClickable("id", "go-login");
        Assertions.assertEquals("Login", logoutModel.getLoginButtonText());
    }
}
