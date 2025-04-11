package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private AppiumDriver driver;

    private static final By SEARCH_FIELD_LOCATOR = By.xpath("//android.widget.EditText[@resource-id=\"net.osmand.plus:id/searchEditText\"]");
    private static final By RESULTS_LOCATOR = By.id("net.osmand.plus:id/buttonToolbarTitle");
    private static final By LIST_ITEM_LOCATOR = By.id("net.osmand.plus:id/searchListItemLayout");
    public SearchPage(AppiumDriver driver) {
        this.driver = driver;
    }
    public SearchPage enterSearchQuery(String query) {
        WebElement searchField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD_LOCATOR));
//        WebElement searchField = driver.findElement(SEARCH_FIELD_LOCATOR);
        searchField.sendKeys(query);
        TestUtils.waitForAllElementsToBeVisible(driver, SEARCH_FIELD_LOCATOR);
    return this;
    }

    public boolean isSearchResultDisplayed(String resultText) {
        try {
            String xpath = ".//android.widget.TextView[contains(@text, '" + resultText + "')]";
            WebElement result = driver.findElement(By.xpath(xpath));
            return result.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public SearchPage selectSearchResultByText(String visibleText) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(LIST_ITEM_LOCATOR));

        List<WebElement> results = driver.findElements(LIST_ITEM_LOCATOR);

        for (WebElement result : results) {
            try {
                WebElement textView = result.findElement(By.xpath(".//android.widget.TextView[contains(@text, '" + visibleText + "')]"));
                if (textView != null) {
                    result.click();
                    return this;
                }

            } catch (NoSuchElementException ignored) {
            }
        }

        throw new NoSuchElementException("No result with text: " + visibleText);
    }
    public MapPage selectSearchResult() {
        WebElement result = driver.findElement(RESULTS_LOCATOR);
        result.click();
        return new MapPage(driver);
    }
}
