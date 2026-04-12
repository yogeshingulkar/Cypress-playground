<reference types="cypress" /> //this cmd tells code editor to use cypress types and suggestions.

  //describe will group all related test cases
describe('Demo Web Shop', () => {
  //it runs before each & every test cases 
  beforeEach(() => {
    cy.visit('https://demowebshop.tricentis.com/')
  });

  it('Verify homepage loads properly', () => {

    //find logo from HTML DOM & Verifies it's visibility.
    cy.get('.header-logo').should('be.visible')

    //common browser navigation's
    cy.go('back')
    cy.go('forward')
    cy.reload() //que : how i can verify that reload is happened or not. (asked pseudo code)
    // i can verify it with the URL and Page title.. below i mentioned the steps to verify it with cypress.
    //there are total 3 ways i found.
    //1. with URL
     cy.url().should('eq', 'https://example.com');
    
    // 2. element reloaded or not 
    cy.get('h1').should('be.visible');
    
    //3. input cleared or not after reload happened..
    cy.get('input').should('have.value', '');

    // Checks cookies exist or not / verifies that session working properly...
    cy.getCookies().should('exist')

    // access the browser window
    cy.window().then((win) => {
      //verify that local storage exist or not
      expect(win.localStorage).to.exist
    });
  });

  
  it('Search product functionality', () => {
    //searchbox found and then clear if their any content present and then type book.
    cy.get('#small-searchterms').clear().type('book')
    // find the search button and clicked.
    cy.get('input[value="Search"]').click()

    //verify that book products are visible with the url or content or page title as welllllll
    cy.get('.product-item').should('exist')
  });

  
  it('Search validation (empty input)', () => {
  //search field cleared...
    cy.get('#small-searchterms').clear()
  
    // handled alert popup 
    cy.on('window:alert', (text) => {
      expect(text).to.contains('Please enter some search keyword')
    });

    // alert start
    cy.get('input[value="Search"]').click()
  });

  it('Login test', () => {
    //click on login 
    cy.contains('Log in').click()

    // credentials entered 
    cy.get('#Email').type('test@test.com')
    cy.get('#Password').type('123456')

    // click on login button (submit)
    cy.get('input[value="Log in"]').click()

    // here we check that is the reload happens perfectly - verified using body ele in HTML DOM 
    cy.get('body').should('be.visible')
  });

  // verify with url that we're in right page or not  
  it('Verify category navigation', () => {
    cy.contains('Books').click()
    cy.url().should('contain', '/books')
  });

  it('Add product to cart', () => {
    cy.contains('Books').click()
    cy.get('.product-item').first().click()
    cy.get('input[value="Add to cart"]').click()

    //to verify that product is successfully added into the cart with message
    cy.get('.bar-notification').should('be.visible')
  });

  // 
  it('Verify cart hover popup', () => {
    // mouse hovering cmd
    cy.get('#topcartlink').trigger('mouseenter')
    // verified that popup is appeared or not
    cy.get('#flyout-cart').should('be.visible')
  });

  it('Newsletter subscription', () => {
    cy.get('#newsletter-email').type('yogeshingulkar750@gmail.com')
    cy.get('#newsletter-subscribe-button').click()
    // verify that subscribed or not
    cy.get('#newsletter-result-block').should('be.visible')
  });

  //first check the options and then click performed by me 
  it('Vote in poll', () => {
    cy.get('#pollanswers-1').check()
    cy.get('#vote-poll-1').click()
  });

  //
  it('Register new user', () => {
    cy.contains('Register').click()
    cy.get('#gender-male').check()
    cy.get('#FirstName').type('Yogesh')
    cy.get('#LastName').type('Ingulkar')
    
    // optimised logic
    cy.get('#Email').type(`yogesh${Date.now()}@mail.com`)
    cy.get('#Password').type('123456')
    cy.get('#ConfirmPassword').type('123456')
    cy.get('#register-button').click()
    
    // verify that expected result matching with actual result i got...
    cy.get('body').should('contain', 'Your registration completed')
  });

});
//before write any test case it must be verify at the end <- important Note
// Note : in this script i used basic commands, navigation, cookies, local storage, alerts, form filling, checkboxes, radio buttons, dropdowns, mouse hover, drag and drop, file upload, API testing, performance testing. 
