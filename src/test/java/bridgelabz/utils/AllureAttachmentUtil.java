package bridgelabz.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureAttachmentUtil {

    private static final Logger log = LogManager.getLogger(AllureAttachmentUtil.class);

    private AllureAttachmentUtil() {
    }

    public static void attachScreenshot(WebDriver driver) {
        try {
            log.info("Capturing screenshot for Allure");

            byte[] screenshot =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.getLifecycle().addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    "png",
                    screenshot
            );

            log.info("Screenshot attached to Allure successfully");

        } catch (Exception e) {
            log.error("Failed to capture screenshot", e);
        }
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String attachLog(String message) {
        log.info("Allure log attached: {}", message);
        return message;
    }
}
