package bridgelabz.utils;

import bridgelabz.driver.BrowserContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

public class AllureEnvironmentUtil {
    private static final Logger log = LogManager.getLogger(AllureEnvironmentUtil.class);

    public static void createEnvironmentFile(String browserValue, String platform) {
        try {
            Properties props = new Properties();

            props.setProperty("Application", "OpenCart");
            props.setProperty("Environment", System.getProperty("env", "QA"));
            props.setProperty("OS", platform);
            props.setProperty("User", System.getProperty("user.name"));
            props.setProperty("Java Version", System.getProperty("java.version"));
            props.setProperty("Browser", browserValue);
            props.setProperty("Execution Mode", browserValue.contains("Cross") ? "Multi-Browser" : "Single-Browser");
            System.getProperty("java.version");
            props.setProperty("Grid Enabled", String.valueOf(ConfigReader.isGridEnabled()));


            File dir = new File("target/allure-results");
            if (!dir.exists()) dir.mkdirs();

            FileWriter writer = new FileWriter("target/allure-results/environment.properties");
            props.store(writer, "Allure Environment");
            writer.close();

            log.info("✅ environment.properties created at the end of run.");
        } catch (Exception e) {
            log.error("❌ Failed to write environment file at the end", e);
            e.printStackTrace();
        }
    }
}
