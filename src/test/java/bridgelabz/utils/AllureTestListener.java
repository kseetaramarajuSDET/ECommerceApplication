package bridgelabz.utils;

import bridgelabz.driver.BrowserContext;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.TestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

import java.util.List;

public class AllureTestListener implements TestLifecycleListener, ITestListener {
    private static final Logger log = LogManager.getLogger(AllureTestListener.class);

    @Override
    public void beforeTestStop(TestResult result) {
        String browser = BrowserContext.getBrowser() != null ? BrowserContext.getBrowser() : ConfigReader.browser();

        // 1. Detect if we are in a Parallel Suite via TestNG XML
        boolean isParallelRun = false;
        try {
            XmlTest xmlTest = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
            // If the XML has the 'browser' parameter, it's one of our parallel tests
            if (xmlTest != null && xmlTest.getParameter("browser") != null) {
                isParallelRun = true;
            }
        } catch (Exception e) {
            log.debug("Could not determine TestNG context, defaulting to standard behavior.");
        }

        // 2. CONDITION: Only run naming logic if it's actually a parallel suite run
        if (isParallelRun && browser != null) {
            log.info("🎯 Parallel Mode: Updating Allure result for browser: {}", browser);

            // 1. Visbile Name update
            String browserTag = " [" + browser.toUpperCase() + "]";
            result.setName(result.getName() + browserTag);

            // 2. Identify if it has examples (Outline)
            List<Parameter> allureParams = result.getParameters();


            boolean isScenarioOutline = (allureParams != null && !allureParams.isEmpty());

            // 3. build the string from EXISTING parameters only
            String paramsData = "";
            if (isScenarioOutline) {
                StringBuilder dataBuilder = new StringBuilder();
                for (Parameter p : allureParams) {
                    if (dataBuilder.length() > 0) dataBuilder.append("_");
                    dataBuilder.append(p.getValue());
                }
                paramsData = "_" + dataBuilder.toString();
            }

            // 4. Set unique FullName using just the browserTag and existing data
            // This line alone handles the 33/33 count issue
            result.setFullName(result.getFullName() + browserTag + paramsData);

            // 5. Set History ID
            result.setHistoryId(result.getName() + "_" + paramsData);

            log.info("✅ Allure Identity updated for parallel execution.");
            BrowserContext.clear();
        } else {
            // This is a normal run - we do nothing so Allure stays clean
            log.info("🍃 Non-Parallel Mode: Skipping Allure interceptor for {}", result.getName());
            // Important to still clear the context to prevent memory leaks
            BrowserContext.clear();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("🏁 Test Suite finished. Generating Allure environment file...");

        // Use the context provided by TestNG directly!
        XmlTest xmlTest = context.getCurrentXmlTest();
        String cmdBrowser = System.getProperty("browser");
        String browserValue = "Cross-Browser Suite";

        // 1. Get the OS Platform from XML parameter (fallback to system default)
        String xmlPlatform = (xmlTest != null) ? xmlTest.getParameter("os") : null;
        String finalPlatform = (xmlPlatform != null) ? xmlPlatform : System.getProperty("os.name");

        // Logic: If CMD browser exists OR XML has no 'browser' param -> Single Run
        if ((cmdBrowser != null && !cmdBrowser.isBlank()) || (xmlTest != null && xmlTest.getParameter("browser") == null)) {

            browserValue = (cmdBrowser != null && !cmdBrowser.isBlank()) ? cmdBrowser.toUpperCase() : ConfigReader.browser().toUpperCase();
        }
        log.info("📊 Final Browser for Allure: {}", browserValue);

        // Now call your Util with this verified data
        AllureEnvironmentUtil.createEnvironmentFile(browserValue, finalPlatform);

    }


}