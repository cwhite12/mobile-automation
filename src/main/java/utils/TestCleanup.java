package utils;

import flows.NavigationFlows;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestCleanup {

    private AppiumDriver driver;
    private static final By SPEEDOMETER_LOCATOR = By.id("net.osmand.plus:id/speedometer_container");
    private static final By SEARCH_BUTTON_LOCATOR = By.id("net.osmand.plus:id/map_search_button");

    public TestCleanup(AppiumDriver driver) {
        this.driver = driver;
    }

    /**This was a simple app that required a simple cleanup process, with a bigger app with more flows/pages
     I would implement cleanup methods for each scenario, e.g cleanUpFromCurrentScreen() which would grab screen title
     or relevant element, and would then run a cleanup method for this screen specifically,
     e.g cleanUpFromExampleThirdPage(), cleanupFromExampleFourthPage()*
     */


    public static void cleanupToMap(AppiumDriver driver) {
        try {

            int maxAttempts = 5;
            while (!isElementVisible(driver, SEARCH_BUTTON_LOCATOR) && maxAttempts-- > 0) {
                driver.navigate().back();
                Thread.sleep(500);
            }

            if (isElementVisible(driver, SEARCH_BUTTON_LOCATOR)) {
                System.out.println("Returned to map page.");
            } else {
                System.out.println("Warning: search button not visible. Map page not confirmed.");
            }

            if (isElementVisible(driver, SPEEDOMETER_LOCATOR)) {
                System.out.println("Speedometer is still visible – attempting to stop navigation.");
                NavigationFlows navigationFlows = new NavigationFlows(driver);
                navigationFlows.stopNavigation();
            }

        } catch (Exception e) {
            System.out.println("Cleanup failed: " + e.getMessage());
        }
    }

    private static boolean isElementVisible(AppiumDriver driver, By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
