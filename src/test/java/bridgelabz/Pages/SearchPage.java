package bridgelabz.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {

    private static final Logger log = LogManager.getLogger(SearchPage.class);

    // Locators
    private final By searchInput = By.xpath("//input[@placeholder='Search']");

    private final By searchBtn = By.xpath("//button[@class='btn btn-default btn-lg']");

    private final By searchedTerm = By.xpath("//div[@class='product-thumb']//div[@class='image']//a//img[@title='iMac']");

    private final By productNotFoundErrorMsg = By.xpath("//p[contains(text(),'There is no product that matches the search criter')]");


    public void user_enters_in_search_box(String searchTerm) {
        log.info("Entering search term in search box");

        waitForVisible(searchInput);
        type(searchInput, searchTerm);

        log.info("Search term entered: {}", searchTerm);
    }

    public void user_clicks_on_search_button() {
        log.info("Clicking on Search button");

        waitForClickable(searchBtn);
        click(searchBtn);

        log.info("Search button clicked");
    }

    public boolean user_should_see_search_results_related_to_searchterm(String searchTerm) {

        log.info("Validating search results for search term: {}", searchTerm);

        waitForVisible(searchedTerm);
        WebElement element = getElement(searchedTerm);

        String title = element.getAttribute("title").toLowerCase();
        boolean status = title.contains(searchTerm.toLowerCase());

        log.info("Search result title: {}", title);
        log.info("Search term present in result: {}", status);

        return status;

    }

    public boolean userShouldSeeAProductNotFoundErrorMessage() {
        log.info("Verifying 'Product Not Found' error message");

        waitForVisible(productNotFoundErrorMsg);
        boolean status = isDisplayed(productNotFoundErrorMsg);

        log.info("'Product Not Found' error message displayed: {}", status);
        return status;
    }

    public void user_leaves_search_box_empty() {
        log.info("User leaves search box empty");
        // No action required – input is empty by default
    }
}
