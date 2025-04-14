package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.Location;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class TestUtils {

    public static String captureScreenshot(String testName, AppiumDriver driver) {
        String filePath = null;
        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File srcFile = driver.getScreenshotAs(OutputType.FILE);
            filePath = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved to: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static boolean waitUntilElementIsGone(AppiumDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForAllElementsToBeVisible(AppiumDriver driver, By locator, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForAllElementsToBeVisible(AppiumDriver driver, By locator) {
        waitForAllElementsToBeVisible(driver, locator, Duration.ofSeconds(10));
    }

    public static WebElement waitToBeClickable(AppiumDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitUntilElementIsDisplayed(AppiumDriver driver, WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(driver1 -> element.isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement waitUntilElementIsVisible(AppiumDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilAsserted(Runnable assertion, int timeoutInSeconds, int pollIntervalMillis) {
        long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000L);
        AssertionError lastError = null;

        while (System.currentTimeMillis() < endTime) {
            try {
                assertion.run();
                return;
            } catch (AssertionError e) {
                lastError = e;
                try {
                    Thread.sleep(pollIntervalMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread was interrupted while waiting for assertion", ie);
                }
            }
        }

        // retries have all failed at this point
        if (lastError != null) {
            throw lastError;
        } else {
            throw new AssertionError("Assertion failed and no AssertionError was captured.");
        }
    }
    public static void setMockLocation(AppiumDriver driver, double lat, double lon) {
        try {
            ((AndroidDriver) driver).setLocation(new Location(lat, lon));
            System.out.println("Mock location set to: " + lat + ", " + lon);
        } catch (Exception e) {
            System.out.println("Failed to set mock location: " + e.getMessage());
        }
    }
}