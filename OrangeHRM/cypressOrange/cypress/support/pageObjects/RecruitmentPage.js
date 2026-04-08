class RecruitmentPage {
    visit() {
        cy.get('a[href*="/recruitment/viewRecruitmentModule"]').click();
    }

    verifyCandidatesVisible() {
        cy.url().should('include', '/recruitment/viewCandidates');
        cy.get('.oxd-table-filter-title').should('contain', 'Candidates');
    }

    addCandidate() {
        cy.contains('button', 'Add').click();
    }

    fillCandidateDetails(firstName, lastName, email) {
        cy.get('input[name="firstName"]').clear().type(firstName);
        cy.get('input[name="lastName"]').clear().type(lastName);
        cy.get('input[placeholder="Type here"]').first().clear().type(email);
    }

    saveCandidate() {
        cy.get('button[type="submit"]').click();
    }
}
export default new RecruitmentPage();
