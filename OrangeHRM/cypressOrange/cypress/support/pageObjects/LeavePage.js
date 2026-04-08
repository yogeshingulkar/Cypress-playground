class LeavePage {
    visit() {
        cy.get('a[href*="/leave/viewLeaveModule"]').click();
    }

    verifyLeaveListVisible() {
        cy.url().should('include', '/leave/viewLeaveList');
        cy.get('.oxd-table-filter').should('be.visible');
    }

    applyLeaveMenu() {
        cy.contains('a', 'Apply').click();
    }

    verifyApplyLeavePage() {
        cy.url().should('include', '/leave/applyLeave');
        cy.get('h6').should('contain.text', 'Apply Leave');
    }

    selectLeaveType() {
        cy.get('.oxd-select-text').first().click();
        cy.get('.oxd-select-dropdown').contains('CAN - FMLA').click();
    }
}
export default new LeavePage();
