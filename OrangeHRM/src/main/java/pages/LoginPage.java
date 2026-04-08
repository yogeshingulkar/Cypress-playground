package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;

public class LoginPage {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Invalid credentials'] | //p[contains(@class, 'oxd-text') and text()='Invalid credentials']")
    private WebElement invalidCredentialsMessage;

    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredMessage;
    
    // User Profile for Logout
    @FindBy(xpath = "//span[contains(@class, 'oxd-userdropdown-tab')]")
    private WebElement userProfileDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String user) {
        try {
            WaitUtil.waitForVisibility(driver, usernameInput).clear();
            usernameInput.sendKeys(user);
            log.info("Entered username");
        } catch (Exception e) {
            log.error("Failed entering username", e);
        }
    }

    public void enterPassword(String pass) {
        WaitUtil.waitForVisibility(driver, passwordInput).clear();
        passwordInput.sendKeys(pass);
        log.info("Entered password");
    }

    public void clickLogin() {
        WaitUtil.waitForClickable(driver, loginButton).click();
        log.info("Clicked login button");
    }

    public boolean isInvalidCredentialsMessageDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, invalidCredentialsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRequiredMessageDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, requiredMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public DashboardPage login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
        return new DashboardPage(driver);
    }

    public void performLogout() {
        try {
            WaitUtil.waitForClickable(driver, userProfileDropdown).click();
            WaitUtil.waitForClickable(driver, logoutLink).click();
            log.info("Clicked Logout");
        } catch(Exception e) {
            log.error("Failed logging out", e);
        }
    }
}