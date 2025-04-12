package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.time.Duration;

public class MapPage {
    private static final By SPEEDOMETER_LOCATOR = By.id("net.osmand.plus:id/speedometer_container");
    private static final By NAV_ARROW_LOCATOR = By.id("net.osmand.plus:id/widget_top_icon");
    private static final By NAV_BUTTON_LOCATOR = By.id("net.osmand.plus:id/context_menu_directions_button");
    private static final By SEARCH_BUTTON_LOCATOR = By.id("net.osmand.plus:id/map_search_button");
    AppiumDriver driver;

    public MapPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public SearchPage openSearch() {
        WebElement searchButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
        searchButton.click();
        return new SearchPage(driver);
    }

    public boolean isNavigationButtonVisible() {
        WebElement navBtn = driver.findElement(NAV_BUTTON_LOCATOR);
        try {
            return navBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public NavigationPage startNavigation() {
        WebElement navBtn = driver.findElement(NAV_BUTTON_LOCATOR);
        TestUtils.waitUntilElementIsDisplayed(driver, navBtn);
        navBtn.click();
        return new NavigationPage(driver);
    }


    public boolean isNavigationUIVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

            WebElement navArrow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    NAV_ARROW_LOCATOR));

            WebElement speedometerContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    SPEEDOMETER_LOCATOR));

            return navArrow.isDisplayed() && speedometerContainer.isDisplayed();
        } catch (Exception e) {
            System.out.println("Navigation UI elements not visible: " + e.getMessage());
            return false;
        }
    }

    public boolean assertReturnToMapPage() {
        TestUtils.waitUntilElementIsGone(driver, SPEEDOMETER_LOCATOR);
        return true;
    }
}