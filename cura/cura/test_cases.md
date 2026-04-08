# CURA TeleHealth - Demo Home Health Test Cases

Since we are testing the specific target url `https://cura.com/demo-home-health/?utm_source=chatgpt.com` provided, which is a conversational lead-generation landing page without native form fields (like select and checkboxes), the focus is placed on the site's primary interactive features: search utility, drop-down menus (hover actions), the Calendly booking iframe, and navigation links.

## Test Cases Detailed

| Test Case ID | Scenario Name | Description | Key Cypress Terms Used |
| :--- | :--- | :--- | :--- |
| **TC_001_Page_Init** | **Viewport & Page Load** | Sets the viewport and asserts the base URL includes query parameters (`utm_source`) correctly. | `cy.viewport()`, `cy.visit()`, `cy.url().should('include')`, `.should('exist')` |
| **TC_002_Search_Type**| **Query Interaction** | Simulates clicking the search icon and typing a query into the dynamic search bar. | `.click()`, `cy.get()`, `.type('{enter}')` |
| **TC_003_Hover_Login**| **Hovering Menus** | Simulates hovering (`.trigger('mouseover')`) over the top navigation "Login" menu to reveal and assert its child dropdown elements. | `.trigger('mouseover')`, `.should('be.visible')`, `.first()`, `cy.contains()` |
| **TC_004_Products_Menu**| **DOM Traversal** | Hovers over the products menu and verifies the presence of specific product solutions, demonstrating Cypress DOM traversal. | `.find()`, `.should('have.attr')`, `.invoke()` |
| **TC_005_Calendly_Wrap**| **iframe Validation** | Asserts the existence of the expected Calendly booking form, since Cura Health uses it for their Demo intake. | `cy.get()`, `.should('have.attr')` |

### Cypress API Employed:
- **Environment**: `cy.visit()`, `cy.url()`, `cy.viewport()`, `{enter}` keyboard event.
- **Locators & Queries**: `cy.get()`, `cy.contains()`, `.find()`, `.first()`.
- **User Actions**: `.click()`, `.type()`, `.trigger('mouseover')`, `.invoke()`.
- **Assertions**: `.should('be.visible')`, `.should('exist')`, `.should('have.attr')`.

*These cases are housed under `cypress/e2e/cura_com_demo.cy.js`*
