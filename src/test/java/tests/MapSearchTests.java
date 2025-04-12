package tests;

import flows.MapSearchFlows;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MapPage;
import pages.SearchPage;

public class MapSearchTests extends BaseTest {

    MapPage mapPage;
    MapSearchFlows mapSearchFlows;
    @BeforeMethod
    public void setUpPages() {

        mapPage = new MapPage(driver);
        mapSearchFlows = new MapSearchFlows(driver);
    }

    @Test
    //@TestCase(key = "QA-T1")
    //@Description("Searching for a location within map mobile app")
    public void searchLocationTest() {
        mapSearchFlows.searchLocation("The Headrow Leeds");
        test.info("Searched for location 'The Headrow Leeds'");
        SearchPage searchPage = new SearchPage(driver);
        boolean resultDisplayed = searchPage.isSearchResultDisplayed("The Headrow");
        Assert.assertTrue(resultDisplayed, "'The Headrow' not found in search results");
        test.pass("The headrow was found in search results!");
    }

    @Test
    //@TestCase(key = "QA-T2")
    //@Description("Searching for a location within map mobile app and selecting a result")
    public void selectSearchResult() {
        mapSearchFlows.searchLocation("The Headrow")
                .selectSearchResultByText("The Headrow").selectSearchResult();
        test.info("Searched and selected location 'The Headrow'");
        boolean navigationButtonVisible = mapPage.isNavigationButtonVisible();
        Assert.assertTrue(navigationButtonVisible, "Navigation button is not visible");
        test.pass("The navigation button was visible after selection!");
    }
}