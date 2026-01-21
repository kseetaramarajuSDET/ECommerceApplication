package bridgelabz.utils;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream("config/config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found");
            }
            PROPERTIES.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {
    }

    private static String get(String key) {
        // Command-line override first
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }

    public static String browser() {
        return get("browser");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static boolean isGridEnabled() {
        return Boolean.parseBoolean(get("grid.enabled"));
    }

    public static String gridUrl() {
        return get("grid.url");
    }

    public static String baseUrl() {
        return get("base.url");
    }

    public static int timeout() {
        return Integer.parseInt(get("timeout"));
    }
}
