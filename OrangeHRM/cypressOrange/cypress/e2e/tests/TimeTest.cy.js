import LoginPage from '../../support/pageObjects/LoginPage';
import TimePage from '../../support/pageObjects/TimePage';

describe('Time Module Test Suite - Positive & Negative Scenarios', () => {
    beforeEach(() => {
        LoginPage.visit();
        LoginPage.login('Admin', 'admin123');
        TimePage.visit();
    });

    it('POS_TC_TIME_001 - Verify navigation to Time Module', () => {
        TimePage.verifyTimeSheetVisible();
        cy.get('.oxd-topbar-header-breadcrumb').should('contain', 'Time');
    });

    it('POS_TC_TIME_002 - Verify all Top Bar Navigation Tabs are visible', () => {
        TimePage.getTopBarTabs().should('have.length.greaterThan', 2);
        TimePage.getTopBarTabs().contains('Timesheets').should('be.visible');
        TimePage.getTopBarTabs().contains('Attendance').should('be.visible');
        TimePage.getTopBarTabs().contains('Reports').should('be.visible');
    });

    it('POS_TC_TIME_003 - Search timesheet for valid employee', () => {
        TimePage.fillEmployeeName('Arjun Das A');
        TimePage.clickView();

        cy.url().should('include', '/time/viewTimesheet');
        cy.get('.orangehrm-paper-container').should('be.visible');
    });

    it('POS_TC_TIME_004 - Navigate to Attendance (Punch In/Out)', () => {
        TimePage.clickAttendanceTab();
        TimePage.clickPunchInOut();
        TimePage.verifyPunchInPage();

        cy.get('.oxd-date-wrapper').should('be.visible');
        cy.get('.oxd-time-wrapper').should('be.visible');
    });

    it('NEG_TC_TIME_005 - Search timesheet with Invalid Employee Name', () => {
        const invalidName = '!!!INVALID_EMP_999!!!';
        TimePage.fillInvalidEmployeeName(invalidName);

        cy.get('body').click(0, 0);

        cy.get('.oxd-autocomplete-dropdown').should('contain', 'No Records Found');

        TimePage.clickView();

        TimePage.getInvalidMessage().should('be.visible').and('contain.text', 'Invalid');
    });

    it('NEG_TC_TIME_006 - Submit Timesheet Search without Employee Name', () => {
        cy.get('button[type="submit"]').should('be.visible');

        cy.get('input[placeholder="Type for hints..."]').clear();
        TimePage.clickView();

        TimePage.getInvalidMessage().should('be.visible').and('contain.text', 'Required');
    });
});
