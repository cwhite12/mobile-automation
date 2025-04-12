package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;


    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("OsmAnd Mobile Test Report");
            spark.config().setReportName("Mobile Automation Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Environment", "Android 12 Emulator");
            extent.setSystemInfo("App Version", "4.5.5");
            extent.setSystemInfo("Tester", "Conor White");
        }
        return extent;
    }

}
