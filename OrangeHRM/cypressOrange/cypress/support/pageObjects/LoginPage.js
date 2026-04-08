class LoginPage {
    visit() {
        cy.visit('/web/index.php/auth/login');
    }
    
    getLogo() {
        return cy.get('.orangehrm-login-branding > img');
    }

    fillUsername(username) {
        cy.get('input[name="username"]').clear().type(username);
    }

    fillPassword(password) {
        cy.get('input[name="password"]').clear().type(password);
    }

    submit() {
        cy.get('button[type="submit"]').click();
    }

    login(username, password) {
        this.fillUsername(username);
        this.fillPassword(password);
        this.submit();
    }

    getErrorMessage() {
        return cy.get('.oxd-alert-content-text');
    }

    getRequiredMessages() {
        return cy.get('.oxd-input-group__message');
    }

    clickForgotPassword() {
        cy.get('.orangehrm-login-forgot').click();
    }
}
export default new LoginPage();
