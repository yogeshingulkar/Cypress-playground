import LoginPage from '../../support/pageObjects/LoginPage';
import MyInfoPage from '../../support/pageObjects/MyInfoPage';

describe('MyInfo Test Suite', () => {
    beforeEach(() => {
        LoginPage.visit();
        LoginPage.login('Admin', 'admin123');
        MyInfoPage.visit();
    });

    it('TC_INFO_001 - Verify user can navigate to My Info', () => {
        MyInfoPage.verifyPersonalDetailsVisible();
    });

    it('TC_INFO_002 - Verify user can update My Info details', () => {
        MyInfoPage.updateFirstName('AdminEdited');
        MyInfoPage.savePersonalDetails();
        
        cy.get('.oxd-toast').should('be.visible').and('contain', 'Success');
    });
});
