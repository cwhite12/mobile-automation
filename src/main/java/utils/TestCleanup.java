package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class TestCleanup {

    private AppiumDriver driver;

    public TestCleanup(AppiumDriver driver) {
        this.driver = driver;
    }

    /**This was a simple app that required a simple cleanup process, with a bigger app with more flows/pages
     I would implement cleanup methods for each scenario, e.g cleanUpFromCurrentScreen() which would grab screen title
     or relevant element, and would then run a cleanup method for this screen specifically,
     e.g cleanUpFromExampleThirdPage(), cleanupFromExampleFourthPage()*
     */


    public void returnToMapPage() {
        try {
            int maxBackTaps = 6;
            for (int i = 0; i < maxBackTaps; i++) {
                if (isMapPageVisible()) {
                    System.out.println("Returned to map page.");
                    return;
                }
                driver.navigate().back();
                Thread.sleep(500); // small delay to let transitions finish
            }
            System.out.println(" Failed to return to map page in " + maxBackTaps + " attempts.");
        } catch (Exception e) {
            System.out.println("Error in cleanup: " + e.getMessage());
        }
    }

    private boolean isMapPageVisible() {
        try {
            return driver.findElement(By.id("net.osmand.plus:id/map_search_button")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
