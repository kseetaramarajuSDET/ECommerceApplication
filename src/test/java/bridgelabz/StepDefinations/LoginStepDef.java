package bridgelabz.StepDefinations;

import bridgelabz.Pages.HomePage;
import bridgelabz.Pages.LoginPage;
import bridgelabz.utils.YamlReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class LoginStepDef {

    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();


    @And("user enter username {string} and password {string}")
    public void userEnterUsernameAndPassword(String email, String password) {
        loginPage.userEnterUsernameAndPassword(email, password);
    }

    @And("user clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.userClicksOnLoginButton();
    }

    @Then("user should get error message")
    public void userShouldGetErrorMessage() {
        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Expected login failure but succeeded"
        );
    }

    @Then("user login result should be validated {string}")
    public void userLoginResultShouldBeValidated(String result) {
        if (result.equalsIgnoreCase("success")) {
            Assert.assertTrue(
                    homePage.userNavigatesToHomepage(),
                    "Expected login success but failed"
            );
        } else {
            Assert.assertTrue(
                    loginPage.isLoginErrorDisplayed(),
                    "Expected login failure but succeeded"
            );
        }
    }

    @When("user does not enter any credentials and click on login button")
    public void userDoesNotEnterAnyCredentialsAndClickOnLoginButton() {
        loginPage.userClicksOnLoginButton();
    }
}
