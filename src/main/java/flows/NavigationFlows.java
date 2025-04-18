package flows;

import io.appium.java_client.AppiumDriver;
import pages.MapPage;
import pages.NavigationPage;
import utils.TestUtils;

public class NavigationFlows {
    private final AppiumDriver driver;
    private final MapPage mapPage;
    private final NavigationPage navigationPage;

    public NavigationFlows(AppiumDriver driver) {
        this.driver = driver;
        this.mapPage = new MapPage(driver);
        this.navigationPage = new NavigationPage(driver);
    }

    public NavigationPage startRouteNavigation(String destination) {
        mapPage.openSearch()
                .enterSearchQuery(destination)
                .selectSearchResultByText(destination)
                .selectSearchResult()
                .startNavigation();

        return new NavigationPage(driver);
    }

    public NavigationPage startRoutNavigationWithExactLocation(String destination) {
        mapPage.openSearch()
                .enterSearchQuery(destination)
                .selectSearchResultByText(destination);
        mapPage.startNavigation();
        return new NavigationPage(driver);
    }
    public MapPage stopNavigation() {
        navigationPage.clickMenuButton()
                .clickNavigationButton()
                .clickStopButtonAndConfirm();
        return new MapPage(driver);
    }

}
