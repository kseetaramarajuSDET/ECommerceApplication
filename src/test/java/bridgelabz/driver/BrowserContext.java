package bridgelabz.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowserContext {

    private static final Logger log =
            LogManager.getLogger(BrowserContext.class);

    private static final ThreadLocal<String> BROWSER =
            new ThreadLocal<>();

    private BrowserContext() {
    }

    public static void setBrowser(String browser) {
        log.info(
                "🧠 [BrowserContext SET] Browser='{}' | Thread='{}'",
                browser,
                Thread.currentThread().getName()
        );
        BROWSER.set(browser);
    }

    public static String getBrowser() {
        String browser = BROWSER.get();

        log.info(
                "🔍 [BrowserContext GET] Browser='{}' | Thread='{}'",
                browser,
                Thread.currentThread().getName()
        );

        return browser;
    }

    public static void clear() {
        log.info(
                "🧹 [BrowserContext CLEAR] Browser='{}' | Thread='{}'",
                BROWSER.get(),
                Thread.currentThread().getName()
        );
        BROWSER.remove();
    }
}

