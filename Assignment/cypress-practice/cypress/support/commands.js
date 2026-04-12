// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })


//above data is by-default provided by cypress when we create a new project. We can add our custom commands here. For example, if we want to create a custom command for login, we can do it like this:

Cypress.Commands.add('openSite', () => {
  cy.visit('https://demowebshop.tricentis.com/')
})

Cypress.Commands.add('verifyText', (text) => {
  cy.contains(text).should('be.visible')
})

Cypress.Commands.add('clickByText', (text) => {
  cy.contains(text).click()
})

Cypress.Commands.add('login', (email, password) => {
  cy.openSite()
  cy.clickByText('Log in')
  cy.get('#Email').type(email)
  cy.get('#Password').type(password)
  cy.get('input[value="Log in"]').click()
})

Cypress.Commands.add('addToCart', (productName) => {
  cy.contains(productName).click()
  cy.get('#add-to-cart-button-1').click()
})

Cypress.Commands.add('checkout', () => {
  cy.clickByText('Shopping cart')
  cy.get('#termsofservice').check()
  cy.get('#checkout').click()
})

Cypress.Commands.add('fillBillingAddress', (address) => {
  cy.get('#BillingNewAddress_FirstName').type(address.firstName)
  cy.get('#BillingNewAddress_LastName').type(address.lastName)
  cy.get('#BillingNewAddress_Email').type(address.email)
  cy.get('#BillingNewAddress_CountryId').select(address.country)
  cy.get('#BillingNewAddress_City').type(address.city)
  cy.get('#BillingNewAddress_Address1').type(address.address1)
  cy.get('#BillingNewAddress_ZipPostalCode').type(address.zip)
  cy.get('#BillingNewAddress_PhoneNumber').type(address.phone)
  cy.get('input[name="save"]').click()
})

