package flows;

import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import pages.MapPage;
import pages.SearchPage;

public class MapSearchFlows {
    private AppiumDriver driver;
    private MapPage mapPage;

    public MapSearchFlows(AppiumDriver driver) {
        this.driver = driver;
        this.mapPage = new MapPage(driver);
    }

    public SearchPage searchLocation(String location) {
        mapPage.openSearch()
                .enterSearchQuery(location);
        return new SearchPage(driver);
    }

}
