class AdminPage {
    enterUsername(username) {
        cy.get('label').contains('Username').parent().parent().find('input').clear().type(username);
    }

    clickSearch() {
        cy.contains('button', 'Search').click();
    }

    getRecordsFoundText() {
        return cy.get('.oxd-text--span').contains('Record');
    }
}
export default new AdminPage();
