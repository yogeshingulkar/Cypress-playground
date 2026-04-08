class DashboardPage {
    getWelcomeMessage() {
        return cy.get('.oxd-userdropdown-name');
    }

    goToPIM() {
        cy.get('a[href*="/pim/viewPimModule"]').click();
    }
    
    goToAdmin() {
        cy.get('a[href*="/admin/viewAdminModule"]').click();
    }

    goToLeave() {
        cy.get('a[href*="/leave/viewLeaveModule"]').click();
    }
}
export default new DashboardPage();
