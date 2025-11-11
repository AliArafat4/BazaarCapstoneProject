@Customer @ProductsDetails @Regression
  Feature: View Products Details As a Customer

    Background:
      Given user goes to home page
      And user enters email "customer@sda.com" and password "Password.12345"
      And user clicks login button
      And user should be logged in successfully

    @US05TC01 @KnownIssue
    Scenario: User clicks on a product to open details page
      When customer clicks on a product to open details page
      Then customer should see product details page


    @US05TC02 @KnownIssue
    Scenario: User can see product name on details page
      When customer clicks on a product to open details page
      Then customer can see product name on details page

    @US05TC03 @KnownIssue
    Scenario: User can see product price on details page
      When customer clicks on a product to open details page
      Then customer can see product price on details page

    @US05TC04 @KnownIssue
    Scenario: User can see product description on details page
      When customer clicks on a product to open details page
      Then customer can see product description on details page

    @US05TC05 @KnownIssue
    Scenario: User can see product image on details page
      When customer clicks on a product to open details page
      Then customer can see product image on details page

    @US05TC06 @KnownIssue
    Scenario: User can see product details for the product selected
      When customer clicks on a product to open details page
      Then customer can see product details for the product selected


