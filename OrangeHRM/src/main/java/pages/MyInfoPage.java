package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;

public class MyInfoPage {

    WebDriver driver;
    private static final Logger log = LogManager.getLogger(MyInfoPage.class);

    @FindBy(xpath = "//h6[text()='PIM']")
    private WebElement myInfoHeader;

    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement saveButton;

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isMyInfoPageDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, myInfoHeader).isDisplayed();
        } catch (Exception e) {
            log.warn("My Info page is missing", e);
            return false;
        }
    }

    public void editPersonalDetails(String fname, String lname) {
        WebElement fNameField = WaitUtil.waitForVisibility(driver, firstNameInput);
        fNameField.clear();
        fNameField.sendKeys(fname);

        WebElement lNameField = WaitUtil.waitForVisibility(driver, lastNameInput);
        lNameField.clear();
        lNameField.sendKeys(lname);

        WaitUtil.waitForClickable(driver, saveButton).click();
        log.info("Updated My Info details");
    }

    public void waitForPageToLoad() {
        try { Thread.sleep(1500); } catch(Exception e) {}
    }
}
