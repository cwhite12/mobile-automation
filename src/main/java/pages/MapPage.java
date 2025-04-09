package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;

public class MapPage {
    AndroidDriver driver;

    public MapPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void openSearch() {
        WebElement searchButton = driver.findElement(By.id("net.osmand.plus:id/map_search_button"));
        searchButton.click();
    }

    public void enterSearchQuery(String query) {
        WebElement searchField = driver.findElement(By.id("net.osmand.plus:id/search_text"));
        searchField.sendKeys(query);
    }

    public void selectSearchResult() {
        WebElement result = driver.findElement(By.id("net.osmand.plus:id/list_text"));
        result.click();
    }

    public void startNavigation() {
        WebElement navBtn = driver.findElement(By.id("net.osmand.plus:id/navigation_button"));
        navBtn.click();
    }
}