import LoginPage from '../../support/pageObjects/LoginPage';
import LeavePage from '../../support/pageObjects/LeavePage';

describe('Leave Test Suite', () => {
    beforeEach(() => {
        LoginPage.visit();
        LoginPage.login('Admin', 'admin123');
        LeavePage.visit();
    });

    it('TC_LEAVE_001 - Verify user can access Leave List', () => {
        LeavePage.verifyLeaveListVisible();
    });

    it('TC_LEAVE_002 - Verify user can access Apply Leave page', () => {
        LeavePage.applyLeaveMenu();
        LeavePage.verifyApplyLeavePage();
    });
});
