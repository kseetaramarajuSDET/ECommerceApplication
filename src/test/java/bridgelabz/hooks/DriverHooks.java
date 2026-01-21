package bridgelabz.hooks;

import bridgelabz.utils.ConfigReader;
import bridgelabz.driver.DriverFactory;
import bridgelabz.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class DriverHooks {

    private static final Logger log =
            LogManager.getLogger(DriverHooks.class);

    @Before(order = 0)
    public void setUp() {

        log.info("🚀 Driver setup started");

        try {
            // 1️⃣ Create browser
            log.info("🧩 Initializing WebDriver");
            DriverManager.setDriver(DriverFactory.createDriver());
            log.info("✅ WebDriver initialized successfully");

            // 2️⃣ Browser configurations
            log.info("⏱ Setting implicit wait: {} seconds",
                    ConfigReader.timeout());
            DriverManager.getDriver()
                    .manage()
                    .timeouts()
                    .implicitlyWait(
                            Duration.ofSeconds(ConfigReader.timeout())
                    );

            log.info("🧹 Deleting all cookies");
            DriverManager.getDriver().manage().deleteAllCookies();

            log.info("🖥 Maximizing browser window");
            DriverManager.getDriver().manage().window().maximize();

            // 3️⃣ Launch application
            log.info("🌐 Navigating to URL: {}",
                    ConfigReader.baseUrl());
            DriverManager.getDriver().get(ConfigReader.baseUrl());

            log.info("✅ Driver setup completed successfully");

        } catch (Exception e) {

            log.error("❌ Exception during driver setup", e);

            // Cleanup to avoid half-initialized driver
            DriverManager.quitDriver();

            // 🔥 Rethrow → scenario MUST fail
            throw e;
        }
    }

    @After(order = 0)
    public void tearDown() {

        log.info("🛑 Driver teardown started");

        if (DriverManager.isDriverAlive()) {
            log.info("🧨 Quitting WebDriver");
            DriverManager.quitDriver();
            log.info("✅ WebDriver quit successfully");
        } else {
            log.warn("⚠️ WebDriver already closed. Skipping quit.");
        }
    }
}
