package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MapPage {
    AndroidDriver<MobileElement> driver;

    public MapPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void searchLocation(String location) {
        driver.findElementById("//android.widget.TextView[@text=\"Search here\"]").sendKeys(location);
        driver.findElementById("com.example:id/searchBtn").click();
    }
    public void selectLocation(String location) {
        driver.findElementById("//android.widget.LinearLayout[@resource-id=\"com.google.android.apps.maps:id/compass_container\"]/android.widget.LinearLayout00");
    }
    public void startNavigation() {
        driver.findElementById("com.example:id/navigationBtn").click();
    }
}