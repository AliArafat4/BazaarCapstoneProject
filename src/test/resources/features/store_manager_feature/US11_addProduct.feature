@Regression @AddProduct
Feature:
  Store Manager Add Product Feature

  Background:
    Given user is in home page
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button

  @Smoke @PositiveAddProduct
  Scenario: Store Manager adds a new product with all information successfully
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    And store manager enters product name "Sample Book"
    And store manager sets product price "10.00"
    And store manager sets product stock "100"
    And store manager sets product SKU "00001"
    And store manager sets product category "Books"
    And store manager sets product manufacturer "Books Company inc."
    And store manager uploads product image "book.png"
    And store manager sets product discount "1.00"
    And store manager enters product description "This is a sample book."
    And store manager clicks on Submit button
    Then store manager should see success message for adding product
    And store manager get redirected to Products page
    And store manager should see the new product in the products list with name "Sample Book"
    And assert the new product via API with name "Sample Book"

#    @NegativeAddProduct
#    Scenario: Store Manager fails to add a new product due to missing name
#        Given store manager is logged in
#        When store manager navigates to "Products" section
#        And store manager clicks on "Add New Product" button
#        And store manager leaves product name empty
#        And store manager enters product description "This is a sample product."
#        And store manager sets product price "29.99"
#        And store manager uploads product image "sample_image.jpg"
#        And store manager clicks on "Save Product" button
#        Then store manager should see error message for missing product name
#        And assert the new product via API does not exist with description "This is a sample product."