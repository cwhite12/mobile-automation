package utils;

import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class GestureUtils {

    private final WebDriverWait wait;
    private final AppiumDriver driver;

    public GestureUtils(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @SneakyThrows
    public void tapBottomLeftCornerAndWaitForMenu(By menuLocator) {
        try {
            Thread.sleep(2000);
            int x = 165;
            int y = 2267;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);

            tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(List.of(tap));

            wait.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));

        } catch (Exception e) {
            System.err.println("Failed to tap or locate menu: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

