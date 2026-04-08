class CuraPage {
  visit() {
    cy.visit('/')
  }

  clickMakeAppointment() {
    cy.get('#btn-make-appointment').should('be.visible').click()
  }

  login(username, password) {
    if (username) cy.get('#txt-username').clear().type(username)
    if (password) cy.get('#txt-password').clear().type(password)
    cy.get('#btn-login').click()
  }

  getLoginErrorMessage() {
    return cy.get('.text-danger')
  }

  selectFacility(facility) {
    cy.get('#combo_facility').select(facility)
  }

  checkReadmission(check = true) {
    if (check) {
      cy.get('#chk_hospotal_readmission').check()
    } else {
      cy.get('#chk_hospotal_readmission').uncheck()
    }
  }

  selectProgram(program) {
    cy.get(`input[name="programs"][value="${program}"]`).check()
  }

  selectDate(date) {
    cy.get('#txt_visit_date').clear().type(`${date}{esc}`)
  }

  addComment(comment) {
    cy.get('#txt_comment').clear().type(comment)
  }

  bookAppointment() {
    cy.get('#btn-book-appointment').click()
  }

  validateAppointment() {
    cy.get('h2').should('contain', 'Appointment Confirmation')
  }

  getAppointmentDetails() {
    return {
      facility: cy.get('#facility'),
      readmission: cy.get('#hospital_readmission'),
      program: cy.get('#program'),
      visitDate: cy.get('#visit_date'),
      comment: cy.get('#comment')
    }
  }

  goToHomepage() {
    cy.contains('Go to Homepage').click()
  }

  openMenu() {
    cy.get('#menu-toggle').click()
  }

  clickMenuItem(itemName) {
    cy.get('#sidebar-wrapper').contains(itemName).click()
  }

  logout() {
    this.openMenu()
    this.clickMenuItem('Logout')
  }
}

export default CuraPage