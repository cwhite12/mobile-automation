package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GestureUtils;
import utils.TestUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NavigationPage {

    private static final By START_BUTTON_LOCATOR = By.id("net.osmand.plus:id/start_button_descr");
    private static final By ADD_STOP_BUTTON_LOCATOR = By.id("net.osmand.plus:id/to_button_image_view");
    private static final By SEARCH_BUTTON_LOCATOR = By.id("net.osmand.plus:id/first_item");
    private static final By SEARCH_TEXTBOX_LOCATOR = By.id("net.osmand.plus:id/searchEditText");
    private static final By ROUTE_SUMMARY_MENU_LOCATOR = By.id("net.osmand.plus:id/map_right_widgets_panel");
    private static final By ROUTE_SUMMARY_MENU_ITEMS_LOCATOR = By.xpath(".//android.widget.LinearLayout[@resource-id='net.osmand.plus:id/container']");
    private static final By MENU_BUTTON_LOCATOR = By.xpath("//android.widget.ImageView[@content-desc=\"Back to menu\"]");
    private static final By NAVIGATION_BUTTON_LOCATOR = By.xpath("//android.widget.ListView[@resource-id=\"net.osmand.plus:id/menuItems\"]/android.widget.LinearLayout[6]");
    private static final By STOP_BUTTON = By.id("net.osmand.plus:id/cancel_button_descr");
    private static final By YES_BUTTON = By.xpath("//android.widget.TextView[@resource-id=\"net.osmand.plus:id/button_text\" and @text=\"Yes\"]");
    private static final By SPEEDOMETER_LOCATOR = By.id("net.osmand.plus:id/speedometer_container");

    AppiumDriver driver;

    public NavigationPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public NavigationPage clickStartButton() {
        WebElement startButton = driver.findElement(START_BUTTON_LOCATOR);
        startButton.click();
        return this;
    }

    public NavigationPage addExtraStop(String secondDestination) {
        WebElement addStopButton = driver.findElement(ADD_STOP_BUTTON_LOCATOR);
        addStopButton.click();
        TestUtils.waitForAllElementsToBeVisible(driver, SEARCH_BUTTON_LOCATOR);
        WebElement searchButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
        searchButton.click();
        WebElement searchTextBox = driver.findElement(SEARCH_TEXTBOX_LOCATOR);
        searchTextBox.click();
        searchTextBox.sendKeys(secondDestination);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.selectSearchResultByText(secondDestination)
                .selectSearchResult();
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.clickStartButton();
        return this;
    }
    public boolean isNavigationStarted() {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isRouteSummaryVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(ROUTE_SUMMARY_MENU_LOCATOR))
                    .isDisplayed();
            List<WebElement> startButtons = driver.findElements(START_BUTTON_LOCATOR);
            boolean isStartButtonVisible = !startButtons.isEmpty() && startButtons.get(0).isDisplayed();

            return isRouteSummaryVisible && !isStartButtonVisible;

    }

    public int getRouteSummaryItemCount() {
        WebElement panel = driver.findElement(ROUTE_SUMMARY_MENU_LOCATOR);

        // Will find only the containers within the panel
        List<WebElement> containers = panel.findElements(
                ROUTE_SUMMARY_MENU_ITEMS_LOCATOR);

        int visibleCount = 0;
        for (WebElement container : containers) {
            if (container.isDisplayed()) {
                visibleCount++;
            }
        }

        return visibleCount;
    }
    public List<String> getRouteSummaryTexts() {
        WebElement panel = driver.findElement(By.id("net.osmand.plus:id/map_right_widgets_panel"));

        // Find all descendant TextViews, not just direct children
        List<WebElement> textViews = panel.findElements(By.xpath(".//android.widget.TextView"));

        List<String> values = new ArrayList<>();
        for (WebElement textView : textViews) {
            if (textView.isDisplayed()) {
                values.add(textView.getText().trim());
            }
        }
        return values;
    }

    public NavigationPage clickMenuButton() {
        GestureUtils gestureUtils = new GestureUtils(driver);
        gestureUtils.tapBottomLeftCornerAndWaitForMenu(MENU_BUTTON_LOCATOR);
        WebElement menuButton = driver.findElement(MENU_BUTTON_LOCATOR);
        menuButton.click();
        return this;
    }

    public NavigationPage clickNavigationButton() {
        TestUtils.waitUntilElementIsVisible(driver, NAVIGATION_BUTTON_LOCATOR);
        WebElement navigationButton = driver.findElement(NAVIGATION_BUTTON_LOCATOR);
        navigationButton.click();
        return this;
    }

    public MapPage clickStopButtonAndConfirm() {
        WebElement stopButton = driver.findElement(STOP_BUTTON);
        stopButton.click();
        WebElement yesButton = TestUtils.waitUntilElementIsVisible(driver,
                YES_BUTTON);
        yesButton.click();
        return new MapPage(driver);
    }


}
