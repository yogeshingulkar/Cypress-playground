package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;

public class PIMPage {

    WebDriver driver;
    private static final Logger log = LogManager.getLogger(PIMPage.class);

    // ==================== PIM Page Header ====================
    @FindBy(xpath = "//h6[text()='PIM']")
    private WebElement pimHeader;

    // ==================== Top Navigation Tabs ====================
    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListTab;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeTab;

    @FindBy(xpath = "//a[text()='Reports']")
    private WebElement reportsTab;

    // ==================== Add Employee Button ====================
    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    // ==================== Search / Filter Form Fields ====================
    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//input[contains(@class,'oxd-input')])[2]")
    private WebElement employeeNameInput;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//input[contains(@class,'oxd-input')])[3]")
    private WebElement employeeIdInput;

    @FindBy(xpath = "//label[text()='Employment Status']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement employmentStatusDropdown;

    @FindBy(xpath = "//label[text()='Include']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement includeDropdown;

    @FindBy(xpath = "//label[text()='Supervisor Name']/../..//input")
    private WebElement supervisorNameInput;

    @FindBy(xpath = "//label[text()='Job Title']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement jobTitleDropdown;

    @FindBy(xpath = "//label[text()='Sub Unit']/../..//div[contains(@class,'oxd-select-text')]")
    private WebElement subUnitDropdown;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement resetButton;

    // ==================== Add Employee Form Fields ====================
    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "middleName")
    private WebElement middleNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[text()='Employee Id']/../..//input")
    private WebElement addEmployeeIdInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement cancelButton;

    // ==================== Employee List Table ====================
    @FindBy(xpath = "//div[@class='orangehrm-employee-list']//span[contains(text(),'Record')]")
    private WebElement recordsFoundLabel;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-header')]//input[@type='checkbox']")
    private WebElement selectAllCheckbox;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']")
    private List<WebElement> employeeTableRows;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']//input[@type='checkbox']")
    private List<WebElement> rowCheckboxes;

    // ==================== Table Action Buttons (per row) ====================
    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']//i[contains(@class,'bi-pencil-fill')]/..")
    private List<WebElement> editButtons;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@class='oxd-table-card']//i[contains(@class,'bi-trash')]/..")
    private List<WebElement> deleteButtons;

    // ==================== Bulk Delete Button ====================
    @FindBy(xpath = "//button[contains(@class,'oxd-button--label-danger')]")
    private WebElement deleteSelectedButton;

    // ==================== Delete Confirmation Dialog ====================
    @FindBy(xpath = "//div[contains(@class,'orangehrm-modal-footer')]//button[contains(@class,'oxd-button--label-danger')]")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//div[contains(@class,'orangehrm-modal-footer')]//button[contains(@class,'oxd-button--text')]")
    private WebElement cancelDeleteButton;

    // ==================== Toast / Success Message ====================
    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]//p[contains(@class,'oxd-text--toast-message')]")
    private WebElement toastMessage;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]")
    private WebElement successToast;

    // ==================== Validation / Error Messages ====================
    @FindBy(xpath = "//span[contains(@class,'oxd-input-field-error-message')]")
    private List<WebElement> validationErrors;

    @FindBy(xpath = "//span[text()='Required']")
    private List<WebElement> requiredFieldErrors;

    // ==================== Autocomplete Dropdown ====================
    @FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-dropdown')]//div[@role='option']")
    private List<WebElement> autocompleteOptions;

    // ==================== Pagination ====================
    @FindBy(xpath = "//nav[@aria-label='Pagination Navigation']")
    private WebElement paginationNav;

    @FindBy(xpath = "//button[contains(@class,'oxd-pagination-page-item--previous')]")
    private WebElement paginationPrevious;

    @FindBy(xpath = "//button[contains(@class,'oxd-pagination-page-item--next')]")
    private WebElement paginationNext;

    // ==================== Personal Details (Edit Employee) ====================
    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;

    @FindBy(xpath = "(//div[contains(@class,'oxd-form')]//input[contains(@class,'oxd-input')])[2]")
    private WebElement editFirstNameInput;

    @FindBy(xpath = "(//div[contains(@class,'oxd-form')]//input[contains(@class,'oxd-input')])[3]")
    private WebElement editMiddleNameInput;

    @FindBy(xpath = "(//div[contains(@class,'oxd-form')]//input[contains(@class,'oxd-input')])[4]")
    private WebElement editLastNameInput;

    @FindBy(xpath = "(//div[contains(@class,'oxd-form')]//input[contains(@class,'oxd-input')])[5]")
    private WebElement editEmployeeIdField;

    // ==================== Select Dropdown Options ====================
    @FindBy(xpath = "//div[@role='listbox']//div[@role='option']")
    private List<WebElement> dropdownOptions;

    // ==================== Constructor ====================
    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ==================== Page Verification Methods ====================
    public boolean isPIMPageDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, pimHeader).isDisplayed();
        } catch (Exception e) {
            log.error("PIM page header not displayed", e);
            return false;
        }
    }

    public String getPIMHeaderText() {
        return WaitUtil.waitForVisibility(driver, pimHeader).getText();
    }

    public boolean isEmployeeListTabDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, employeeListTab).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddEmployeeTabDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, addEmployeeTab).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, searchButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isResetButtonDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, resetButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddButtonDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, addButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRecordsFoundLabelDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, recordsFoundLabel).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getRecordsFoundText() {
        try {
            return WaitUtil.waitForVisibility(driver, recordsFoundLabel).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isEmployeeTableDisplayed() {
        try {
            return !employeeTableRows.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public int getEmployeeTableRowCount() {
        try {
            Thread.sleep(1000); // brief wait for table to render
            return employeeTableRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ==================== Navigation Methods ====================
    public void clickEmployeeListTab() {
        WaitUtil.waitForClickable(driver, employeeListTab).click();
        log.info("Clicked Employee List tab");
    }

    public void clickAddEmployeeTab() {
        WaitUtil.waitForClickable(driver, addEmployeeTab).click();
        log.info("Clicked Add Employee tab");
    }

    public void clickReportsTab() {
        WaitUtil.waitForClickable(driver, reportsTab).click();
        log.info("Clicked Reports tab");
    }

    // ==================== Add Employee Methods ====================
    public void clickAddButton() {
        WaitUtil.waitForClickable(driver, addButton).click();
        log.info("Clicked Add button");
    }

    public void enterFirstName(String firstName) {
        WaitUtil.waitForVisibility(driver, firstNameInput).clear();
        firstNameInput.sendKeys(firstName);
        log.info("Entered first name: " + firstName);
    }

    public void enterMiddleName(String middleName) {
        WaitUtil.waitForVisibility(driver, middleNameInput).clear();
        middleNameInput.sendKeys(middleName);
        log.info("Entered middle name: " + middleName);
    }

    public void enterLastName(String lastName) {
        WaitUtil.waitForVisibility(driver, lastNameInput).clear();
        lastNameInput.sendKeys(lastName);
        log.info("Entered last name: " + lastName);
    }

    public void enterEmployeeId(String empId) {
        WebElement field = WaitUtil.waitForVisibility(driver, addEmployeeIdInput);
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(empId);
        log.info("Entered employee ID: " + empId);
    }

    public void clickSave() {
        WaitUtil.waitForClickable(driver, saveButton).click();
        log.info("Clicked Save button");
    }

    public void clickCancel() {
        WaitUtil.waitForClickable(driver, cancelButton).click();
        log.info("Clicked Cancel button");
    }

    public void addEmployee(String firstName, String middleName, String lastName) {
        clickAddButton();
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        clickSave();
        log.info("Added employee: " + firstName + " " + middleName + " " + lastName);
    }

    public void addEmployeeViaTab(String firstName, String middleName, String lastName) {
        clickAddEmployeeTab();
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        clickSave();
        log.info("Added employee via tab: " + firstName + " " + middleName + " " + lastName);
    }

    // ==================== Search / Filter Methods ====================
    public void enterEmployeeName(String name) {
        WaitUtil.waitForVisibility(driver, employeeNameInput).clear();
        employeeNameInput.sendKeys(name);
        log.info("Entered employee name for search: " + name);
    }

    public void enterEmployeeIdSearch(String empId) {
        WaitUtil.waitForVisibility(driver, employeeIdInput).clear();
        employeeIdInput.sendKeys(empId);
        log.info("Entered employee ID for search: " + empId);
    }

    public void selectEmploymentStatus(String status) {
        WaitUtil.waitForClickable(driver, employmentStatusDropdown).click();
        selectDropdownOption(status);
        log.info("Selected employment status: " + status);
    }

    public void selectInclude(String includeOption) {
        WaitUtil.waitForClickable(driver, includeDropdown).click();
        selectDropdownOption(includeOption);
        log.info("Selected include option: " + includeOption);
    }

    public void enterSupervisorName(String supervisorName) {
        WaitUtil.waitForVisibility(driver, supervisorNameInput).clear();
        supervisorNameInput.sendKeys(supervisorName);
        log.info("Entered supervisor name: " + supervisorName);
    }

    public void selectJobTitle(String jobTitle) {
        WaitUtil.waitForClickable(driver, jobTitleDropdown).click();
        selectDropdownOption(jobTitle);
        log.info("Selected job title: " + jobTitle);
    }

    public void selectSubUnit(String subUnit) {
        WaitUtil.waitForClickable(driver, subUnitDropdown).click();
        selectDropdownOption(subUnit);
        log.info("Selected sub unit: " + subUnit);
    }

    public void clickSearch() {
        WaitUtil.waitForClickable(driver, searchButton).click();
        log.info("Clicked Search button");
    }

    public void clickReset() {
        WaitUtil.waitForClickable(driver, resetButton).click();
        log.info("Clicked Reset button");
    }

    public void searchByEmployeeName(String name) {
        enterEmployeeName(name);
        clickSearch();
        log.info("Searched by employee name: " + name);
    }

    public void searchByEmployeeId(String empId) {
        enterEmployeeIdSearch(empId);
        clickSearch();
        log.info("Searched by employee ID: " + empId);
    }

    // ==================== Edit Employee Methods ====================
    public void clickEditOnFirstRow() {
        if (!editButtons.isEmpty()) {
            WaitUtil.waitForClickable(driver, editButtons.get(0)).click();
            log.info("Clicked edit on first employee row");
        } else {
            log.warn("No edit buttons found in the table");
        }
    }

    public void clickEditByRowIndex(int index) {
        if (index < editButtons.size()) {
            WaitUtil.waitForClickable(driver, editButtons.get(index)).click();
            log.info("Clicked edit on row index: " + index);
        } else {
            log.warn("Row index " + index + " out of bounds. Total edit buttons: " + editButtons.size());
        }
    }

    public boolean isPersonalDetailsPageDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, personalDetailsHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void editEmployeeFirstName(String newFirstName) {
        WebElement field = WaitUtil.waitForVisibility(driver, editFirstNameInput);
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(newFirstName);
        log.info("Edited first name to: " + newFirstName);
    }

    public void editEmployeeLastName(String newLastName) {
        WebElement field = WaitUtil.waitForVisibility(driver, editLastNameInput);
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(newLastName);
        log.info("Edited last name to: " + newLastName);
    }

    // ==================== Delete Employee Methods ====================
    public void clickDeleteOnFirstRow() {
        if (!deleteButtons.isEmpty()) {
            WaitUtil.waitForClickable(driver, deleteButtons.get(0)).click();
            log.info("Clicked delete on first employee row");
        } else {
            log.warn("No delete buttons found in the table");
        }
    }

    public void clickDeleteByRowIndex(int index) {
        if (index < deleteButtons.size()) {
            WaitUtil.waitForClickable(driver, deleteButtons.get(index)).click();
            log.info("Clicked delete on row index: " + index);
        }
    }

    public void confirmDelete() {
        WaitUtil.waitForClickable(driver, confirmDeleteButton).click();
        log.info("Confirmed deletion");
    }

    public void cancelDelete() {
        WaitUtil.waitForClickable(driver, cancelDeleteButton).click();
        log.info("Cancelled deletion");
    }

    public void deleteFirstEmployee() {
        clickDeleteOnFirstRow();
        confirmDelete();
        log.info("Deleted first employee from the list");
    }

    public void selectAllEmployees() {
        WaitUtil.waitForClickable(driver, selectAllCheckbox).click();
        log.info("Selected all employees checkbox");
    }

    public void selectEmployeeCheckbox(int index) {
        if (index < rowCheckboxes.size()) {
            WaitUtil.waitForClickable(driver, rowCheckboxes.get(index)).click();
            log.info("Selected checkbox at row index: " + index);
        }
    }

    public void clickDeleteSelected() {
        WaitUtil.waitForClickable(driver, deleteSelectedButton).click();
        log.info("Clicked Delete Selected button");
    }

    public void bulkDeleteSelectedEmployees() {
        clickDeleteSelected();
        confirmDelete();
        log.info("Bulk deleted selected employees");
    }

    // ==================== Toast / Success Methods ====================
    public boolean isSuccessToastDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, successToast).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getToastMessageText() {
        try {
            return WaitUtil.waitForVisibility(driver, toastMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // ==================== Validation Methods ====================
    public boolean isValidationErrorDisplayed() {
        try {
            Thread.sleep(500);
            return !validationErrors.isEmpty() && validationErrors.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getValidationErrorCount() {
        try {
            Thread.sleep(500);
            return validationErrors.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isRequiredFieldErrorDisplayed() {
        try {
            Thread.sleep(500);
            return !requiredFieldErrors.isEmpty() && requiredFieldErrors.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getRequiredFieldErrorCount() {
        try {
            Thread.sleep(500);
            return requiredFieldErrors.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFirstValidationErrorText() {
        try {
            return WaitUtil.waitForVisibility(driver, validationErrors.get(0)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // ==================== Export / Download ====================
    public void clickExportCSV() {
        try {
            // The export button is usually inside a dropdown toggle above the table
            WebElement exportDropdownTrigger = driver.findElement(
                    By.xpath(
                            "//div[contains(@class,'orangehrm-employee-list')]//button[contains(@class,'oxd-button--text')]//i[contains(@class,'bi-download')]/.."));
            WaitUtil.waitForClickable(driver, exportDropdownTrigger).click();
            log.info("Clicked Export/Download button");
        } catch (Exception e) {
            log.warn("Export button not found, trying alternate xpath");
            try {
                WebElement exportBtn = driver.findElement(
                        By.xpath("//button[contains(@class,'oxd-button')]//i[contains(@class,'bi-download')]/.."));
                WaitUtil.waitForClickable(driver, exportBtn).click();
                log.info("Clicked alternate Export button");
            } catch (Exception ex) {
                log.error("Export button not found", ex);
            }
        }
    }

    // ==================== Autocomplete Selection ====================
    public void selectAutocompleteOption(String optionText) {
        try {
            Thread.sleep(1000); // wait for autocomplete dropdown to appear
            for (WebElement option : autocompleteOptions) {
                if (option.getText().contains(optionText)) {
                    option.click();
                    log.info("Selected autocomplete option: " + optionText);
                    return;
                }
            }
            log.warn("Autocomplete option not found: " + optionText);
        } catch (Exception e) {
            log.error("Failed to select autocomplete option", e);
        }
    }

    // ==================== Dropdown Helper ====================
    private void selectDropdownOption(String optionText) {
        try {
            Thread.sleep(500); // wait for dropdown to open
            for (WebElement option : dropdownOptions) {
                if (option.getText().trim().equalsIgnoreCase(optionText)) {
                    option.click();
                    log.info("Selected dropdown option: " + optionText);
                    return;
                }
            }
            log.warn("Dropdown option not found: " + optionText);
        } catch (Exception e) {
            log.error("Error selecting dropdown option: " + optionText, e);
        }
    }

    // ==================== Pagination Methods ====================
    public boolean isPaginationDisplayed() {
        try {
            return paginationNav.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNextPage() {
        WaitUtil.waitForClickable(driver, paginationNext).click();
        log.info("Clicked Next page");
    }

    public void clickPreviousPage() {
        WaitUtil.waitForClickable(driver, paginationPrevious).click();
        log.info("Clicked Previous page");
    }

    // ==================== Table Data Retrieval ====================
    public String getCellText(int rowIndex, int colIndex) {
        try {
            List<WebElement> cells = employeeTableRows.get(rowIndex)
                    .findElements(By.xpath(".//div[@role='cell']"));
            if (colIndex < cells.size()) {
                return cells.get(colIndex).getText().trim();
            }
        } catch (Exception e) {
            log.error("Failed to get cell text at [" + rowIndex + "][" + colIndex + "]", e);
        }
        return "";
    }

    // ==================== URL Verification ====================
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOnPIMPage() {
        return driver.getCurrentUrl().contains("/pim");
    }

    public boolean isOnAddEmployeePage() {
        return driver.getCurrentUrl().contains("/pim/addEmployee");
    }

    // ==================== Save Button on Add Form ====================
    public boolean isSaveButtonDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, saveButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== No Records Found ====================
    public boolean isNoRecordsFound() {
        try {
            WebElement noRecords = driver.findElement(
                    By.xpath("//span[text()='No Records Found']"));
            return noRecords.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== Wait After Action Helpers ====================
    public void waitForTableToLoad() {
        try {
            Thread.sleep(2000); // allow table data to refresh
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitForPageToLoad() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}