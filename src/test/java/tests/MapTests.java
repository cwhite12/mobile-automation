package tests;

import org.testng.annotations.*;
import pages.MapPage;
import utils.DriverManager;

public class MapTests {
    MapPage map;

    @BeforeClass
    public void setup() {
        DriverManager.initDriver();
        map = new MapPage(DriverManager.driver);
    }

    @Test
    public void searchAndSelectLocation() {
        map.openSearch();
        map.enterSearchQuery("Central Park");
        map.selectSearchResult();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}