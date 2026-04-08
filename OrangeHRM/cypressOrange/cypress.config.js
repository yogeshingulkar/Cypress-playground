const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    baseUrl: 'https://opensource-demo.orangehrmlive.com',
    setupNodeEvents(on, config) {
      // implement node event listeners

    },
    defaultCommandTimeout: 10000,
    pageLoadTimeout: 60000,
    viewportWidth: 1280,
    viewportHeight: 720,
    video: false,
    chromeWebSecurity: false
  },
});
