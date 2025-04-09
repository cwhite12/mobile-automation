package tests;

import org.testng.annotations.*;
import pages.MapPage;
import utils.DriverManager;

public class RouteNavigationTests {
    MapPage map;

    @BeforeClass
    public void setup() {
        DriverManager.initDriver();
        map = new MapPage(DriverManager.driver);
    }

    @Test
    public void startRouteNavigation() {
        map.openSearch();
        map.enterSearchQuery("Times Square");
        map.selectSearchResult();
        map.startNavigation();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
