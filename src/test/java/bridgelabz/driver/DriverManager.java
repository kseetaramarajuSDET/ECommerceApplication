package bridgelabz.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final Logger log =
            LogManager.getLogger(DriverManager.class);

    private DriverManager() {
        // Prevent instantiation
    }

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    // ✅ STRICT → use inside Pages / Step Definitions
    public static WebDriver getDriver() {

        WebDriver driver = DRIVER.get();

        if (driver == null) {
            log.error("❌ WebDriver requested but NOT initialized for this thread");
            throw new IllegalStateException(
                    "WebDriver is not initialized. Did you forget to use Hooks?"
            );
        }

        log.debug("WebDriver fetched successfully from ThreadLocal");
        return driver;
    }

    // ✅ SAFE → use ONLY inside hooks
    public static WebDriver getDriverIfPresent() {

        WebDriver driver = DRIVER.get();

        if (driver == null) {
            log.warn("⚠️ WebDriver not present in ThreadLocal (probably setup failed)");
        } else {
            log.debug("WebDriver is present in ThreadLocal");
        }
        return driver;
    }

    public static void setDriver(WebDriver driver) {

        if (driver == null) {
            log.error("❌ Attempted to set NULL WebDriver into ThreadLocal");
            throw new IllegalArgumentException("Driver cannot be null");
        }

        DRIVER.set(driver);
        log.info("✅ WebDriver successfully set into ThreadLocal");
    }

    public static boolean isDriverAlive() {

        boolean alive = DRIVER.get() != null;
        log.debug("WebDriver alive status: {}", alive);
        return alive;
    }

    public static void quitDriver() {

        WebDriver driver = DRIVER.get();

        if (driver != null) {
            log.info("🧹 Quitting WebDriver");
            try {
                driver.quit();
                log.info("✅ WebDriver quit successfully");
            } catch (Exception e) {
                log.error("❌ Error while quitting WebDriver", e);
            } finally {
                DRIVER.remove();
                log.debug("ThreadLocal WebDriver reference removed");
            }
        } else {
            log.warn("⚠️ quitDriver() called but WebDriver was already NULL");
        }
    }
}
