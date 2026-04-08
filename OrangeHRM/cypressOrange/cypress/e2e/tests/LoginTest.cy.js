import LoginPage from '../../support/pageObjects/LoginPage';

describe('Login Test Suite', () => {
    beforeEach(() => {
        LoginPage.visit();
    });

    it('TC_LOGIN_001 to 006 - Verify UI Elements', () => {
        cy.url().should('include', '/auth/login');
        cy.get('input[name="username"]').should('be.visible').and('have.attr', 'placeholder', 'Username');
        cy.get('input[name="password"]').should('be.visible').and('have.attr', 'placeholder', 'Password');
        cy.get('button[type="submit"]').should('be.enabled');
    });

    it('TC_LOGIN_007 to 009 - Input mechnisms and mask', () => {
        LoginPage.fillUsername('Admin');
        cy.get('input[name="username"]').should('have.value', 'Admin');

        LoginPage.fillPassword('admin123');
        cy.get('input[name="password"]').should('have.value', 'admin123').and('have.attr', 'type', 'password');
    });

    it('TC_LOGIN_013 to 015 - Empty Credentials Test', () => {
        LoginPage.submit();
        LoginPage.getRequiredMessages().should('have.length', 2).each(($el) => {
            cy.wrap($el).should('have.text', 'Required');
        });
    });

    it('TC_LOGIN_011 to 012 - Invalid Logic Test', () => {
        LoginPage.login('InvalidUser', 'InvalidPass123');
        LoginPage.getErrorMessage().should('contain.text', 'Invalid credentials');
    });

    it('TC_LOGIN_010 - Successful Login', () => {
        LoginPage.login('Admin', 'admin123');
        cy.url().should('include', '/dashboard/index');
        cy.get('.oxd-topbar-header-breadcrumb').should('contain', 'Dashboard');
    });

    it('TC_LOGIN_029 - Post-logout navigation', () => {
        LoginPage.login('Admin', 'admin123');
        cy.url().should('include', '/dashboard/index');

        cy.get('.oxd-userdropdown-name').click();
        cy.contains('Logout').click();

        cy.url().should('include', '/auth/login');
        cy.go('back');
        cy.url().should('not.include', '/dashboard/index');
    });
});
