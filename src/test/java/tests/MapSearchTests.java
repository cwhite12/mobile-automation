package tests;

import flows.MapSearchFlows;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MapPage;
import pages.SearchPage;

public class MapSearchTests extends BaseTest{

    MapPage mapPage = new MapPage(driver);
    MapSearchFlows mapSearchFlows = new MapSearchFlows(driver);

    @Test
    //@TestCase(key = "QA-T1")
    //@Description("Searching for a location within map mobile app")
    public void searchLocationTest() {
     mapSearchFlows.searchLocation("The Headrow Leeds");

        SearchPage searchPage = new SearchPage(driver);
        boolean resultDisplayed = searchPage.isSearchResultDisplayed("The Headrow");
        Assert.assertTrue(resultDisplayed, "'The Headrow' not found in search results");
    }
    @Test
    //@TestCase(key = "QA-T2")
    //@Description("Searching for a location within map mobile app and selecting a result")
    public void selectSearchResult() {
        mapSearchFlows.searchLocation("The Headrow")
        .selectSearchResultByText("The Headrow").selectSearchResult();
        boolean navigationButtonVisible = mapPage.isNavigationButtonVisible();
        Assert.assertTrue(navigationButtonVisible, "Location pin not visible after selection");
    }
}