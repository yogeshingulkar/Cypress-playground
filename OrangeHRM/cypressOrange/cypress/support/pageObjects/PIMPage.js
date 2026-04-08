class PIMPage {
    clickAddButton() {
        cy.contains('button', 'Add').click();
    }

    enterFirstName(firstName) {
        cy.get('input[name="firstName"]').clear().type(firstName);
    }

    enterMiddleName(middleName) {
        cy.get('input[name="middleName"]').clear().type(middleName);
    }

    enterLastName(lastName) {
        cy.get('input[name="lastName"]').clear().type(lastName);
    }

    enterEmployeeId() {
        const uniqueId = Math.floor(Math.random() * 1000000).toString();
        cy.get('.oxd-grid-item').contains('Employee Id').parent().parent().find('input').clear().type(uniqueId);
    }

    clickSave() {
        cy.get('button[type="submit"]').click();
    }

    enterEmployeeIdSearch(id) {
        // Find input related to employee ID, which is the second input with class oxd-input usually
        cy.get('label').contains('Employee Id').parent().parent().find('input').clear().type(id);
    }

    enterEmployeeName(name) {
        cy.get('label').contains('Employee Name').parent().parent().find('input').clear().type(name);
    }
    
    selectAutocompleteOption(text) {
        cy.get('.oxd-autocomplete-dropdown').contains(text).click();
    }

    clickSearch() {
        cy.contains('button', 'Search').click();
    }

    clickReset() {
        cy.contains('button', 'Reset').click();
    }

    clickDeleteOnFirstRow() {
        cy.get('.oxd-table-body .oxd-table-row').first().find('.bi-trash').click();
    }

    confirmDelete() {
        cy.get('.oxd-button--label-danger').click();
    }

    clickEditOnFirstRow() {
        cy.get('.oxd-table-body .oxd-table-row').first().find('.bi-pencil-fill').click();
    }

    getSuccessToast() {
        return cy.get('.oxd-toast-content--success');
    }
}
export default new PIMPage();
