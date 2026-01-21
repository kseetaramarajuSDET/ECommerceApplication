package bridgelabz.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    // Locators
    private final By inputEmail = By.id("input-email");
    private final By inputPassword = By.id("input-password");
    private final By btnLogin = By.xpath("//input[@value='Login']");
    private final By headerMyAccount = By.xpath("//h2[normalize-space()='My Account']");
    private final By loginErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

    private final By logoutLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");

    public void userEnterUsernameAndPassword(String email, String password) {
        log.info("Entering login credentials");

        log.info("Entering email address");
        type(inputEmail, email);

        log.info("Entering password");
        type(inputPassword, password);
    }

    public void userClicksOnLoginButton() {
        log.info("Attempting to click Login button");

        waitForClickable(btnLogin);
        click(btnLogin);

        log.info("Login button clicked");
    }

    public boolean isLoginErrorDisplayed() {
        log.info("Checking if login error message is displayed");

        waitForVisible(loginErrorMsg);
        boolean status = isDisplayed(loginErrorMsg);

        log.info("Login error message displayed status: {}", status);
        return status;
    }


    public void logoutIfLoggedIn() {
        log.info("Checking if user is already logged in");

        if (isDisplayed(logoutLink)) {
            log.info("User is logged in. Performing logout");

            waitForClickable(logoutLink);
            click(logoutLink);

            log.info("User logged out successfully");
        } else {
            log.info("User is not logged in. Logout not required");
        }
    }

}
