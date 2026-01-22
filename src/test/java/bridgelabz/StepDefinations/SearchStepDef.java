package bridgelabz.StepDefinations;

import bridgelabz.Pages.HomePage;
import bridgelabz.Pages.RegisterPage;
import bridgelabz.Pages.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchStepDef {

    private final SearchPage searchPage = new SearchPage();


    @When("user enters {string} in the search box")
    public void user_enters_in_search_box(String searchTerm) {
        searchPage.user_enters_in_search_box(searchTerm);
    }

    @When("user clicks on the search button")
    public void user_clicks_on_search_button() {
        searchPage.user_clicks_on_search_button();
    }

    @Then("user should see search results related to {string}")
    public void user_should_see_search_results_related_to_searchterm(String searchTerm) {
        Assert.assertTrue(searchPage.user_should_see_search_results_related_to_searchterm(searchTerm), " Searched Term is not Displayed ");
    }

    @Then("user should see a message {string}")
    public void user_should_see_message(String message) {
        System.out.println("Validating message: " + message);
    }

    @When("user leaves the search box empty")
    public void user_leaves_search_box_empty() {
        searchPage.user_leaves_search_box_empty();
    }


    @Then("user should see a product not found error message")
    public void userShouldSeeAProductNotFoundErrorMessage() {
        searchPage.userShouldSeeAProductNotFoundErrorMessage();
    }
}