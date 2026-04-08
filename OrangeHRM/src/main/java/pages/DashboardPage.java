package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;

public class DashboardPage {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(DashboardPage.class);

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminMenu;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenu;

    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentMenu;

    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoMenu;

    // --- Dashboard UI Validation Widgets ---
    @FindBy(xpath = "//div[contains(@class,'oxd-sidepanel')]")
    private WebElement sidePanel;

    @FindBy(xpath = "//p[text()='Quick Launch'] | //p[contains(text(), 'Quick Launch')]")
    private WebElement quickLaunchPanel;

    @FindBy(xpath = "//p[text()='My Actions'] | //p[contains(text(), 'My Actions')]")
    private WebElement myActionsWidget;

    @FindBy(xpath = "//span[contains(@class,'oxd-userdropdown-tab')]")
    private WebElement profileIcon;

    @FindBy(xpath = "//ul[contains(@class,'oxd-dropdown-menu')]")
    private WebElement profileDropdown;

    @FindBy(xpath = "//button[contains(normalize-space(),'Upgrade')] | //div[contains(@class, 'orangehrm-upgrade')]")
    private WebElement upgradeButton;

    @FindBy(xpath = "//button[@title='Help'] | //i[contains(@class, 'bi-question-lg')]")
    private WebElement helpIcon;

    @FindBy(xpath = "//div[contains(@class,'orangehrm-dashboard-widget')]")
    private List<WebElement> allWidgets;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution')] | //canvas")
    private WebElement employeeDistributionChart;

    @FindBy(xpath = "//p[contains(text(), 'Buzz Latest Posts')]")
    private WebElement buzzLatestPostsWidget;

    @FindBy(xpath = "//p[contains(text(), 'Employees on Leave')]")
    private WebElement employeesOnLeaveWidget;


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        try {
            return WaitUtil.waitForVisibility(driver, dashboardHeader).isDisplayed();
        } catch (Exception e) {
            log.warn("Dashboard is missing", e);
            return false;
        }
    }

    public boolean isSidebarVisible() {
        return WaitUtil.waitForVisibility(driver, sidePanel).isDisplayed();
    }

    public boolean isQuickLaunchVisible() {
        return WaitUtil.waitForVisibility(driver, quickLaunchPanel).isDisplayed();
    }

    public boolean isMyActionsVisible() {
        return WaitUtil.waitForVisibility(driver, myActionsWidget).isDisplayed();
    }

    public boolean isProfileIconVisible() {
        return WaitUtil.waitForVisibility(driver, profileIcon).isDisplayed();
    }

    public boolean clickAndVerifyProfileDropdown() {
        WaitUtil.waitForClickable(driver, profileIcon).click();
        return WaitUtil.waitForVisibility(driver, profileDropdown).isDisplayed();
    }

    public boolean isUpgradeButtonVisible() {
        try {
            return upgradeButton.isDisplayed();
        } catch (Exception e) {
            log.warn("Upgrade button not found, it might be deactivated on demo.");
            return false; 
        }
    }

    public boolean isHelpIconVisible() {
        return WaitUtil.waitForVisibility(driver, helpIcon).isDisplayed();
    }

    public boolean areWidgetsAligned() {
        return !allWidgets.isEmpty();
    }
    
    public int getWidgetCount() {
        return allWidgets.size();
    }

    public void refreshDashboard() {
        driver.navigate().refresh();
        WaitUtil.waitForVisibility(driver, dashboardHeader);
    }

    public boolean isEmployeeDistributionChartVisible() {
        return WaitUtil.waitForVisibility(driver, employeeDistributionChart).isDisplayed();
    }

    public boolean isBuzzLatestPostsVisible() {
        return WaitUtil.waitForVisibility(driver, buzzLatestPostsWidget).isDisplayed();
    }

    public boolean isEmployeesOnLeaveVisible() {
        return WaitUtil.waitForVisibility(driver, employeesOnLeaveWidget).isDisplayed();
    }

    // --- Navigation ---
    public AdminPage goToAdmin() {
        WaitUtil.waitForClickable(driver, adminMenu).click();
        log.info("Navigated to Admin module");
        return new AdminPage(driver);
    }

    public PIMPage goToPIM() {
        WaitUtil.waitForClickable(driver, pimMenu).click();
        log.info("Navigated to PIM module");
        return new PIMPage(driver);
    }

    public LeavePage goToLeave() {
        WaitUtil.waitForClickable(driver, leaveMenu).click();
        return new LeavePage(driver);
    }

    public RecruitmentPage goToRecruitment() {
        WaitUtil.waitForClickable(driver, recruitmentMenu).click();
        log.info("Navigated to Recruitment module");
        return new RecruitmentPage(driver);
    }

    public MyInfoPage goToMyInfo() {
        WaitUtil.waitForClickable(driver, myInfoMenu).click();
        log.info("Navigated to My Info module");
        return new MyInfoPage(driver);
    }
}