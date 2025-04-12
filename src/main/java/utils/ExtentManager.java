package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extentReports.attachReporter(spark);
    }

    public static ExtentReports getReporter() {
        return extentReports;
    }
}
