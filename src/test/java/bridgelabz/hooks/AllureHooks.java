package bridgelabz.hooks;

import bridgelabz.driver.BrowserContext;
import bridgelabz.driver.DriverManager;
import bridgelabz.utils.AllureAttachmentUtil;
import bridgelabz.utils.AllureEnvironmentUtil;
import bridgelabz.utils.ConfigReader;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureHooks {

    private static final Logger log =
            LogManager.getLogger(AllureHooks.class);

//    @BeforeAll
//    public static void setupAllureEnvironmentOnce() {
//        log.info("🧾 Creating Allure environment details");
//        AllureEnvironmentUtil.createEnvironmentFile();
//        log.info("✅ Allure environment file created");
//    }

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        String browser = BrowserContext.getBrowser() != null ? BrowserContext.getBrowser() : ConfigReader.browser();

        log.info("▶️ Starting Scenario: {} | Browser: {}",
                scenario.getName(), browser);

        // FIX: Add browser as an Allure Parameter.
        // This forces Allure to treat "Scenario A - Chrome" and "Scenario A - Edge" as different tests.
        Allure.parameter("Browser", browser);

        // Keep your labels if you like them for filtering
        Allure.label("browser", browser);

        AllureAttachmentUtil.attachLog(
                "▶️ Starting Scenario: " + scenario.getName() + " | Browser: " + browser
        );
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {

        Status status = scenario.getStatus();
        log.info("⏹ Scenario finished: {} | Status: {}",
                scenario.getName(), status);

        log.info("Cucumber @After hook started for scenario: {}", scenario.getName());
        log.info("Scenario status: {}", status);

        if (status != Status.PASSED) {

            log.error("Scenario ended with status: {} | Name: {}", status, scenario.getName());

            AllureAttachmentUtil.attachLog(
                    "❌ Scenario ended with status: " + status +
                            " | Name: " + scenario.getName()
            );

            if (DriverManager.isDriverAlive()) {
                log.info("Driver is alive. Capturing screenshot for Allure.");

                AllureAttachmentUtil.attachScreenshot(
                        DriverManager.getDriverIfPresent()
                );
                log.info("Capturing screenshot is Sccessfully Completed !!");
            } else {
                log.warn("Driver already closed. Screenshot skipped.");

                AllureAttachmentUtil.attachLog(
                        "⚠️ Driver already closed. Screenshot skipped."
                );
            }

        } else {
            log.info("Scenario PASSED: {}", scenario.getName());

            AllureAttachmentUtil.attachLog(
                    "✅ Scenario PASSED: " + scenario.getName()
            );
        }

        log.info("Cucumber @After hook completed for scenario: {}", scenario.getName());
    }

}
