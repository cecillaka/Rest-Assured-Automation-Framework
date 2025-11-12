package com.api.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        loadEnvironmentConfig();
        writeAllureEnvironment(); // ✅ automatically write Allure environment
    }

    private static void loadEnvironmentConfig() {
        properties = new Properties();

        // Read environment from Maven system property: -Denv=qa
        String environment = System.getProperty("env", "dev"); // default = dev
        String configFilePath = String.format("src/test/resources/config-%s.properties", environment);

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
            System.out.println("Loaded config for environment: " + environment);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load config file: " + configFilePath, e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null)
            throw new RuntimeException("❌ Property not found: " + key);
        return value;
    }

    // ✅ Write environment.properties for Allure
    private static void writeAllureEnvironment() {
        Properties props = new Properties();
        props.setProperty("Base URL", get("base.url"));
        props.setProperty("Timeout", get("timeout"));
        props.setProperty("Environment", System.getProperty("env", "dev"));

        try {
            File file = new File("target/allure-results/environment.properties");
            file.getParentFile().mkdirs(); // create folders if missing

            try (FileOutputStream fos = new FileOutputStream(file)) {
                props.store(fos, "Allure environment info");
                System.out.println("✅ Allure environment.properties written successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
