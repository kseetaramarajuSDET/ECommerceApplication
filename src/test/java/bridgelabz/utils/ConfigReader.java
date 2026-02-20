package bridgelabz.utils;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found");
            }
            PROPERTIES.load(is);
            System.out.println("===========+++++++++++++++++++==========================");
            System.out.println("✅ DEBUG: Properties loaded. Browser key value: " + PROPERTIES.getProperty("browser"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {
    }

    private static String get(String key) {
        // 1. Try to get from System (-Dbrowser)
        String value = System.getProperty(key);

        // 2. If System is null OR empty/blank, try the properties file
        if (value == null || value.isBlank()) {
            value = PROPERTIES.getProperty(key);
        }

        // 3. If it's STILL null/empty (meaning file didn't load or key is missing)
        if (value == null || value.isBlank()) {
            // Hard fallback so the framework doesn't crash or send empty strings
            if (key.equalsIgnoreCase("browser")) return "chrome";
            return "";
        }
        return value.trim();
    }

    public static String browser() {
        System.out.println("DEBUG: System Property: " + System.getProperty("browser"));
        System.out.println("DEBUG: File Property: " + PROPERTIES.getProperty("browser"));
        System.out.println("DEBUG: Total Keys in File: " + PROPERTIES.size());
        String bb = get("browser");
        System.out.println("===========+++++++++++++++++++==========================");
        System.out.println(bb);
        System.out.println("===========+++++++++++++++++++==========================");
        return get("browser");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static boolean isGridEnabled() {
        return Boolean.parseBoolean(get("grid.enabled"));
    }

    public static String gridUrl() {
        String url = get("grid.url");
        if (isGridEnabled() && (url == null || url.isBlank())) {
            throw new RuntimeException("grid.url must be provided when grid.enabled=true");
        }
        return url;
    }

    public static String baseUrl() {
        return get("base.url");
    }

    public static int timeout() {
        return Integer.parseInt(getOrDefault("timeout", "10"));
    }

    private static String getOrDefault(String key, String defaultValue) {
        return System.getProperty(key, PROPERTIES.getProperty(key, defaultValue));
    }

}
