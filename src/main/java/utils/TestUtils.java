 package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class TestUtils {

    public static void captureScreenshot(String testName, AppiumDriver driver) {
        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File srcFile = driver.getScreenshotAs(OutputType.FILE);
            String fileName = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(srcFile, new File(fileName));
            System.out.println("Screenshot saved to: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000);
        AssertionError lastError = null;

        while (System.currentTimeMillis() < endTime) {
            try {
                assertion.run();
                return; // success, assertion passed
            } catch (AssertionError e) {
                lastError = e;
                try {
                    Thread.sleep(pollIntervalMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // restore interrupted status
                    throw new RuntimeException("Thread was interrupted while waiting for assertion", ie);
                }
            }
        }

        // If we get here, all retries failed
        if (lastError != null) {
            throw lastError;
        } else {
            throw new AssertionError("Assertion failed and no AssertionError was captured.");
        }
    }
}