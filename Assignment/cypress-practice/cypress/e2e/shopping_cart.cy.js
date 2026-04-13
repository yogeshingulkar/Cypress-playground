describe('DemoWebShop - Wishlist & Cart Flow', () => {

  const timestamp = Date.now();
  const user = {
    firstName: 'Yogesh',
    lastName: 'Ingulkar',
    email: `testuser_${timestamp}@gmail.com`,
    password: 'Admin@123'
  };

  const dismissNotification = () => {
    cy.get('body').then(($body) => {
      if ($body.find('.bar-notification .close').length) {
        cy.get('.bar-notification .close').click({ force: true });
      }
    });
  };

  before(() => {
    cy.visit('https://demowebshop.tricentis.com/register');
    cy.get('#gender-male').check();
    cy.get('#FirstName').clear().type(user.firstName);
    cy.get('#LastName').clear().type(user.lastName);
    cy.get('#Email').clear().type(user.email);
    cy.get('#Password').clear().type(user.password);
    cy.get('#ConfirmPassword').clear().type(user.password);
    cy.get('#register-button').click();
    cy.url({ timeout: 15000 }).should('include', '/registerresult');
    cy.log('Registration successful for: ' + user.email);
  });

  beforeEach(() => {
    cy.session(
      user.email,
      () => {
        cy.visit('/login');
        cy.get('#Email').clear().type(user.email);
        cy.get('#Password').clear().type(user.password);
        cy.get('input[value="Log in"]').click();
        cy.get('.account', { timeout: 10000 }).should('contain', user.email);
      },
      {
        validate() {
          cy.visit('/');
          cy.get('.account').should('contain', user.email);
        }
      }
    );
  });

  it('Step 1 - Add $5 Virtual Gift Card to Wishlist', () => {
    cy.visit('/5-virtual-gift-card');
    cy.get('#giftcard_1_RecipientName').clear().type('Test Recipient');
    cy.get('#giftcard_1_RecipientEmail').clear().type('recipient@mail.com');
    cy.get('#add-to-wishlist-button-1').click();
    cy.get('.bar-notification', { timeout: 8000 })
      .should('be.visible')
      .and('contain', 'The product has been added to your');
    dismissNotification();
  });

  it('Step 2 - Add $25 Virtual Gift Card to Wishlist', () => {
    cy.visit('/25-virtual-gift-card');
    cy.get('#giftcard_2_RecipientName').clear().type('Second Recipient');
    cy.get('#giftcard_2_RecipientEmail').clear().type('second@mail.com');
    cy.get('#add-to-wishlist-button-2').click();
    cy.get('.bar-notification', { timeout: 8000 })
      .should('be.visible')
      .and('contain', 'The product has been added to your');
    dismissNotification();
  });

  it('Step 3 - Verify Wishlist contains products', () => {
    cy.visit('/wishlist');
    cy.get('form[action="/wishlist"]').within(() => {
      cy.get('table.cart').should('be.visible');
      cy.get('tbody tr').should('have.length.gte', 1);
    });
    cy.get('table.cart tbody tr').each(($row) => {
      cy.log('Wishlist item: ' + $row.find('td.product').text().trim());
    });
  });

  it('Step 4 - Move ALL Wishlist items to Shopping Cart', () => {
    cy.visit('/wishlist');
    cy.get('table.cart', { timeout: 8000 }).should('be.visible');
    cy.get('input[name="addtocart"]').check({ multiple: true });
    cy.get('input[value="Add to cart"]').click();
    cy.visit('/cart');
    cy.get('table.cart', { timeout: 8000 }).should('be.visible');
    cy.get('tbody tr').should('have.length.gte', 1);
  });

  it('Step 5 - Remove ALL items from Shopping Cart', () => {
    cy.visit('/cart');
    cy.get('table.cart', { timeout: 8000 }).should('be.visible');
    cy.get('input[name="removefromcart"]').check({ multiple: true });
    cy.get('input[value="Update shopping cart"]').click();
    cy.get('.order-summary-content', { timeout: 8000 })
      .should('contain', 'Your Shopping Cart is empty!');
  });

  it('Step 6 - Remove ALL items from Wishlist', () => {
    cy.visit('/wishlist');
    cy.get('body').then(($body) => {
      if ($body.find('input[name="removefromcart"]').length > 0) {
        cy.get('input[name="removefromcart"]').check({ multiple: true });
        cy.get('input[name="updatecart"]').click();
        cy.get('.wishlist-content', { timeout: 8000 })
          .should('contain', 'The wishlist is empty!');
      } else {
        cy.get('.wishlist-content').should('contain', 'The wishlist is empty!');
      }
    });
  });

  it('Full Flow - Add to Wishlist -> Add to Cart -> Remove from Cart -> Remove from Wishlist', () => {
    cy.visit('/5-virtual-gift-card');
    cy.get('#giftcard_1_RecipientName').clear().type('Test Recipient');
    cy.get('#giftcard_1_RecipientEmail').clear().type('recipient@mail.com');
    cy.get('#add-to-wishlist-button-1').click();
    cy.get('.bar-notification', { timeout: 8000 })
      .should('be.visible')
      .and('contain', 'The product has been added to your');
    dismissNotification();

    cy.visit('/25-virtual-gift-card');
    cy.get('#giftcard_2_RecipientName').clear().type('Second Recipient');
    cy.get('#giftcard_2_RecipientEmail').clear().type('second@mail.com');
    cy.get('#add-to-wishlist-button-2').click();
    cy.get('.bar-notification', { timeout: 8000 })
      .should('be.visible')
      .and('contain', 'The product has been added to your');
    dismissNotification();

    cy.visit('/wishlist');
    cy.get('table.cart', { timeout: 8000 }).should('be.visible');
    cy.get('tbody tr').should('have.length.gte', 1);

    cy.get('input[name="addtocart"]').check({ multiple: true });
    cy.get('input[value="Add to cart"]').click();

    cy.visit('/cart');
    cy.get('table.cart', { timeout: 8000 }).should('be.visible');
    cy.get('tbody tr').should('have.length.gte', 1);

    cy.get('input[name="removefromcart"]').check({ multiple: true });
    cy.get('input[value="Update shopping cart"]').click();
    cy.get('.order-summary-content', { timeout: 8000 })
      .should('contain', 'Your Shopping Cart is empty!');

    cy.visit('/wishlist');
    cy.get('body').then(($body) => {
      if ($body.find('input[name="removefromcart"]').length > 0) {
        cy.get('input[name="removefromcart"]').check({ multiple: true });
        cy.get('input[name="updatecart"]').click();
        cy.get('.wishlist-content', { timeout: 8000 })
          .should('contain', 'The wishlist is empty!');
      } else {
        cy.log('Wishlist already empty');
      }
    });
  });

});
