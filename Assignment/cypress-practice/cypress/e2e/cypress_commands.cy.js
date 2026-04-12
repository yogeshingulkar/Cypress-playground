<reference types="cypress" />

describe('Demo Web Shop', () => {
  beforeEach(() => {
    cy.visit('https://demowebshop.tricentis.com/')
  })

  it('Verify homepage loads properly', () => {
    cy.get('.header-logo').should('be.visible')

    cy.go('back')
    cy.go('forward')
    cy.reload()

    cy.getCookies().should('exist')

    cy.window().then((win) => {
      expect(win.localStorage).to.exist
    })
  })

  it('Search product functionality', () => {
    cy.get('#small-searchterms').clear().type('book')
    cy.get('input[value="Search"]').click()

    cy.get('.product-item').should('exist')
  })

  it('Search validation (empty input)', () => {
    cy.get('#small-searchterms').clear()

    cy.on('window:alert', (text) => {
      expect(text).to.contains('Please enter some search keyword')
    })

    cy.get('input[value="Search"]').click()
  })

  it('Login test', () => {
    cy.contains('Log in').click()

    cy.get('#Email').type('test@test.com')
    cy.get('#Password').type('123456')

    cy.get('input[value="Log in"]').click()

    cy.get('body').should('be.visible')
  })

  it('Verify category navigation', () => {
    cy.contains('Books').click()

    cy.url().should('contain', '/books')
  })

  it('Add product to cart', () => {
    cy.contains('Books').click()

    cy.get('.product-item').first().click()

    cy.get('input[value="Add to cart"]').click()

    cy.get('.bar-notification').should('be.visible')
  })

  it('Verify cart hover popup', () => {
    cy.get('#topcartlink').trigger('mouseenter')

    cy.get('#flyout-cart').should('be.visible')
  })

  it('Newsletter subscription', () => {
    cy.get('#newsletter-email').type('test@test.com')

    cy.get('#newsletter-subscribe-button').click()

    cy.get('#newsletter-result-block').should('be.visible')
  })

  it('Vote in poll', () => {
    cy.get('#pollanswers-1').check()

    cy.get('#vote-poll-1').click()
  })

  it('Register new user', () => {
    cy.contains('Register').click()
    
    cy.get('#gender-male').check()

    cy.get('#FirstName').type('Yogesh')
    cy.get('#LastName').type('Ingulkar')

    cy.get('#Email').type(`yogesh${Date.now()}@mail.com`)

    cy.get('#Password').type('123456')
    cy.get('#ConfirmPassword').type('123456')

    cy.get('#register-button').click()

    cy.get('body').should('contain', 'Your registration completed')
  })

})

// Note : in this script i used basic commands, navigation, cookies, local storage, alerts, form filling, checkboxes, radio buttons, dropdowns, mouse hover, drag and drop, file upload, API testing, performance testing. 

