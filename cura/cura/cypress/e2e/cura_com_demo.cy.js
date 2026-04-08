describe('Cura.com - Demo Home Health Tests', () => {

  beforeEach(() => {
    // 1. Viewport manipulation
    cy.viewport(1280, 720)
    
    // 2. Visit the exact URL with UTM parameter as requested
    cy.visit(Cypress.config().baseUrl)
  })

  it('TC_001_Page_Init: Should successfully load the page with correct UTM tag', () => {
    // 3. Asset the URL contains the exact parameters
    cy.url().should('include', 'utm_source=chatgpt.com')

    // 4. Assert title using the standard title assertion
    cy.title().should('contain', 'Demo Home Health | Cura TeleHealth')
    
    // 5. Look for core branding
    cy.get('#logo').should('exist').and('be.visible')
  })

  it('TC_002_Search_Type: Should interact with search feature by clicking and typing', () => {
    // 6. Click action on the search icon to reveal the search input
    cy.get('#et_search_icon').click()

    // 7. Type method: Testing typing and hitting Enter ({enter})
    cy.get('.et-search-field')
      .should('be.visible')
      .type('Remote Patient Monitoring{enter}')

    // 8. Assert URL has dynamically changed due to search execution
    cy.url().should('include', 's=Remote+Patient+Monitoring')
  })

  it('TC_003_Hover_Login: Should test hovering and discovering Login dropdown capabilities', () => {
    // 9. Finding an element containing text 'Login'
    cy.contains('nav', 'Login')
      .find('.menu-item-has-children') // 10. DOM Traversal
      .contains('Login')
      .trigger('mouseover') // 11. Emulating pointer hover
      
    // 12. Validating sub menu items are now accessible in DOM 
    cy.contains('Telemedicine').should('exist')
    
    // Validating href routing is aligned with Telemedicine consults
    cy.contains('Telemedicine').invoke('attr', 'href').should('eq', 'https://consults.cura.com/')
    
    // Same for RPM
    cy.contains('Remote Patient Monitoring').invoke('attr', 'href').should('eq', 'https://rpm.cura.com/welcome')
  })

  it('TC_004_Products_Menu: Dom Traversal asserting product lines', () => {
    // Triggering another menu explicitly 
    cy.get('#top-menu > li').contains('Products').trigger('mouseover')

    // Using .find() to assert children elements 
    cy.get('#top-menu > li').contains('Products').parent().find('.sub-menu').within(() => {
      // 13. Advanced targeting within a selected tree
      cy.get('li').should('have.length.greaterThan', 2)
      cy.contains('CuraVision').should('exist')
      cy.contains('Telemedicine').should('exist')
    })
  })

  it('TC_005_Calendly_Wrap: Validate Demo Intake form presence (iframe)', () => {
    // Since the actual form is within Calendly wrapper, we assert the widget loads successfully.
    // 14. Querying a specific class property
    cy.get('.calendly-inline-widget')
      .should('exist')
      .and('have.attr', 'data-url') // 15. Attribute assertion
      .and('include', 'calendly.com')
      
    // We cannot natively cross-origin Type into an iframe without specific configuration,
    // so asserting the wrapper acts as the functional test for the booking form presentation layout.
  })
})
