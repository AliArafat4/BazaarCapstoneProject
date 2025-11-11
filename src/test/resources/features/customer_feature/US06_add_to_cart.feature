@Customer @AddToCart @Regression
    Feature: Add Products To Cart As a Customer

      Background:
        Given user goes to home page
        And user enters email "customer@sda.com" and password "Password.12345"
        And user clicks login button
        And user should be logged in successfully




      @US06TC01
      Scenario: Cart count initially zero for customer
        Then cart count should be zero for the customer at the start

      @US06TC02
      Scenario: Cart count increased by one after adding product to cart
        When customer clicks on Add to Cart button for a product
        Then the cart count should increase by one

      @US06TC03 @Smoke
      Scenario: Click on "Add to Cart" button for a product
        When customer clicks on Add to Cart button for a product
        Then the product should be added to the cart


      @US06TC04
        Scenario: Customer can add different products to the cart
          When customer adds multiple different products to the cart
          Then cart count must be increased by the number of products added

      @US06TC05
        Scenario: Customer can add product multiple times to the cart
          When customer adds the same product multiple times to the cart
          Then cart count must be increased by the number of that product additions




