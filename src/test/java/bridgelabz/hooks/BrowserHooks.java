package bridgelabz.hooks;

import bridgelabz.driver.BrowserContext;
import bridgelabz.utils.ConfigReader;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

public class BrowserHooks {
    private static final Logger log =
            LogManager.getLogger(BrowserHooks.class);

    @Before(order = -1) // MUST run before driver creation
    public void setBrowserFromTestNG() {

        log.info("🔹 BrowserHooks @Before started");
        log.info("🧵 Current Thread: {}", Thread.currentThread().getName());

        String finalBrowser;
        String cmdBrowser = System.getProperty("browser");

        // 1️⃣ PRIORITY 1: Check Command Line (-Dbrowser)
        if (cmdBrowser != null && !cmdBrowser.isBlank()) {
            finalBrowser = cmdBrowser;
            log.info("🚀 Priority 1: Command Line detected. Using: {}", finalBrowser);
        } else {
            // 2️⃣ PRIORITY 2: Check TestNG XML Parameter
            String xmlBrowser = null;
            try {
                XmlTest xmlTest = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
                if (xmlTest != null) {
                    xmlBrowser = xmlTest.getParameter("browser");
                    log.info("📄 TestNG <test> name   : {}", xmlTest.getName());
                }
            } catch (Exception e) {
                log.error("❌ Failed to read TestNG context", e);
            }

            if (xmlBrowser != null && !xmlBrowser.isBlank()) {
                finalBrowser = xmlBrowser;
                log.info("📂 Priority 2: TestNG XML detected. Using: {}", finalBrowser);
            } else {
                // 3️⃣ PRIORITY 3: Fallback to ConfigReader (Properties file or Hardcoded Chrome)
                finalBrowser = ConfigReader.browser();
                log.info("⚙️ Priority 3: Fallback to Config/Default. Using: {}", finalBrowser);
            }
        }

        // 4️⃣ LOCK IT IN: Set the Global Source of Truth
        BrowserContext.setBrowser(finalBrowser.toLowerCase().trim());

        log.info("✅ BrowserContext is now locked to: {}", BrowserContext.getBrowser());
        log.info("🔹 BrowserHooks @Before completed");
    }
}