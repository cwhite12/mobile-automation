package tests;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;
import utils.ExtentManager;
import utils.TestUtils;

import java.lang.reflect.Method;

public class BaseTest {

    protected AppiumDriver driver;
    protected ExtentTest test;

    @BeforeMethod
    public void registerTest(Method method) {
        test = ExtentManager.getReporter().createTest(method.getName());
    }
    @BeforeClass
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        ExtentManager.getReporter();
    }

    @AfterMethod
    public void handleTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = TestUtils.captureScreenshot(result.getName(), driver);
            if (test != null) {
                test.fail(result.getThrowable());
                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @AfterClass
    public void tearDownAll() {

        ExtentManager.getReporter().flush();

        if (driver != null) {
            driver.quit();
        }
    }
}