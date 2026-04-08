// e2e.js support file
// Cypress will automatically run this before every test
// We can use it to handle runtime exceptions to prevent test failure from application scripts

Cypress.on('uncaught:exception', (err, runnable) => {
    // returning false here prevents Cypress from failing the test
    return false;
});
