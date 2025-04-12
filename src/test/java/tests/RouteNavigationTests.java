package tests;

import flows.NavigationFlows;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MapPage;
import pages.NavigationPage;
import utils.TestUtils;

public class RouteNavigationTests extends BaseTest {

    private MapPage mapPage;
    private NavigationPage navigationPage;
    private NavigationFlows navigationFlows;

    @BeforeMethod
    public void setUpPages() {
        mapPage = new MapPage(driver);
        navigationPage = new NavigationPage(driver);
        navigationFlows = new NavigationFlows(driver);
    }

    @Test
    //@TestCase(key = "QA-T3")
    //@Description("Starting navigation and verifying that navigation has begun)
    public void startNavigationAndVerifyNavigation() {

        navigationFlows.startRouteNavigation("The Headrow")
                .clickStartButton();

        Assert.assertTrue(mapPage.isNavigationUIVisible(), "Navigation did not start as expected.");
        test.pass("Navigation did start as expected!");
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
        test.pass("All elements on navigation page are visible!");
    }

    @Test
    //@TestCase(key = "QA-T5")
    //@Description("Stopping and starting a journey")
    public void startAndStopNavigationTest() {
        navigationFlows.startRouteNavigation("The Headrow")
                .clickStartButton()
                .clickMenuButton()
                .clickNavigationButton()
                .clickStopButtonAndConfirm();
        Assert.assertTrue(mapPage.assertReturnToMapPage(), "Expected previous screen element to disappear.");
        test.pass("Successfully stopped and started a journey!");
    }
}
