package bridgelabz.Pages;

import bridgelabz.utils.CommonUtility;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Map;
import java.util.Set;

public class RegisterPage extends BasePage {

    private static final Logger log = LogManager.getLogger(RegisterPage.class);

    // Locators
    private final By txtFirstName = By.xpath("//input[@id='input-firstname']");

    private final By txtLastName = By.xpath("//input[@id='input-lastname']");
    private final By txtEmail = By.xpath("//input[@id='input-email']");

    private final By txtTelephone = By.xpath("//input[@id='input-telephone']");

    private final By txtPassword = By.xpath("//input[@id='input-password']");
    private final By txtConfirmPassword = By.xpath("//input[@id='input-confirm']");

    private final By btncheckbox = By.xpath("//input[@type='checkbox']");

    private final By btnContinue = By.xpath("//input[@value='Continue']");

    private final By headerEmailAlreadyExist = By.xpath("//div[text()='Warning: E-Mail Address is already registered!']");

    private final By headerAccountCreationSuccessMessage = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");

    private final By passwordErrorMsg = By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]");

    private final By telephoneErrorMsg = By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]");

    private final By emailErrorMsg = By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]");

    private final By firstNameErrorMsg = By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]");

    private final By lastNameErrorMsg = By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]");

    private final By privacyErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");


    public void userEntersAllMandatoryFields() {

        String firstName = CommonUtility.randomString(5).toUpperCase();
        type(txtFirstName, firstName);
        log.info("Entered First Name: " + firstName);

        String lastName = CommonUtility.randomString(7).toUpperCase();
        type(txtLastName, lastName);
        log.info("Entered Last Name: " + lastName);

        String email = CommonUtility.randomString(6) + "@gmail.com";
        type(txtEmail, email);
        log.info("Entered Email: " + email);

        String mobile = CommonUtility.randomNumber(10);
        type(txtTelephone, mobile);
        log.info("Entered Telephone: " + mobile);

        String password = CommonUtility.randomPassword(10);
        type(txtPassword, password);
        type(txtConfirmPassword, password);
        log.info("Entered Password & Confirm Password");

    }

    public void userAgreesToThePrivacyPolicy() {
        waitForClickable(btncheckbox);
        click(btncheckbox);
        log.info("Clicked on Privacy Policy checkbox");
    }

    public void userClicksOnTheContinueButton() {
        waitForClickable(btnContinue);
        click(btnContinue);
        log.info("Clicked on Continue button");
    }

    public boolean userShouldSeeASuccessMessage() {
        log.info("Verifying account creation success message");

        waitForVisible(headerAccountCreationSuccessMessage);
        boolean status = isDisplayed(headerAccountCreationSuccessMessage);

        log.info("Account creation success message displayed status: {}", status);
        return status;
    }

    public void userLeavesAllMandatoryFieldsEmpty() {
        log.info("User leaves all mandatory fields empty");
        // No action required – fields are empty by default
    }

    public boolean userShouldSeeErrorMessagesForRequiredFields() {
        log.info("Verifying error messages for all mandatory fields");

        boolean privacyError = isDisplayed(privacyErrorMsg);
        boolean firstNameError = isDisplayed(firstNameErrorMsg);
        boolean lastNameError = isDisplayed(lastNameErrorMsg);
        boolean emailError = isDisplayed(emailErrorMsg);
        boolean passwordError = isDisplayed(passwordErrorMsg);
        boolean telephoneError = isDisplayed(telephoneErrorMsg);

        log.info("Privacy policy error displayed: {}", privacyError);
        log.info("First name error displayed: {}", firstNameError);
        log.info("Last name error displayed: {}", lastNameError);
        log.info("Email error displayed: {}", emailError);
        log.info("Password error displayed: {}", passwordError);
        log.info("Telephone error displayed: {}", telephoneError);

        boolean allErrorsDisplayed = privacyError && firstNameError &&
                lastNameError && emailError && passwordError && telephoneError;

        log.info("All mandatory field error messages displayed: {}", allErrorsDisplayed);

        return allErrorsDisplayed;
    }
}
