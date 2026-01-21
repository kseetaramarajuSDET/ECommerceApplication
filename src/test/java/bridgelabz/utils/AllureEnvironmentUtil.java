package bridgelabz.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

public class AllureEnvironmentUtil {

    public static void createEnvironmentFile() {
        try {
            Properties props = new Properties();

            props.setProperty("Application", "OpenCart");
            props.setProperty("Environment",
                    System.getProperty("env", "QA"));
            props.setProperty("Browser",
                    System.getProperty("browser", "Chrome"));
            props.setProperty("OS",
                    System.getProperty("os.name"));
            props.setProperty("User",
                    System.getProperty("user.name"));
            props.setProperty("Java Version",
                    System.getProperty("java.version"));

            File dir = new File("target/allure-results");
            if (!dir.exists()) dir.mkdirs();

            FileWriter writer =
                    new FileWriter("target/allure-results/environment.properties");
            props.store(writer, "Allure Environment");
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
