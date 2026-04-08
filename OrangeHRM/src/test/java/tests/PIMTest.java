package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.DashboardPage;
import pages.PIMPage;

public class PIMTest extends BaseClass {

	//login and PIM Module
    private PIMPage navigateToPIM() {
        login();
        DashboardPage dashboard = new DashboardPage(getDriver());
        PIMPage pim = dashboard.goToPIM();
        pim.waitForPageToLoad();
        return pim;
    }

    // To verify that user can add new record in PIM module.
    @Test(priority = 1)
    public void TC_PIM_061() {
        Reporter.log("TC_PIM_061 - Verify user can add new record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isOnAddEmployeePage(), "Should navigate to Add Employee page");

        pim.enterFirstName("Tejas");
        pim.enterMiddleName("Michael");
        pim.enterLastName("Dole");
        pim.clickSave();

        pim.waitForPageToLoad();
        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),"Personal Details page should be displayed after saving new employee");
        Reporter.log("TC_PIM_061 - PASSED: New employee record added successfully");
    }

    // To verify that user can edit existing record in PIM module.
    @Test(priority = 2)
    public void TC_PIM_062() {
        Reporter.log("TC_PIM_062 - Verify user can edit existing record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getEmployeeTableRowCount() > 0, "Employee table should have at least one record");

        pim.clickEditOnFirstRow();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),
                "Personal Details page should be displayed for editing");

        pim.editEmployeeFirstName("UpdatedFirstName");
        pim.editEmployeeLastName("UpdatedLastName");
        pim.clickSave();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isSuccessToastDisplayed(),
                "Success toast should appear after editing employee");
        Reporter.log("TC_PIM_062 - PASSED: Existing employee record edited successfully");
    }

    // To verify that user can delete record in PIM module.
    @Test(priority = 3)
    public void TC_PIM_063() {
        Reporter.log("TC_PIM_063 - Verify user can delete record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        int initialRowCount = pim.getEmployeeTableRowCount();
        Assert.assertTrue(initialRowCount > 0, "Employee table should have at least one record to delete");

        pim.clickDeleteOnFirstRow();
        pim.confirmDelete();
        pim.waitForTableToLoad();

        Assert.assertTrue(pim.isSuccessToastDisplayed(),
                "Success toast should appear after deleting employee");
        Reporter.log("TC_PIM_063 - PASSED: Employee record deleted successfully");
    }

    // To verify that user can search records in PIM module.
    @Test(priority = 4)
    public void TC_PIM_064() {
        Reporter.log("TC_PIM_064 - Verify user can search records in PIM module");
        PIMPage pim = navigateToPIM();

        pim.enterEmployeeIdSearch("0001");
        pim.clickSearch();
        pim.waitForTableToLoad();

        String recordsText = pim.getRecordsFoundText();
        Assert.assertNotNull(recordsText, "Records Found label should be visible after search");
        Assert.assertTrue(recordsText.contains("Record"), "Record count text should be displayed");
        Reporter.log("TC_PIM_064 - PASSED: Search records functionality working correctly");
    }

    // To verify that validation works correctly in PIM module.
    @Test(priority = 5)
    public void TC_PIM_065() {
        Reporter.log("TC_PIM_065 - Verify validation works correctly in PIM module (Negative)");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        // Try saving without filling any fields
        pim.clickSave();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isValidationErrorDisplayed(),
                "Validation error should be displayed when saving without required fields");
        Assert.assertTrue(pim.getValidationErrorCount() > 0,
                "There should be at least one validation error message");
        Reporter.log("TC_PIM_065 - PASSED: Validation errors displayed correctly for empty fields");
    }

    // To verify that mandatory fields are validated in PIM module.
    @Test(priority = 6)
    public void TC_PIM_066() {
        Reporter.log("TC_PIM_066 - Verify mandatory fields are validated in PIM module");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        // Enter only first name and try saving (last name is also mandatory)
        pim.enterFirstName("TestFirst");
        pim.clickSave();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isRequiredFieldErrorDisplayed(),
                "'Required' error should be displayed for empty mandatory fields");
        Reporter.log("TC_PIM_066 - PASSED: Mandatory field validation working correctly");
    }

    // To verify that user can view details in PIM module.
    @Test(priority = 7)
    public void TC_PIM_067() {
        Reporter.log("TC_PIM_067 - Verify user can view details in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getEmployeeTableRowCount() > 0, "Employee table should have records");

        pim.clickEditOnFirstRow();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),
                "Personal Details page should be displayed to view employee details");
        Assert.assertTrue(pim.getCurrentUrl().contains("/pim/viewPersonalDetails"),
                "URL should indicate viewing personal details");
        Reporter.log("TC_PIM_067 - PASSED: Employee details viewed successfully");
    }

    // To verify that user can export data from PIM module.
    @Test(priority = 8)
    public void TC_PIM_068() {
        Reporter.log("TC_PIM_068 - Verify user can export data from PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.isRecordsFoundLabelDisplayed(), "Records should be available for export");

        pim.clickExportCSV();
        pim.waitForPageToLoad();

        // Export triggers a file download – verify no error occurred
        Assert.assertTrue(pim.isPIMPageDisplayed(),
                "PIM page should remain displayed after exporting");
        Reporter.log("TC_PIM_068 - PASSED: Data export initiated successfully");
    }

    // To verify that user can filter records in PIM module.
    @Test(priority = 9)
    public void TC_PIM_069() {
        Reporter.log("TC_PIM_069 - Verify user can filter records in PIM module");
        PIMPage pim = navigateToPIM();

        pim.selectJobTitle("HR Manager");
        pim.clickSearch();
        pim.waitForTableToLoad();

        String recordsText = pim.getRecordsFoundText();
        Assert.assertTrue(recordsText.contains("Record"),
                "Filtered results should show record count");
        Reporter.log("TC_PIM_069 - PASSED: Records filtered successfully using Job Title");
    }

    // To verify that PIM page is displayed correctly.
    @Test(priority = 10)
    public void TC_PIM_070() {
        Reporter.log("TC_PIM_070 - Verify PIM page is displayed correctly (Negative)");
        PIMPage pim = navigateToPIM();

        Assert.assertTrue(pim.isPIMPageDisplayed(), "PIM header should be visible");
        Assert.assertEquals(pim.getPIMHeaderText(), "PIM", "Header text should be 'PIM'");
        Assert.assertTrue(pim.isEmployeeListTabDisplayed(), "Employee List tab should be visible");
        Assert.assertTrue(pim.isAddEmployeeTabDisplayed(), "Add Employee tab should be visible");
        Assert.assertTrue(pim.isSearchButtonDisplayed(), "Search button should be visible");
        Assert.assertTrue(pim.isResetButtonDisplayed(), "Reset button should be visible");
        Assert.assertTrue(pim.isAddButtonDisplayed(), "Add button should be visible");
        Assert.assertTrue(pim.isRecordsFoundLabelDisplayed(), "Records Found label should be visible");
        Assert.assertTrue(pim.isOnPIMPage(), "URL should contain '/pim'");
        Reporter.log("TC_PIM_070 - PASSED: PIM page displayed correctly with all elements");
    }

    // To verify that user can add new record in PIM module.
    @Test(priority = 11)
    public void TC_PIM_071() {
        Reporter.log("TC_PIM_071 - Verify user can add new record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.clickAddEmployeeTab();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isOnAddEmployeePage(), "Should navigate to Add Employee page via tab");

        pim.enterFirstName("Jane");
        pim.enterMiddleName("Marie");
        pim.enterLastName("Smith");
        pim.clickSave();

        pim.waitForPageToLoad();
        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),
                "Personal Details page should be displayed after adding via tab");
        Reporter.log("TC_PIM_071 - PASSED: New employee added successfully via Add Employee tab");
    }

    // To verify that user can edit existing record in PIM module.
    @Test(priority = 12)
    public void TC_PIM_072() {
        Reporter.log("TC_PIM_072 - Verify user can edit existing record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        int rowCount = pim.getEmployeeTableRowCount();
        Assert.assertTrue(rowCount > 0, "Table should have records to edit");

        pim.clickEditByRowIndex(0);
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),
                "Personal Details should be displayed");

        pim.editEmployeeFirstName("EditedName072");
        pim.clickSave();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isSuccessToastDisplayed(),
                "Success toast should appear after editing");
        Reporter.log("TC_PIM_072 - PASSED: Employee record edited successfully");
    }

    // To verify that user can delete record in PIM module.
    @Test(priority = 13)
    public void TC_PIM_073() {
        Reporter.log("TC_PIM_073 - Verify user can delete record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getEmployeeTableRowCount() > 0, "Records must exist to delete");

        pim.selectEmployeeCheckbox(0);
        pim.clickDeleteSelected();
        pim.confirmDelete();
        pim.waitForTableToLoad();

        Assert.assertTrue(pim.isSuccessToastDisplayed(),
                "Success toast should appear after bulk deleting selected employee");
        Reporter.log("TC_PIM_073 - PASSED: Employee deleted via checkbox selection and delete selected");
    }

    // To verify that user can search records in PIM module.
    @Test(priority = 14)
    public void TC_PIM_074() {
        Reporter.log("TC_PIM_074 - Verify user can search records in PIM module");
        PIMPage pim = navigateToPIM();

        pim.enterEmployeeName("Admin");
        pim.waitForPageToLoad();
        pim.selectAutocompleteOption("Admin");
        pim.clickSearch();
        pim.waitForTableToLoad();

        String recordsText = pim.getRecordsFoundText();
        Assert.assertTrue(recordsText.contains("Record"),
                "Search results should display record count");
        Reporter.log("TC_PIM_074 - PASSED: Search by employee name working correctly");
    }

    // To verify that validation works correctly in PIM module.
    @Test(priority = 15)
    public void TC_PIM_075() {
        Reporter.log("TC_PIM_075 - Verify validation works correctly in PIM module (Negative)");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        // Enter invalid characters in first name
        pim.enterFirstName("12345@#$");
        pim.enterLastName("67890!@#");
        pim.clickSave();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isValidationErrorDisplayed(),
                "Validation error should appear for invalid characters in name fields");
        Reporter.log("TC_PIM_075 - PASSED: Validation errors displayed for invalid input");
    }

    // To verify that mandatory fields are validated in PIM module.
    @Test(priority = 16)
    public void TC_PIM_076() {
        Reporter.log("TC_PIM_076 - Verify mandatory fields are validated in PIM module");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        // Leave first name empty, enter only last name
        pim.enterLastName("OnlyLastName");
        pim.clickSave();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isRequiredFieldErrorDisplayed(),
                "'Required' error should be shown for empty First Name");
        Reporter.log("TC_PIM_076 - PASSED: Mandatory First Name field validation working");
    }

    // To verify that user can view details in PIM module.
    @Test(priority = 17)
    public void TC_PIM_077() {
        Reporter.log("TC_PIM_077 - Verify user can view details in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getEmployeeTableRowCount() > 0, "Records should exist to view");

        // Get info from the first row before clicking
        String empId = pim.getCellText(0, 1);

        pim.clickEditOnFirstRow();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),
                "Employee Personal Details page should display");
        Reporter.log("TC_PIM_077 - PASSED: Successfully viewed employee details for ID: " + empId);
    }

    // To verify that user can export data from PIM module.
    @Test(priority = 18)
    public void TC_PIM_078() {
        Reporter.log("TC_PIM_078 - Verify user can export data from PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        int recordCount = pim.getEmployeeTableRowCount();
        Assert.assertTrue(recordCount > 0, "Records should be available to export");

        pim.clickExportCSV();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPIMPageDisplayed(),
                "PIM page should remain displayed after export action");
        Reporter.log("TC_PIM_078 - PASSED: Export triggered successfully with " + recordCount + " records");
    }

    // To verify that user can filter records in PIM module.
    @Test(priority = 19)
    public void TC_PIM_079() {
        Reporter.log("TC_PIM_079 - Verify user can filter records in PIM module");
        PIMPage pim = navigateToPIM();

        pim.selectSubUnit("Engineering");
        pim.clickSearch();
        pim.waitForTableToLoad();

        String recordsText = pim.getRecordsFoundText();
        Assert.assertTrue(recordsText.contains("Record"),
                "Filtered results should show record count when filtering by Sub Unit");
        Reporter.log("TC_PIM_079 - PASSED: Records filtered successfully using Sub Unit");
    }

    // To verify that PIM page is displayed correctly.
    @Test(priority = 20)
    public void TC_PIM_080() {
        Reporter.log("TC_PIM_080 - Verify PIM page is displayed correctly (Negative)");
        PIMPage pim = navigateToPIM();

        // Verify all key UI components are present
        softAssert.assertTrue(pim.isPIMPageDisplayed(), "PIM header should be visible");
        softAssert.assertTrue(pim.isEmployeeListTabDisplayed(), "Employee List tab should be visible");
        softAssert.assertTrue(pim.isAddEmployeeTabDisplayed(), "Add Employee tab should be visible");
        softAssert.assertTrue(pim.isSearchButtonDisplayed(), "Search button should be visible");
        softAssert.assertTrue(pim.isResetButtonDisplayed(), "Reset button should be visible");
        softAssert.assertTrue(pim.isAddButtonDisplayed(), "Add button should be visible");
        softAssert.assertTrue(pim.isEmployeeTableDisplayed(), "Employee table should be displayed");
        softAssert.assertTrue(pim.isRecordsFoundLabelDisplayed(), "Records Found label should be visible");
        softAssert.assertAll();
        Reporter.log("TC_PIM_080 - PASSED: All PIM page UI elements verified");
    }

    // To verify that user can add new record in PIM module.
    @Test(priority = 21)
    public void TC_PIM_081() {
        Reporter.log("TC_PIM_081 - Verify user can add new record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.addEmployee("Robert", "James", "Wilson");
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),
                "Personal Details should be displayed after adding employee");
        Reporter.log("TC_PIM_081 - PASSED: New employee Robert James Wilson added successfully");
    }

    // To verify that user can edit existing record in PIM module.
    @Test(priority = 22)
    public void TC_PIM_082() {
        Reporter.log("TC_PIM_082 - Verify user can edit existing record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getEmployeeTableRowCount() > 0, "Records must exist");

        pim.clickEditOnFirstRow();
        pim.waitForPageToLoad();
        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(), "Personal Details page should load");

        pim.editEmployeeFirstName("ModifiedFirst082");
        pim.editEmployeeLastName("ModifiedLast082");
        pim.clickSave();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isSuccessToastDisplayed(),
                "Success toast should be displayed after saving edited employee");
        Reporter.log("TC_PIM_082 - PASSED: Employee record edited and saved successfully");
    }

    // To verify that user can delete record in PIM module.
    @Test(priority = 23)
    public void TC_PIM_083() {
        Reporter.log("TC_PIM_083 - Verify user can delete record in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        int beforeCount = pim.getEmployeeTableRowCount();
        Assert.assertTrue(beforeCount > 0, "Records must exist to delete");

        pim.deleteFirstEmployee();
        pim.waitForTableToLoad();

        Assert.assertTrue(pim.isSuccessToastDisplayed(),
                "Success toast should appear after deleting employee record");
        Reporter.log("TC_PIM_083 - PASSED: Employee record deleted successfully via row delete button");
    }

    // To verify that user can search records in PIM module.
    @Test(priority = 24)
    public void TC_PIM_084() {
        Reporter.log("TC_PIM_084 - Verify user can search records in PIM module");
        PIMPage pim = navigateToPIM();

        // Search with a non-existing employee name
        pim.enterEmployeeName("ZZZNonExistentEmployee999");
        pim.clickSearch();
        pim.waitForTableToLoad();

        // Verify that it shows "No Records Found" or zero records
        String recordsText = pim.getRecordsFoundText();
        boolean noResults = pim.isNoRecordsFound() || recordsText.contains("(0)");
        Assert.assertTrue(noResults,
                "Search with non-existing name should show no records");
        Reporter.log("TC_PIM_084 - PASSED: Search with non-existing name correctly returns no results");
    }

    // To verify that validation works correctly in PIM module.
    @Test(priority = 25)
    public void TC_PIM_085() {
        Reporter.log("TC_PIM_085 - Verify validation works correctly in PIM module (Negative)");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        // Enter extremely long first name (exceeding character limit)
        String longName = "A".repeat(100);
        pim.enterFirstName(longName);
        pim.enterLastName("NormalLast");
        pim.clickSave();
        pim.waitForPageToLoad();

        // Validation should trigger or the name should be truncated
        boolean hasValidation = pim.isValidationErrorDisplayed();
        boolean savedSuccessfully = pim.isPersonalDetailsPageDisplayed();
        Assert.assertTrue(hasValidation || savedSuccessfully,
                "System should either show validation error or successfully handle long input");
        Reporter.log("TC_PIM_085 - PASSED: Validation/handling for long input verified");
    }

    // To verify that mandatory fields are validated in PIM module.
    @Test(priority = 26)
    public void TC_PIM_086() {
        Reporter.log("TC_PIM_086 - Verify mandatory fields are validated in PIM module");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        // Leave both first and last name empty, click save
        pim.clickSave();
        pim.waitForPageToLoad();

        int errorCount = pim.getRequiredFieldErrorCount();
        Assert.assertTrue(errorCount >= 2,
                "At least 2 'Required' errors should display (First Name and Last Name)");

        String errorText = pim.getFirstValidationErrorText();
        Assert.assertTrue(errorText.contains("Required"),
                "Error message should contain 'Required'");
        Reporter.log("TC_PIM_086 - PASSED: Both mandatory fields show 'Required' error (" + errorCount + " errors found)");
    }

    // To verify that user can view details in PIM module.
    @Test(priority = 27)
    public void TC_PIM_087() {
        Reporter.log("TC_PIM_087 - Verify user can view details in PIM module");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getEmployeeTableRowCount() > 0, "Employee records must exist");

        // Read table cell data before navigating
        String firstName = pim.getCellText(0, 2);
        String lastName = pim.getCellText(0, 3);
        Reporter.log("Viewing details for: " + firstName + " " + lastName);

        pim.clickEditOnFirstRow();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(),
                "Personal Details page should be displayed");
        Assert.assertTrue(pim.getCurrentUrl().contains("/viewPersonalDetails"),
                "URL should indicate personal details view");
        Reporter.log("TC_PIM_087 - PASSED: Details page displays correctly for " + firstName + " " + lastName);
    }

    // To verify that user can export data from PIM module.
    @Test(priority = 28)
    public void TC_PIM_088() {
        Reporter.log("TC_PIM_088 - Verify user can export data from PIM module");
        PIMPage pim = navigateToPIM();

        // First filter data then export
        pim.enterEmployeeIdSearch("0001");
        pim.clickSearch();
        pim.waitForTableToLoad();

        pim.clickExportCSV();
        pim.waitForPageToLoad();

        Assert.assertTrue(pim.isPIMPageDisplayed(),
                "PIM page should remain after export of filtered data");
        Reporter.log("TC_PIM_088 - PASSED: Export of filtered data completed successfully");
    }

    // To verify that user can filter records in PIM module.
    @Test(priority = 29)
    public void TC_PIM_089() {
        Reporter.log("TC_PIM_089 - Verify user can filter records in PIM module");
        PIMPage pim = navigateToPIM();

        // Apply multiple filters
        pim.selectEmploymentStatus("Full-Time Permanent");
        pim.selectInclude("Current Employees Only");
        pim.clickSearch();
        pim.waitForTableToLoad();

        String recordsText = pim.getRecordsFoundText();
        Assert.assertTrue(recordsText.contains("Record"),
                "Filtered results should show record count with combined filters");

        // Reset filters and verify all records return
        pim.clickReset();
        pim.waitForTableToLoad();
        String resetRecords = pim.getRecordsFoundText();
        Assert.assertTrue(resetRecords.contains("Record"),
                "After reset, all records should be displayed");
        Reporter.log("TC_PIM_089 - PASSED: Multi-filter and reset working correctly");
    }

    // To verify that PIM page is displayed correctly.
    @Test(priority = 30)
    public void TC_PIM_090() {
        Reporter.log("TC_PIM_090 - Verify PIM page is displayed correctly (Negative)");
        PIMPage pim = navigateToPIM();

        // Comprehensive page layout verification
        softAssert.assertTrue(pim.isPIMPageDisplayed(), "PIM header must be visible");
        softAssert.assertEquals(pim.getPIMHeaderText(), "PIM", "Header text must be 'PIM'");
        softAssert.assertTrue(pim.isOnPIMPage(), "URL should contain '/pim'");
        softAssert.assertTrue(pim.isEmployeeListTabDisplayed(), "Employee List tab must be visible");
        softAssert.assertTrue(pim.isAddEmployeeTabDisplayed(), "Add Employee tab must be visible");
        softAssert.assertTrue(pim.isSearchButtonDisplayed(), "Search button must be visible");
        softAssert.assertTrue(pim.isResetButtonDisplayed(), "Reset button must be visible");
        softAssert.assertTrue(pim.isAddButtonDisplayed(), "Add button must be visible");
        softAssert.assertTrue(pim.isEmployeeTableDisplayed(), "Employee table must be present");
        softAssert.assertTrue(pim.isRecordsFoundLabelDisplayed(), "Records Found label must be visible");
        softAssert.assertAll();
        Reporter.log("TC_PIM_090 - PASSED: Complete PIM page layout verification successful");
    }
}