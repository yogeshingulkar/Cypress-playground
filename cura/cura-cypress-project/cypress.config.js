const { defineConfig } = require("cypress");

module.exports = defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  allowCypressEnv: false,

  e2e: {
    baseUrl: 'https://katalon-demo-cura.herokuapp.com',
    setupNodeEvents(on, config) {
      require('cypress-mochawesome-reporter/plugin')(on);
    },
  },
});
