describe('Framework Demo', () => {

  beforeEach(() => {
    cy.openSite()
  })

  it('Login Test', () => {
    cy.login('test@test.com', '123456')
    cy.verifyText('Log out')
  })

  it('Search + Add to Cart', () => {
    cy.searchProduct('book')
    cy.addFirstProductToCart()
    cy.verifyText('The product has been added')
  })

  it('Cart Hover', () => {
    cy.openCartHover()
    cy.get('#flyout-cart').should('be.visible')
  })

  it('API Test', () => {
    cy.getRequest('https://demowebshop.tricentis.com/')
  })

})
