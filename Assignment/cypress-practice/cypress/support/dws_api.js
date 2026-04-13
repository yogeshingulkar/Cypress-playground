Cypress.Commands.add('getRequest', (url) => {
  cy.request('GET', url)
})

Cypress.Commands.add('postRequest', (url, body) => {
  cy.request('POST', url, body)
})

Cypress.Commands.add('mockApi', (method, url) => {
  cy.intercept(method, url).as('apiCall')
})
//listens @apiCall
