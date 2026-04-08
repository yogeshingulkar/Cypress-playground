package pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtil;

public class LeavePage {

    WebDriver driver;
    private static final Logger log = LogManager.getLogger(LeavePage.class);
    private Actions actions;

    // Header
    @FindBy(xpath = "//h6[text()='Leave']")
    private WebElement leaveHeader;

    // Tabs
    @FindBy(xpath = "//a[text()='Apply']")
    private WebElement applyTab;
    
    @FindBy(xpath = "//a[text()='My Leave']")
    private WebElement myLeaveTab;

    @FindBy(xpath = "//span[contains(text(), 'Entitlements')]")
    private WebElement entitlementsTab;

    @FindBy(xpath = "//span[contains(text(), 'Reports')]")
    private WebElement reportsTab;

    @FindBy(xpath = "//a[text()='Assign Leave']")
    private WebElement assignLeaveTab;

    // Fields
    @FindBy(xpath = "//label[text()='Leave Type']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement leaveTypeDropdown;

    @FindBy(xpath = "//label[contains(text(),'From Date')]/../..//input")
    private WebElement fromDateInput;

    @FindBy(xpath = "//label[contains(text(),'To Date')]/../..//input")
    private WebElement toDateInput;

    @FindBy(xpath = "//textarea")
    private WebElement commentsTextarea;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameInput;

    @FindBy(xpath = "//button[normalize-space()='Apply']")
    private WebElement applyButton;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement resetButton;

    @FindBy(xpath = "//div[contains(@class, 'oxd-table-header')]")
    private WebElement tableHeader;

    // Others
    @FindBy(xpath = "//div[@role='listbox']//div[@role='option']")
    private List<WebElement> dropdownOptions;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]")
    private WebElement successToast;
    
    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']")
    private List<WebElement> leaveTableRows;

    // Approvals
    @FindBy(xpath = "//button[contains(normalize-space(),'Approve')] | //button[contains(@class, 'oxd-button--success')]")
    private List<WebElement> approveButtons;

    @FindBy(xpath = "//button[contains(normalize-space(),'Reject')] | //button[contains(@class, 'oxd-button--danger')]")
    private List<WebElement> rejectButtons;

    public LeavePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLeavePageDisplayed() {
        return WaitUtil.waitForVisibility(driver, leaveHeader).isDisplayed();
    }

    public void clickApplyTab() {
        WebElement tab = WaitUtil.waitForClickable(driver, applyTab);
        actions.moveToElement(tab).pause(500).click().perform();
    }

    public void clickMyLeaveTab() {
        WebElement tab = WaitUtil.waitForClickable(driver, myLeaveTab);
        actions.moveToElement(tab).pause(300).click().perform();
    }

    public void clickEntitlementsTab() {
        WebElement tab = WaitUtil.waitForClickable(driver, entitlementsTab);
        actions.moveToElement(tab).pause(300).click().perform();
    }

    public void clickReportsTab() {
        WebElement tab = WaitUtil.waitForClickable(driver, reportsTab);
        actions.moveToElement(tab).pause(300).click().perform();
    }

    public void clickAssignLeaveTab() {
        WebElement tab = WaitUtil.waitForClickable(driver, assignLeaveTab);
        actions.moveToElement(tab).pause(300).click().perform();
    }

    public void applyLeave(String leaveType, String fromDate, String toDate, String comment) {
        clickApplyTab();
        
        if (leaveType != null && !leaveType.isEmpty()) {
            WebElement dropdown = WaitUtil.waitForClickable(driver, leaveTypeDropdown);
            actions.moveToElement(dropdown).click().perform();
            selectDropdownOption(leaveType);
        }
        
        try {
            Robot robot = new Robot();
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (Exception e) {}
        
        WebElement fDate = WaitUtil.waitForVisibility(driver, fromDateInput);
        actions.moveToElement(fDate).click().sendKeys(fromDate).perform();
        
        WebElement tDate = WaitUtil.waitForVisibility(driver, toDateInput);
        tDate.clear();
        actions.moveToElement(tDate).click().sendKeys(toDate).perform();
        
        if (comment != null) {
            WebElement commText = WaitUtil.waitForVisibility(driver, commentsTextarea);
            actions.moveToElement(commText).pause(200).click().sendKeys(comment).perform();
        }
        
        submitApply();
    }

    public void submitApply() {
        WebElement btn = WaitUtil.waitForClickable(driver, applyButton);
        actions.moveToElement(btn).pause(400).click().perform();
        log.info("Clicked Apply/Submit");
    }

    public void enterEmployeeName(String name) {
        WebElement emp = WaitUtil.waitForVisibility(driver, employeeNameInput);
        actions.moveToElement(emp).click().sendKeys(name).perform();
    }

    public void clickSearch() {
        WebElement searchBtn = WaitUtil.waitForClickable(driver, searchButton);
        actions.moveToElement(searchBtn).pause(300).click().perform();
    }

    public void clickReset() {
        WebElement resetBtn = WaitUtil.waitForClickable(driver, resetButton);
        actions.moveToElement(resetBtn).pause(300).click().perform();
    }

    public void searchMyLeave() {
        clickMyLeaveTab();
        clickSearch();
    }

    public boolean isTableHeaderVisible() {
        try {
            return WaitUtil.waitForVisibility(driver, tableHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickApproveFirstLeave() {
        if (!approveButtons.isEmpty()) {
            actions.moveToElement(approveButtons.get(0)).click().perform();
        }
    }

    public void clickRejectFirstLeave() {
        if (!rejectButtons.isEmpty()) {
            actions.moveToElement(rejectButtons.get(0)).click().perform();
        }
    }

    public void clickFirstLeaveRecord() {
        if (!leaveTableRows.isEmpty()) {
            actions.moveToElement(leaveTableRows.get(0)).click().perform();
        }
    }

    public boolean isSuccessToastDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, successToast).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getLeaveTableRowCount() {
        try {
            Thread.sleep(1000);
            return leaveTableRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isLeaveTypeDropdownAccessible() {
        try {
            return WaitUtil.waitForClickable(driver, leaveTypeDropdown).isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }

    private void selectDropdownOption(String optionText) {
        try {
            Thread.sleep(500);
            for (WebElement option : dropdownOptions) {
                if (option.getText().trim().equalsIgnoreCase(optionText)) {
                    actions.moveToElement(option).click().perform();
                    return;
                }
            }
        } catch (Exception e) {
            log.error("Error selecting dropdown option: " + optionText, e);
        }
    }
    
    public void waitForPageToLoad() {
        try { Thread.sleep(1500); } catch(Exception e) {}
    }
}