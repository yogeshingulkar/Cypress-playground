package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;

public class AdminPage {

    WebDriver driver;
    private static final Logger log = LogManager.getLogger(AdminPage.class);

    // Header
    @FindBy(xpath = "//h6[text()='Admin']")
    private WebElement adminHeader;

    // Search and Add
    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "(//input[contains(@class,'oxd-input')])[2]")
    private WebElement searchUsernameInput;

    @FindBy(xpath = "//label[text()='User Role']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement searchUserRoleDropdown;

    @FindBy(xpath = "//label[text()='Status']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement searchStatusDropdown;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement resetButton;

    // Add Form
    @FindBy(xpath = "//label[text()='User Role']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement addUserRoleDropdown;

    @FindBy(xpath = "//label[text()='Status']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement addStatusDropdown;
    
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameInput;
    
    @FindBy(xpath = "//div[@role='listbox']//span")
    private WebElement autocompleteFirstOption;

    @FindBy(xpath = "//label[text()='Username']/../..//input")
    private WebElement addUsernameInput;

    @FindBy(xpath = "//label[text()='Password']/../..//input")
    private WebElement addPasswordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/../..//input")
    private WebElement addConfirmPasswordInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//div[@role='listbox']//div[@role='option']")
    private List<WebElement> dropdownOptions;

    // Table
    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']")
    private List<WebElement> userTableRows;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']//i[contains(@class,'bi-pencil-fill')]/..")
    private List<WebElement> editButtons;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']//i[contains(@class,'bi-trash')]/..")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//div[contains(@class,'orangehrm-modal-footer')]//button[contains(@class,'oxd-button--label-danger')]")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]")
    private WebElement successToast;
    
    @FindBy(xpath = "//span[contains(text(),'Record')]")
    private WebElement recordsFoundLabel;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isAdminPageDisplayed() {
        return WaitUtil.waitForVisibility(driver, adminHeader).isDisplayed();
    }

    public void clickAddUser() {
        WaitUtil.waitForClickable(driver, addButton).click();
        log.info("Clicked Add button");
    }

    public void searchUser(String username) {
        WaitUtil.waitForVisibility(driver, searchUsernameInput).clear();
        searchUsernameInput.sendKeys(username);
        WaitUtil.waitForClickable(driver, searchButton).click();
        log.info("Searched for user: " + username);
    }
    
    public void addUser(String userRole, String employeeName, String status, String username, String password) {
        clickAddUser();
        
        // Select Role
        WaitUtil.waitForClickable(driver, addUserRoleDropdown).click();
        selectDropdownOption(userRole);
        
        // Enter Emp Name
        WaitUtil.waitForVisibility(driver, employeeNameInput).sendKeys(employeeName);
        WaitUtil.waitForClickable(driver, autocompleteFirstOption).click();
        
        // Select Status
        WaitUtil.waitForClickable(driver, addStatusDropdown).click();
        selectDropdownOption(status);
        
        // Enter Details
        WaitUtil.waitForVisibility(driver, addUsernameInput).sendKeys(username);
        WaitUtil.waitForVisibility(driver, addPasswordInput).sendKeys(password);
        WaitUtil.waitForVisibility(driver, addConfirmPasswordInput).sendKeys(password);
        
        WaitUtil.waitForClickable(driver, saveButton).click();
        log.info("Added new user: " + username);
    }
    
    public void deleteUser() {
        if (!deleteButtons.isEmpty()) {
            WaitUtil.waitForClickable(driver, deleteButtons.get(0)).click();
            WaitUtil.waitForClickable(driver, confirmDeleteButton).click();
            log.info("Deleted first user");
        }
    }

    public boolean isSuccessToastDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, successToast).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getUserTableRowCount() {
        try {
            Thread.sleep(1000);
            return userTableRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    private void selectDropdownOption(String optionText) {
        try {
            Thread.sleep(500);
            for (WebElement option : dropdownOptions) {
                if (option.getText().trim().equalsIgnoreCase(optionText)) {
                    option.click();
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