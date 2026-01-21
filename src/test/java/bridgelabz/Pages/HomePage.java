package bridgelabz.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final Logger log = LogManager.getLogger(HomePage.class);

    private final By desktopsLink = By.xpath("//a[normalize-space()='Desktops']");
    private final By myAccountLink = By.xpath("//a[@title='My Account']");
    private final By registerLink = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']");
    private final By loginLink = By.xpath("//a[normalize-space()='Login']");
    private final By loginBtn = By.xpath("//input[@value='Login']");
    private final By myOrdersHeader = By.xpath("//h2[normalize-space()='My Orders']");

    private final By registerHeader = By.xpath("//h1[normalize-space()='Register Account']");


    public void userLandedInHomePage() {
        log.info("Verifying user landed on Home page");
        waitForVisible(desktopsLink);
        log.info("Home page loaded successfully");
    }

    public void userNavigatesToLoginPage() {
        log.info("Navigating to Login page");

        waitForClickable(myAccountLink);
        log.info("Clicking on My Account link");
        click(myAccountLink);

        waitForClickable(loginLink);
        log.info("Clicking on Login link");
        click(loginLink);

        waitForVisible(loginBtn);
        log.info("Login page loaded successfully");
    }

    public void userNavigatesToRegisterPage() {
        log.info("Navigating to Register page");

        waitForClickable(myAccountLink);
        log.info("Clicking on My Account link");
        click(myAccountLink);

        waitForClickable(registerLink);
        log.info("Clicking on Register link");
        click(registerLink);

        waitForVisible(registerHeader);
        log.info("Register page loaded successfully");
    }

    public boolean userNavigatesToHomepage() {
        log.info("Verifying navigation to Home page after login");

        boolean status = isDisplayed(myOrdersHeader);

        log.info("Home page navigation status: {}", status);
        return status;
    }


}
