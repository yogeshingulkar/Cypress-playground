class TimePage {
    visit() {
        cy.get('a[href*="/time/viewTimeModule"]').click();
    }

    verifyTimeSheetVisible() {
        cy.url().should('include', '/time');
        cy.get('h6').should('contain.text', 'Time');
    }

    fillEmployeeName(name) {
        cy.get('input[placeholder="Type for hints..."]').clear().type(name);
        cy.get('.oxd-autocomplete-dropdown').contains(name).click();
    }

    fillInvalidEmployeeName(name) {
        cy.get('input[placeholder="Type for hints..."]').clear().type(name);
        // Do not click autocomplete dropdown since it won't exist
    }

    getInvalidMessage() {
        // Wait for the specific validation error message under the input
        return cy.get('.oxd-input-group__message');
    }

    clickView() {
        cy.get('button[type="submit"]').click();
    }

    verifyNoRecordsOrInvalid() {
        // When invalid name is typed and View is clicked, validation triggers "Invalid"
        // Wait for validation message
        return cy.get('.oxd-input-group__message').contains('Invalid');
    }

    getTopBarTabs() {
        return cy.get('.oxd-topbar-body-nav-tab');
    }

    clickAttendanceTab() {
        cy.contains('.oxd-topbar-body-nav-tab', 'Attendance').click();
    }

    clickPunchInOut() {
        cy.contains('Punch In/Out').click();
    }

    verifyPunchInPage() {
        cy.get('h6').should('contain', 'Punch In');
    }
}
export default new TimePage();
