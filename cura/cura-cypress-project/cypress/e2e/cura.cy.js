import CuraPage from '../pages/curaPage'

/**
 * Mocha Documentation in Cypress
 * 
 * Describe: Used to group logically related test cases. In this suite, we group 
 * tests by page or specific feature.
 * 
 * Context: An alias for describe, usually used to define the state or condition 
 * under which the tests are running (e.g., 'when user is logged in', 'invalid inputs').
 * 
 * It: Represents the actual test case. Should have a clear, descriptive name of 
 * the expected behavior.
 * 
 * Before / BeforeEach: Hooks used to set up preconditions before tests run.
 */
describe('CURA Healthcare End-to-End Test Suite', () => {

  const cura = new CuraPage()
  let userData

  before(() => {
    // Load fixture data once before all tests
    cy.fixture('user').then((data) => {
      userData = data
    })
  })

  beforeEach(() => {
    // Ensure we start on the homepage for each test
    cura.visit()
  })

  context('Authentication Scenarios', () => {
    
    it('should successfully log in with valid credentials', () => {
      cura.clickMakeAppointment()
      cura.login(userData.username, userData.password)
      // Assert successful login by checking URL
      cy.url().should('include', '#appointment')
    })

    it('should fail to log in with invalid password', () => {
      cura.clickMakeAppointment()
      cura.login(userData.username, 'WrongPassword123!')
      cura.getLoginErrorMessage().should('be.visible').and('contain', 'Login failed! Please ensure the username and password are valid.')
    })

    it('should successfully log out', () => {
      cura.clickMakeAppointment()
      cura.login(userData.username, userData.password)
      cura.logout()
      // Assert successful logout by checking the presence of Make Appointment button on home screen
      cy.get('#btn-make-appointment').should('be.visible')
    })
  })

  context('Appointment Booking Scenarios (Authenticated)', () => {
    
    beforeEach(() => {
      // Precondition: User must be logged in to book an appointment
      cura.clickMakeAppointment()
      cura.login(userData.username, userData.password)
    })

    it('should successfully book an appointment with standard inputs', () => {
      const visitDate = '24/11/2026'
      const comment = 'Regular health checkup.'

      cura.selectFacility('Hongkong CURA Healthcare Center')
      cura.checkReadmission(true)
      cura.selectProgram('Medicaid')
      cura.selectDate(visitDate)
      cura.addComment(comment)

      cura.bookAppointment()

      cura.validateAppointment()

      cy.get('#facility').should('contain', 'Hongkong CURA Healthcare Center')
      cy.get('#hospital_readmission').should('contain', 'Yes')
      cy.get('#program').should('contain', 'Medicaid')
      cy.get('#visit_date').should('contain', visitDate)
      cy.get('#comment').should('contain', comment)
    })

    it('should successfully book an appointment with minimum required inputs', () => {
      // Facility is selected by default, Readmission unchecked by default, Medicare is default
      const visitDate = '30/12/2026'
      
      cura.selectDate(visitDate)
      cura.bookAppointment()

      cura.validateAppointment()
      cy.get('#visit_date').should('contain', visitDate)
      cy.get('#comment').should('be.empty')
    })

    it('should not allow booking an appointment without a visit date', () => {
      cura.selectFacility('Seoul CURA Healthcare Center')
      cura.addComment('Trying to book without a date')
      
      cura.bookAppointment()
      
      // We should still be on the appointment page (booking should not proceed)
      cy.url().should('include', '#appointment')
    })
  })

  context('Navigation and UI Consistency', () => {
    
    it('should navigate to History page and assert no history if empty', () => {
      cura.clickMakeAppointment()
      cura.login(userData.username, userData.password)
      
      cura.openMenu()
      cura.clickMenuItem('History')
      
      cy.url().should('include', 'history.php')
      cy.get('.container').should('contain', 'No appointment.')
    })

    it('should display the correct social media links in the footer', () => {
      cy.get('footer').within(() => {
        cy.get('.fa-facebook').should('be.visible')
        cy.get('.fa-twitter').should('be.visible')
        cy.get('.fa-dribbble').should('be.visible')
      })
    })
  })
})