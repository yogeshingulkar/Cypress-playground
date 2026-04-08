class CuraHealthPage {
  visit() {
    cy.visit('/')
  }

  getTitle() {
    return cy.title()
  }

  getBrandLogo() {
    return cy.get('img[alt*="cura" i], .custom-logo, .brand, [class*="logo"]')
  }

  getDemoForm() {
    // Check for standard form patterns on landing pages
    return cy.get('form')
  }

  getNavigation() {
    return cy.get('header, nav, .main-navigation')
  }
  
  getFooter() {
    return cy.get('footer')
  }
}

export default CuraHealthPage
