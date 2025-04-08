package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
    AndroidDriver<MobileElement> driver;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void enterUsername(String user) {
        driver.findElementById("com.example:id/username").sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElementById("com.example:id/password").sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElementById("com.example:id/loginBtn").click();
    }
}