const { defineConfig } = require("cypress");
const { downloadFile } = require('cypress-downloadfile/lib/addPlugin');

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      on('task', { downloadFile });
      return config;
    },
    specPattern: "cypress/e2e/**/*.cy.{js,jsx,ts,tsx}",
    supportFile: "cypress/support/e2e.js",
    defaultCommandTimeout: 5000,
    env: {
      "baseUrl-1": "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login",
      "email-1": "Admin",
      "password-1": "admin123"
    }
  }
});
