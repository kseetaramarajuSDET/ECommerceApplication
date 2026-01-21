package bridgelabz.Pages;

import bridgelabz.utils.ConfigReader;
import bridgelabz.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    protected static final Logger log = LogManager.getLogger(BasePage.class);

    protected BasePage() {
        if (!DriverManager.isDriverAlive()) {
            throw new IllegalStateException(
                    "Driver not initialized before Page Object creation"
            );
        }
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.timeout())
        );
        this.actions = new Actions(driver);
    }


    /* ================= WAIT METHODS ================= */

    protected WebElement waitForVisible(By locator) {
        log.info("Waiting for visibility of element: {}", locator);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.error("Element NOT visible after timeout: {}", locator);
            throw e; // 🔥 IMPORTANT → rethrow
        }
    }


    protected WebElement waitForClickable(By locator) {
        log.info("Waiting for element to be clickable: {}", locator);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            log.error("Element NOT clickable after timeout: {}", locator);
            throw e; // 🔥 MUST rethrow → scenario should FAIL
        }
    }

    /* ================= ACTION METHODS ================= */

    protected void type(By locator, String text) {
        WebElement element = waitForVisible(locator);
        log.info("Typing '{}' into {}", text, locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        try {
            log.info("Clicking on element: {}", locator);
            waitForClickable(locator).click();
        } catch (Exception e) {
            log.warn("Normal click failed, trying JS click: {}", locator);
            jsClick(locator);
        }
    }

    protected void jsClick(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    /* ================= STATE CHECKS ================= */

    protected boolean isDisplayed(By locator) {
        log.info("Checking visibility of element: {}", locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            log.warn("Element NOT visible: {}", locator);
            return false;
        }
    }

    protected boolean isEnabled(By locator) {
        try {
            boolean enabled = waitForVisible(locator).isEnabled();
            log.info("Element enabled {} -> {}", enabled, locator);
            return enabled;
        } catch (Exception e) {
            log.warn("Element not enabled: {}", locator);
            return false;
        }
    }

    /* ================= NAVIGATION ================= */

    protected void navigateTo(String url) {
        log.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    protected String getTitle() {
        log.info("Fetching page title");
        String title = driver.getTitle();
        log.info("Page title is: {}", title);
        return title;
    }

    protected WebElement getElement(By by) {
        log.info("Locating element using: {}", by);
        WebElement element = driver.findElement(by);
        log.info("Element located successfully: {}", by);
        return element;
    }


}
