import 'cypress-mochawesome-reporter/register';

Cypress.on('uncaught:exception', (err, runnable) => {
  // returning false here prevents Cypress from failing the test on unhandled exceptions from WordPress/Thirdparty
  return false;
});
