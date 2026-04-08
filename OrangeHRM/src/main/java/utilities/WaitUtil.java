package utilities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    // Dynamic universal wait provider
    public static WebDriverWait getWait(WebDriver driver) {
        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        return getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickable(WebDriver driver, WebElement element) {
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }
}