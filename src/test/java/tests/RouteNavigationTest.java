package tests;

import flows.NavigationFlows;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MapPage;
import pages.NavigationPage;
import utils.TestUtils;

public class RouteNavigationTest extends BaseTest {

    private MapPage mapPage;
  private  NavigationPage navigationPage;
    private NavigationFlows navigationFlows;

    @BeforeMethod
    public void setUpPages() {
        mapPage = new MapPage(driver);
        navigationPage = new NavigationPage(driver);
        navigationFlows = new NavigationFlows(driver);
    }
//    public NavigationPage startRouteNavigation(String destination) {
//        mapPage.openSearch()
//        .enterSearchQuery(destination)
//                .selectSearchResultByText(destination)
//                .selectSearchResult()
//                .startNavigation();
//
//        return new NavigationPage(driver);
//    }
    @Test
    //@TestCase(key = "QA-T3")
    //@Description("Starting navigation and verifying that navigation has begun)
    public void startNavigationAndVerifyNavigation() {

        navigationFlows.startRouteNavigation("The Headrow")
                .clickStartButton();

        Assert.assertTrue(mapPage.isNavigationUIVisible(), "Navigation did not start as expected.");
    }
    @Test
    //@TestCase(key = "QA-T4")
    //@Description("Starting a journey with multiple stops")
    public void startNavigationWithMultipleStops() {
        navigationFlows.startRoutNavigationWithExactLocation("Leeds Beckett University")
                .addExtraStop("Castleford");

        TestUtils.waitUntilAsserted(() -> {
            int count = navigationPage.getRouteSummaryItemCount();
            Assert.assertEquals(count, 5, "Expected 4 route summary values in the top-right panel.");
        }, 25, 500);

    }
    @Test
    //@TestCase(key = "QA-T4")
    //@Description("Stopping and starting a journey")
    public void startAndStopNavigationTest() {
        navigationFlows.startRouteNavigation("The Headrow")
                .clickStartButton()
                .clickMenuButton()
                .clickNavigationButton()
                .clickStopButtonAndConfirm();
        Assert.assertTrue(mapPage.assertReturnToMapPage(), "Expected previous screen element to disappear.");
    }
}
