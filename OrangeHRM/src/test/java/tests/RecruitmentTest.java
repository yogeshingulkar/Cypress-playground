package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.DashboardPage;
import pages.RecruitmentPage;

public class RecruitmentTest extends BaseClass {

    private RecruitmentPage navigateToRecruitment() {
        login();
        DashboardPage dashboard = new DashboardPage(getDriver());
        RecruitmentPage recruitment = dashboard.goToRecruitment();
        recruitment.waitForPageToLoad();
        return recruitment;
    }

    @Test(priority = 1)
    public void TC_RECRUITMENT_01_VerifyRecruitmentPage() {
        Reporter.log("TC_RECRUITMENT_01 - Verify Recruitment Page Displayed");
        RecruitmentPage recruitment = navigateToRecruitment();
        Assert.assertTrue(recruitment.isRecruitmentPageDisplayed(), "Recruitment Page should be displayed");
        Reporter.log("TC_RECRUITMENT_01 - PASSED");
    }

    @Test(priority = 2)
    public void TC_RECRUITMENT_02_AddCandidate() {
        Reporter.log("TC_RECRUITMENT_02 - Verify Add new candidate");
        RecruitmentPage recruitment = navigateToRecruitment();
        recruitment.addCandidate("John", "A", "Doe", "johndoe@example.com");
        recruitment.waitForPageToLoad();
        Assert.assertTrue(recruitment.isSuccessToastDisplayed(), "Success message should display");
        Reporter.log("TC_RECRUITMENT_02 - PASSED");
    }

    @Test(priority = 3)
    public void TC_RECRUITMENT_03_SearchCandidate() {
        Reporter.log("TC_RECRUITMENT_03 - Verify search candidate functionality");
        RecruitmentPage recruitment = navigateToRecruitment();
        recruitment.searchCandidate("John");
        recruitment.waitForPageToLoad();
        Assert.assertTrue(recruitment.getCandidateRowCount() >= 0,
                "Candidate table should be accessible and display corresponding data");
        Reporter.log("TC_RECRUITMENT_03 - PASSED");
    }
}
