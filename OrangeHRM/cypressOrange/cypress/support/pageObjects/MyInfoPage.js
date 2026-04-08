class MyInfoPage {
    visit() {
        cy.get('a[href*="/pim/viewMyDetails"]').click();
    }

    verifyPersonalDetailsVisible() {
        cy.url().should('include', '/pim/viewPersonalDetails/empNumber');
        cy.get('h6').should('contain.text', 'Personal Details');
    }

    updateFirstName(newName) {
        cy.get('input[name="firstName"]').clear().type(newName);
    }

    savePersonalDetails() {
        // Find the first save button (Personal Details section)
        cy.get('button[type="submit"]').first().click();
    }
}
export default new MyInfoPage();
