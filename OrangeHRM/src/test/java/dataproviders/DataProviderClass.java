package dataproviders;

import org.testng.annotations.DataProvider;
import utilities.ExcelUtil;

public class DataProviderClass {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtil.getData("Login");
    }

    @DataProvider(name = "adminData")
    public Object[][] getAdminData() {
        return ExcelUtil.getData("Admin");
    }

    @DataProvider(name = "pimData")
    public Object[][] getPIMData() {
        return ExcelUtil.getData("PIM");
    }

    @DataProvider(name = "leaveData")
    public Object[][] getLeaveData() {
        return ExcelUtil.getData("Leave");
    }
}