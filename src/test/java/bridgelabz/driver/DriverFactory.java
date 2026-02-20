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
import org.testng.annotations.Parameters;

import java.net.URL;

public final class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);


    private DriverFactory() {
    }


    public static WebDriver createDriver() {


        log.info("Thread: {} | BrowserContext = {}", Thread.currentThread().getName(), BrowserContext.getBrowser());


        String browser;
        //Inside DriverFactory.createDriver()
        if (BrowserContext.getBrowser() != null && BrowserContext.getBrowser()!="") {
            browser = BrowserContext.getBrowser();
        } else {
            browser = ConfigReader.browser();
        }

        System.err.println("BrowserContext.getBrowser()" + BrowserContext.getBrowser());
        System.err.println("ConfigReader.browser()" + ConfigReader.browser());

        // No more ternary operators needed here, because Hooks already did the work!
        boolean headless = ConfigReader.headless();
        boolean gridEnabled = ConfigReader.isGridEnabled();
        String gridUrl = ConfigReader.gridUrl();
        String baseUrl = ConfigReader.baseUrl();
        int timeout = ConfigReader.timeout();

        log.info("🚀 Starting WebDriver creation");
        log.info("Browser       : {}", browser);
        log.info("Headless      : {}", headless);
        log.info("Grid Enabled  : {}", gridEnabled);
        log.info("Grid Url : {}", gridUrl);
        log.info("Base Url : {}", baseUrl);
        log.info("TimeOut : {}", timeout);

        System.out.println("-----------------------> ");
        System.err.println("BrowserContext.getBrowser()" + BrowserContext.getBrowser());
        System.err.println("ConfigReader.browser()" + ConfigReader.browser());
        try {
            switch (browser) {

                case "firefox":
                    log.info("Initializing Firefox Driver");
                    FirefoxOptions firefox = new FirefoxOptions();

                    if (headless) {
                        log.info("Running Firefox in headless mode");
                        firefox.addArguments("--headless");
                    }

                    return gridEnabled ? createRemoteDriver(firefox) : new FirefoxDriver(firefox);

                case "edge":
                    log.info("Initializing Edge Driver");
                    EdgeOptions edge = new EdgeOptions();

                    if (headless) {
                        log.info("Running Edge in headless mode");
                        edge.addArguments("--headless");
                    }

                    return gridEnabled ? createRemoteDriver(edge) : new EdgeDriver(edge);

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

                    return gridEnabled ? createRemoteDriver(chrome) : new ChromeDriver(chrome);
            }
        } catch (Exception e) {
            log.error("❌ Failed to initialize WebDriver", e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }


    private static WebDriver createRemoteDriver(MutableCapabilities options) throws Exception {

        String gridUrl = ConfigReader.gridUrl();
        log.info("Connecting to Selenium Grid at: {}", gridUrl);

        WebDriver driver = new RemoteWebDriver(new URL(gridUrl), options);

        log.info("✅ Remote WebDriver initialized successfully");

        return driver;
    }


}
