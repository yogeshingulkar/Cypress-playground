const fs = require('fs');
const cypress = require('cypress');
cypress.run({
  spec: 'cypress/e2e/cypress_commands.cy.js'
}).then(results => {
  const errors = [];
  results.runs[0].tests.forEach(t => {
    if (t.state === 'failed') {
      errors.push({
        title: t.title.join(' '),
        error: t.displayError
      });
    }
  });
  fs.writeFileSync('errors2.json', JSON.stringify(errors, null, 2));
}).catch(console.error);
