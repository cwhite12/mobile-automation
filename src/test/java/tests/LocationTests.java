package tests;

import flows.MapSearchFlows;
import flows.NavigationFlows;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MapPage;
import pages.NavigationPage;
import utils.TestUtils;

import java.util.List;

public class LocationTests extends BaseTest {

    MapPage mapPage;
    MapSearchFlows mapSearchFlows;
    NavigationFlows navigationFlows;
    NavigationPage navigationPage;

    @BeforeMethod
    public void setUpPages() {

        mapPage = new MapPage(driver);
        navigationPage = new NavigationPage(driver);
        mapSearchFlows = new MapSearchFlows(driver);
        navigationFlows = new NavigationFlows(driver);
    }

    @Test
    //@TestCase(key = "QA-T6")
    //@Description("Begin journey and change location during navigation)
    public void testRouteRecalculationOnLocationChange() throws InterruptedException {

        navigationFlows.startRouteNavigation("Headingley")
                .clickStartButton();
        Assert.assertTrue(navigationPage.isNavigationStarted(), "Navigation did not start successfully");
        List<String> originalSummaryValues = navigationPage.getRouteSummaryTexts();
        System.out.println("The original summary values of the jourey = " + originalSummaryValues);
        test.info("Journey started!");
        Thread.sleep(10000);

        TestUtils.setMockLocation(driver, 53.72587, -1.36256); //Location set to Castleford
        test.info("Mock location set to Castleford to trigger route recalculation.");

        Thread.sleep(7500);
        List<String> updatedSummaryValues = navigationPage.getRouteSummaryTexts();

        Assert.assertNotEquals(updatedSummaryValues, originalSummaryValues,
                "Route summary values did not update after location change.");
        test.pass("Route changed successfully after location change!");
    }
}
