package utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class DriverManager {
    public static AndroidDriver<MobileElement> driver;

    public static void initDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "emulator-5554");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("appPackage", "net.osmand.plus");
            caps.setCapability("appActivity", "net.osmand.plus.activities.MapActivity");
            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability("noReset", true);

            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        if (driver != null) driver.quit();
    }
}