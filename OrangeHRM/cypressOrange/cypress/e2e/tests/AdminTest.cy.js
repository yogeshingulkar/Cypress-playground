import LoginPage from '../../support/pageObjects/LoginPage';
import DashboardPage from '../../support/pageObjects/DashboardPage';
import AdminPage from '../../support/pageObjects/AdminPage';

describe('Admin Test Suite', () => {
    beforeEach(() => {
        LoginPage.visit();
        LoginPage.login('Admin', 'admin123');
        DashboardPage.goToAdmin();
        cy.url().should('include', '/admin/viewSystemUsers');
    });

    it('Admin Module - Search User', () => {
        AdminPage.enterUsername('Admin');
        AdminPage.clickSearch();
        AdminPage.getRecordsFoundText().should('be.visible');
    });
});
