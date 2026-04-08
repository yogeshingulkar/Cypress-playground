package base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utilities.ConfigReader;

public class BaseClass {
    // ThreadLocal perfectly mapped driver for completely safe parallel execution
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected Logger log = LogManager.getLogger(this.getClass().getName());
    protected SoftAssert softAssert;

    // Get the driver synchronously
    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        log.info("Starting framework setup for browser: " + browser);
        softAssert = new SoftAssert();

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getProperty("browser");
        }

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*"); // Fix CDP Warnings
                    threadLocalDriver.set(new ChromeDriver(chromeOptions));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    threadLocalDriver.set(new EdgeDriver(edgeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    threadLocalDriver.set(new FirefoxDriver(ffOptions));
                    break;
                default:
                    throw new RuntimeException("Invalid browser defined: " + browser);
            }

            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout"))));

            log.info("Navigating to configured URL");
            getDriver().get(ConfigReader.getProperty("url"));

        } catch (Exception e) {
            log.error("Driver initialization failed: ", e);
            throw new RuntimeException("Driver Initialization Failed", e);
        }
    }

    public void login() {
        LoginPage login = new LoginPage(getDriver());
        login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        log.info("Performed default login process");
    }

    // Add common navigation methods implicitly requested
    public void navigateTo(String url) {
        getDriver().navigate().to(url);
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocalDriver.remove();
            log.info("Browser active session closed");
        }
    }
}