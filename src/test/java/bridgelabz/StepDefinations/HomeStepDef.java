package bridgelabz.StepDefinations;

import bridgelabz.Pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HomeStepDef {

    private final HomePage homePage = new HomePage();

    @Given("user landed in dashboard page")
    public void userLandedInDashboardPage() {
        homePage.userLandedInHomePage();
    }

    @When("user navigates to login page")
    public void userNavigatesToLoginPage() {
        homePage.userNavigatesToLoginPage();
    }

    @Then("user navigates to homepage")
    public void userNavigatesToHomepage() {
        Assert.assertTrue(homePage.userNavigatesToHomepage(), "User Login Is UnSuccessful !! ");
    }

}
