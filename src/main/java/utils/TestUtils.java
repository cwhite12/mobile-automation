package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;

public class TestUtils {
    public static String captureScreenshot(String name) {
        try {
            File srcFile = ((TakesScreenshot) DriverManager.driver).getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + name + ".png";
            File targetFile = new File(path);
            Files.copy(srcFile.toPath(), targetFile.toPath());
            return path;
        } catch (Exception e) {
            return null;
        }
    }
}