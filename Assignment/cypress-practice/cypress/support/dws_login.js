Cypress.Commands.add('login', (email, password) => {
  cy.contains('Log in').click()

  cy.get('#Email').clear().type(email)
  cy.get('#Password').clear().type(password)

  cy.get('input[value="Log in"]').click()
})

Cypress.Commands.add('logout', () => {
  cy.contains('Log out').click()
})

Cypress.Commands.add('register', (firstName, lastName, email, password) => {
  cy.contains('Register').click()
    cy.get('#FirstName').type(firstName)
    cy.get('#LastName').type(lastName)
    cy.get('#Email').type(email)
    cy.get('#Password').type(password)
    cy.get('#ConfirmPassword').type(password)
    cy.get('#register-button').click()
})

Cypress.Commands.add('addToCart', (productName) => {
  cy.contains(productName).click()
  cy.get('#add-to-cart-button-1').click()
})
