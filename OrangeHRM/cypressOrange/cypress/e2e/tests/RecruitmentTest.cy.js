import LoginPage from '../../support/pageObjects/LoginPage';
import RecruitmentPage from '../../support/pageObjects/RecruitmentPage';

describe('Recruitment Test Suite', () => {
    beforeEach(() => {
        LoginPage.visit();
        LoginPage.login('Admin', 'admin123');
        RecruitmentPage.visit();
    });

    it('TC_REC_001 - Verify candidates list is loaded', () => {
        RecruitmentPage.verifyCandidatesVisible();
    });

    it('TC_REC_002 - Add a new candidate', () => {
        RecruitmentPage.addCandidate();
        cy.url().should('include', '/recruitment/addCandidate');
        RecruitmentPage.fillCandidateDetails('John', 'Doe', 'john.doe@test.com');
        RecruitmentPage.saveCandidate();
        
        cy.get('.oxd-toast').should('be.visible').and('contain', 'Success');
    });
});
