@Customer @AddToCart
    Feature: Add Products To Cart As a Customer

      Background:
        Given user goes to home page
        And user enters email "customer@sda.com" and password "Password.12345"
        And user clicks login button
        And user should be logged in successfully





        @US06TC01
        Scenario: Click on "Add to Cart" button for a product
          When customer clicks on "Add to Cart" button for a product
          Then the product should be added to the cart