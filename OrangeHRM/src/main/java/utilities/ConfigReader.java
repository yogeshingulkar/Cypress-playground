package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static Properties prop;
    private static final Logger log = LogManager.getLogger(ConfigReader.class);

    public static Properties loadProperties() {
        if (prop != null)
            return prop;
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("config/config.properties");
            prop.load(fis);
            log.info("Properties file loaded successfully");
        } catch (IOException e) {
            log.error("Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("Could not load config.properties file", e);
        }
        return prop;
    }

    public static String getProperty(String key) {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(key);
    }
}