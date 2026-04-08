package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.AdminPage;
import pages.DashboardPage;

public class AdminTest extends BaseClass {

    private AdminPage navigateToAdmin() {
        login();
        DashboardPage dashboard = new DashboardPage(getDriver());
        AdminPage admin = dashboard.goToAdmin();
        admin.waitForPageToLoad();
        return admin;
    }

    @Test(priority = 1)
    public void TC_ADMIN_01_VerifyAdminPage() {
        Reporter.log("TC_ADMIN_01 - Verify Admin Page Displayed");
        AdminPage admin = navigateToAdmin();
        Assert.assertTrue(admin.isAdminPageDisplayed(), "Admin Page should be displayed");
        Reporter.log("TC_ADMIN_01 - PASSED");
    }

    @Test(priority = 2)
    public void TC_ADMIN_02_SearchUser() {
        Reporter.log("TC_ADMIN_02 - Verify search functionality");
        AdminPage admin = navigateToAdmin();
        admin.searchUser("Admin");
        admin.waitForPageToLoad();
        Assert.assertTrue(admin.getUserTableRowCount() > 0, "Users should be found");
        Reporter.log("TC_ADMIN_02 - PASSED");
    }

    @Test(priority = 3)
    public void TC_ADMIN_03_AddUser() {
        Reporter.log("TC_ADMIN_03 - Verify Add new user");
        AdminPage admin = navigateToAdmin();
        admin.addUser("Admin", "a", "Enabled", "TestUserHr1234", "Admin@123!");
        admin.waitForPageToLoad();
        Assert.assertTrue(admin.isSuccessToastDisplayed(), "Success message should display");
        Reporter.log("TC_ADMIN_03 - PASSED");
    }
    
    @Test(priority = 4, dependsOnMethods = "TC_ADMIN_03_AddUser")
    public void TC_ADMIN_04_DeleteUser() {
        Reporter.log("TC_ADMIN_04 - Verify Delete user");
        AdminPage admin = navigateToAdmin();
        admin.searchUser("TestUserHr1234");
        admin.waitForPageToLoad();
        admin.deleteUser();
        admin.waitForPageToLoad();
        Assert.assertTrue(admin.isSuccessToastDisplayed(), "Success message should display");
        Reporter.log("TC_ADMIN_04 - PASSED");
    }
}