const cypress = require('cypress');
cypress.run({
  spec: 'cypress/e2e/custom_commands.cy.js'
}).then(results => {
  console.log(JSON.stringify(results.runs[0].tests.map(t => ({title: t.title.join(' '), state: t.state, err: t.displayError})), null, 2));
}).catch(console.error);
