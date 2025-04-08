package tests;

import org.testng.annotations.*;
import pages.LoginPage;
import pages.MapPage;
import utils.DriverManager;

public class MapTests {
    LoginPage login;
    MapPage map;

    @BeforeClass
    public void setup() {
        DriverManager.initDriver();
        login = new LoginPage(DriverManager.driver);
        map = new MapPage(DriverManager.driver);
        login.enterUsername("testuser");
        login.enterPassword("testpass");
        login.clickLogin();
    }

    @Test
    public void searchValidLocation() {
        map.searchLocation("New York");
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}