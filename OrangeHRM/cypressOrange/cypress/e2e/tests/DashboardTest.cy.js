import LoginPage from '../../support/pageObjects/LoginPage';
import DashboardPage from '../../support/pageObjects/DashboardPage';

describe('Dashboard Test Suite', () => {
    beforeEach(() => {
        LoginPage.visit();
        LoginPage.login('Admin', 'admin123');
        cy.url().should('include', '/dashboard/index');
    });

    it('TC_DASH_001 - Verify Dashboard UI Elements', () => {
        DashboardPage.getWelcomeMessage().should('be.visible');
        cy.get('.orangehrm-dashboard-widget-name').should('have.length.greaterThan', 2); // Verify widgets
    });

    it('TC_DASH_002 - Verify navigation links from Dashboard', () => {
        DashboardPage.goToAdmin();
        cy.url().should('include', '/admin/viewSystemUsers');
        
        cy.visit('/web/index.php/dashboard/index');
        DashboardPage.goToLeave();
        cy.url().should('include', '/leave/viewLeaveList');
    });
});
