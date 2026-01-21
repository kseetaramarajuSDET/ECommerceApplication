package bridgelabz.StepDefinations;

import bridgelabz.Pages.HomePage;
import bridgelabz.Pages.LoginPage;
import bridgelabz.Pages.RegisterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Map;
import java.util.Set;

public class RegisterStepDef {

    private final HomePage homePage = new HomePage();
    private final RegisterPage registerPage = new RegisterPage();


    @Given("user is on the registration page")
    public void userIsOnTheRegistrationPage() {
        homePage.userNavigatesToRegisterPage();
    }

    @And("user agrees to the privacy policy")
    public void userAgreesToThePrivacyPolicy() {
        registerPage.userAgreesToThePrivacyPolicy();
    }

    @And("user clicks on the continue button")
    public void userClicksOnTheContinueButton() {
        registerPage.userClicksOnTheContinueButton();
    }

    @Then("user should see a success message")
    public void userShouldSeeASuccessMessage() {
        Assert.assertTrue(registerPage.userShouldSeeASuccessMessage(), " Account Success Message Not Displayed ");
    }

    @When("user leaves all mandatory fields empty")
    public void userLeavesAllMandatoryFieldsEmpty() {
        registerPage.userLeavesAllMandatoryFieldsEmpty();
    }

    @Then("user should see error messages for required fields")
    public void userShouldSeeErrorMessagesForRequiredFields() {
        Assert.assertTrue(registerPage.userShouldSeeErrorMessagesForRequiredFields(), "Error Messages are Not Dispplayed ");
    }

    @When("user enters all mandatory fields")
    public void userEntersAllMandatoryFields() {
        registerPage.userEntersAllMandatoryFields();
    }
}
