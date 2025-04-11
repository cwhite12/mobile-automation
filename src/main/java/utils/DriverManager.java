package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;

public class DriverManager {
    public static AndroidDriver driver;

    public static void initDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName("emulator-5554")
                    .setAppPackage("net.osmand.plus")
                    .setAppActivity("net.osmand.plus.activities.MainActivity")
                    .setNoReset(true)
                    .setAutomationName("UiAutomator2")
                    .setAutoGrantPermissions(true);

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) driver.quit();
    }
}