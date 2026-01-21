package bridgelabz.driver;

import bridgelabz.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class DriverFactory {

    private static final Logger log =
            LogManager.getLogger(DriverFactory.class);


    private DriverFactory() {
    }

    public static WebDriver createDriver() {

        String browser = ConfigReader.browser().toLowerCase();
        boolean headless = ConfigReader.headless();
        boolean gridEnabled = ConfigReader.isGridEnabled();

        log.info("🚀 Starting WebDriver creation");
        log.info("Browser       : {}", browser);
        log.info("Headless      : {}", headless);
        log.info("Grid Enabled  : {}", gridEnabled);

        try {
            switch (browser) {

                case "firefox":
                    log.info("Initializing Firefox Driver");
                    FirefoxOptions firefox = new FirefoxOptions();

                    if (headless) {
                        log.info("Running Firefox in headless mode");
                        firefox.addArguments("--headless");
                    }

                    return gridEnabled
                            ? createRemoteDriver(firefox)
                            : new FirefoxDriver(firefox);

                case "edge":
                    log.info("Initializing Edge Driver");
                    EdgeOptions edge = new EdgeOptions();

                    if (headless) {
                        log.info("Running Edge in headless mode");
                        edge.addArguments("--headless");
                    }

                    return gridEnabled
                            ? createRemoteDriver(edge)
                            : new EdgeDriver(edge);

                case "chrome":
                default:
                    log.info("Initializing Chrome Driver");
                    ChromeOptions chrome = new ChromeOptions();

                    chrome.addArguments("--start-maximized");
                    chrome.addArguments("--disable-notifications");

                    if (headless) {
                        log.info("Running Chrome in headless mode");
                        chrome.addArguments("--headless=new");
                    }

                    return gridEnabled
                            ? createRemoteDriver(chrome)
                            : new ChromeDriver(chrome);
            }
        } catch (Exception e) {
            log.error("❌ Failed to initialize WebDriver", e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }


    private static WebDriver createRemoteDriver(MutableCapabilities options)
            throws Exception {

        String gridUrl = ConfigReader.gridUrl();
        log.info("Connecting to Selenium Grid at: {}", gridUrl);

        WebDriver driver =
                new RemoteWebDriver(new URL(gridUrl), options);

        log.info("✅ Remote WebDriver initialized successfully");

        return driver;
    }


}
