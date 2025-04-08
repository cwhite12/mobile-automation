package tests;

import org.testng.annotations.*;
import pages.LoginPage;
import utils.DriverManager;

public class LoginTests {
    LoginPage login;

    @BeforeClass
    public void setup() {
        DriverManager.initDriver();
        login = new LoginPage(DriverManager.driver);
    }

    @Test
    public void validLoginTest() {
        login.enterUsername("testuser");
        login.enterPassword("testpass");
        login.clickLogin();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}