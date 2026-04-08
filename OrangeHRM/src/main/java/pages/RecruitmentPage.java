package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;

public class RecruitmentPage {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(RecruitmentPage.class);

    @FindBy(xpath = "//h6[text()='Recruitment']")
    private WebElement recruitmentHeader;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement candidateNameInput;

    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "middleName")
    private WebElement middleNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;
    
    @FindBy(xpath = "//label[text()='Email']/../..//input")
    private WebElement emailInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']")
    private List<WebElement> candidateRows;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]")
    private WebElement successToast;

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isRecruitmentPageDisplayed() {
        return WaitUtil.waitForVisibility(driver, recruitmentHeader).isDisplayed();
    }

    public void clickAddCandidate() {
        WaitUtil.waitForClickable(driver, addButton).click();
        log.info("Clicked Add Candidate button");
    }

    public void addCandidate(String firstName, String middleName, String lastName, String email) {
        clickAddCandidate();
        WaitUtil.waitForVisibility(driver, firstNameInput).sendKeys(firstName);
        WaitUtil.waitForVisibility(driver, middleNameInput).sendKeys(middleName);
        WaitUtil.waitForVisibility(driver, lastNameInput).sendKeys(lastName);
        WaitUtil.waitForVisibility(driver, emailInput).sendKeys(email);
        WaitUtil.waitForClickable(driver, saveButton).click();
        log.info("Added candidate: " + firstName + " " + lastName);
    }

    public void searchCandidate(String name) {
        WaitUtil.waitForVisibility(driver, candidateNameInput).sendKeys(name);
        WaitUtil.waitForClickable(driver, searchButton).click();
        log.info("Searched for candidate: " + name);
    }

    public boolean isSuccessToastDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, successToast).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCandidateRowCount() {
        try {
            Thread.sleep(1000); // Wait for table refresh
            return candidateRows.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public void waitForPageToLoad() {
        try { Thread.sleep(1500); } catch(Exception e) {}
    }
}
