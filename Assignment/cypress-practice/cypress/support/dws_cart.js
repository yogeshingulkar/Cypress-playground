Cypress.Commands.add('searchProduct', (product) => {
  cy.get('#small-searchterms').clear().type(product)
  cy.get('input[value="Search"]').click()
})

Cypress.Commands.add('addFirstProductToCart', () => {
  cy.get('.product-item').first().find('input[value="Add to cart"]').click()
})

Cypress.Commands.add('openCartHover', () => {
  cy.get('#topcartlink').trigger('mouseenter')
  cy.get('#flyout-cart').invoke('show')
})

Cypress.Commands.add('proceedToCheckout', () => {
  cy.openCartHover()
  cy.contains('Go to cart').click()
  cy.get('#termsofservice').check()
  cy.get('#checkout').click()
})

