package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.DashboardPage;
import pages.PIMPage;

public class PIMTest1 extends BaseClass {

    private PIMPage navigateToPIM() {
        login();
        DashboardPage dashboard = new DashboardPage(getDriver());
        PIMPage pim = dashboard.goToPIM();
        pim.waitForPageToLoad();
        return pim;
    }

    @Test(priority = 1)
    public void verifyPIMPageUIElements() {
        Reporter.log("TC_PIM_UI - Verifying PIM page layout and elements");
        PIMPage pim = navigateToPIM();

        softAssert.assertTrue(pim.isPIMPageDisplayed(), "PIM header should be visible");
        softAssert.assertEquals(pim.getPIMHeaderText(), "PIM", "Header text should be 'PIM'");
        softAssert.assertTrue(pim.isEmployeeListTabDisplayed(), "Employee List tab should be visible");
        softAssert.assertTrue(pim.isAddEmployeeTabDisplayed(), "Add Employee tab should be visible");
        softAssert.assertTrue(pim.isSearchButtonDisplayed(), "Search button should be visible");
        softAssert.assertTrue(pim.isAddButtonDisplayed(), "Add button should be visible");
        softAssert.assertTrue(pim.isEmployeeTableDisplayed(), "Employee table should be displayed");
        softAssert.assertAll();
        
        Reporter.log("TC_PIM_UI - PASSED: All UI elements verified");
    }

    @Test(priority = 2)
    public void verifyAddNewEmployee() {
        Reporter.log("TC_PIM_ADD - Verify user can add a new employee record");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();
        Assert.assertTrue(pim.isOnAddEmployeePage(), "Should be on Add Employee page");

        pim.enterFirstName("Tejas");
        pim.enterMiddleName("Michael");
        pim.enterLastName("Dole");
        pim.clickSave();

        pim.waitForPageToLoad();
        Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(), "Should display Personal Details after save");
        Reporter.log("TC_PIM_ADD - PASSED: New employee added successfully");
    }

    @Test(priority = 3)
    public void verifyEditEmployeeRecord() {
        Reporter.log("TC_PIM_EDIT - Verify user can edit an existing record");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getEmployeeTableRowCount() > 0, "Table must have records to edit");

        pim.clickEditOnFirstRow();
        pim.waitForPageToLoad();

        pim.editEmployeeFirstName("UpdatedName");
        pim.clickSave();
        
        pim.waitForPageToLoad();
        Assert.assertTrue(pim.isSuccessToastDisplayed(), "Success toast should appear after edit");
        Reporter.log("TC_PIM_EDIT - PASSED: Employee record updated successfully");
    }

    @Test(priority = 4)
    public void verifySearchFunctionality() {
        Reporter.log("TC_PIM_SEARCH - Verify search by ID and Name");
        PIMPage pim = navigateToPIM();

        // Search by ID
        pim.enterEmployeeIdSearch("0001");
        pim.clickSearch();
        pim.waitForTableToLoad();
        Assert.assertTrue(pim.getRecordsFoundText().contains("Record"), "Search should return results");

        // Search by Name (Negative/Edge Case)
        pim.clickReset();
        pim.enterEmployeeName("ZZZNonExistent999");
        pim.clickSearch();
        Assert.assertTrue(pim.isNoRecordsFound(), "Should show 'No Records Found' for invalid name");
        Reporter.log("TC_PIM_SEARCH - PASSED: Search functionality verified");
    }

    @Test(priority = 5)
    public void verifyMandatoryFieldValidation() {
        Reporter.log("TC_PIM_VAL - Verify mandatory field validation (Negative)");
        PIMPage pim = navigateToPIM();

        pim.clickAddButton();
        pim.waitForPageToLoad();

        // Attempt save with empty fields
        pim.clickSave();
        Assert.assertTrue(pim.isRequiredFieldErrorDisplayed(), "Required error should show for empty fields");
        Assert.assertTrue(pim.getRequiredFieldErrorCount() >= 2, "First and Last name should trigger errors");
        Reporter.log("TC_PIM_VAL - PASSED: Validation errors displayed correctly");
    }

    @Test(priority = 6)
    public void verifyFilteringAndReset() {
        Reporter.log("TC_PIM_FILTER - Verify filtering by Job Title and Sub Unit");
        PIMPage pim = navigateToPIM();

        pim.selectJobTitle("HR Manager");
        pim.selectSubUnit("Engineering");
        pim.clickSearch();
        pim.waitForTableToLoad();

        Assert.assertTrue(pim.isRecordsFoundLabelDisplayed(), "Filtered results should be visible");

        pim.clickReset();
        pim.waitForTableToLoad();
        Reporter.log("TC_PIM_FILTER - PASSED: Filtering and Reset functionality verified");
    }

    @Test(priority = 7)
    public void verifyExportFunctionality() {
        Reporter.log("TC_PIM_EXPORT - Verify data export to CSV");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        pim.clickExportCSV();
        
        // Ensure page remains stable after download trigger
        Assert.assertTrue(pim.isPIMPageDisplayed(), "Page should remain accessible after export");
        Reporter.log("TC_PIM_EXPORT - PASSED: Export initiated successfully");
    }

    @Test(priority = 8)
    public void verifyDeleteEmployee() {
        Reporter.log("TC_PIM_DELETE - Verify record deletion");
        PIMPage pim = navigateToPIM();

        pim.waitForTableToLoad();
        int initialCount = pim.getEmployeeTableRowCount();
        Assert.assertTrue(initialCount > 0, "Need records to perform delete");

        pim.deleteFirstEmployee();
        pim.waitForTableToLoad();

        Assert.assertTrue(pim.isSuccessToastDisplayed(), "Success toast should appear after deletion");
        Reporter.log("TC_PIM_DELETE - PASSED: Record deleted successfully");
    }
}