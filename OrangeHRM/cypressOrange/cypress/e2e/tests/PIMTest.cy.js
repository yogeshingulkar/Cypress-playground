import LoginPage from '../../support/pageObjects/LoginPage';
import DashboardPage from '../../support/pageObjects/DashboardPage';
import PIMPage from '../../support/pageObjects/PIMPage';

describe('PIM Test Suite', () => {

    beforeEach(() => {
        LoginPage.visit();
        LoginPage.login('Admin', 'admin123');
        DashboardPage.goToPIM();

        cy.url().should('include', '/pim/viewEmployeeList');
        cy.get('.oxd-table-body').should('be.visible');
    });

    it('TC_PIM_061 - Verify user can add new record in PIM module', () => {

        PIMPage.clickAddButton();
        cy.url().should('include', '/pim/addEmployee');

        PIMPage.enterFirstName('Tejas');
        PIMPage.enterMiddleName('Michael');
        PIMPage.enterLastName('Dole');

        PIMPage.enterEmployeeId();

        cy.contains('Save').should('be.enabled').click();

        cy.url().should('include', '/pim/viewPersonalDetails');
        cy.get('h6').should('contain', 'Personal Details');
    });

    it('TC_PIM_064 - Verify user can search records in PIM module', () => {

        PIMPage.enterEmployeeIdSearch('0001');
        PIMPage.clickSearch();

        cy.get('.oxd-table-body').should('be.visible');
        cy.contains('Record').should('be.visible');
    });

    it('TC_PIM_062, 063 - Verify edit and delete records in PIM module', () => {

        PIMPage.clickEditOnFirstRow();
        cy.url().should('include', '/pim/viewPersonalDetails');

        DashboardPage.goToPIM();
        cy.url().should('include', '/pim/viewEmployeeList');

        cy.get('.oxd-table-card')
            .should('be.visible')
            .and('have.length.greaterThan', 0);

        cy.get('.oxd-table-card')
            .first()
            .find('button')
            .eq(1)
            .should('be.visible')
            .click();

        cy.contains('Yes, Delete')
            .should('be.visible')
            .click();

        cy.get('.oxd-toast')
            .should('be.visible')
            .and('contain.text', 'Success');
    });
});