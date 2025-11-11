@Regression @DeleteProduct
Feature: Store Manager Delete Product Feature

  Background:
    Given user is in home page
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button
    Then store manager is logged in
    When store manager navigates to Products section

  @Smoke @PositiveDeleteProduct @PositiveDelete
  Scenario: Store Manager deletes a product successfully
    Given product is available in the list with name "sample book22", price "10", stock "10", and sku "00040"
    Then assert the new product via API with name "sample book22" and sku "00040"
    When store manager clicks on Delete Product Button for "sample book22" product
    And delete dialogue shows
    And store manager confirms deletion of Product
    Then store manager should see success message for deleting a product
    And assert the deleted product via API with sku "00040"


  @PositiveDeleteProduct @PositiveDeletionMessage
  Scenario: Store Manager attempts to delete product and get delete dialogue message
    Given product is available in the list with name "sample book23", price "10", stock "10", and sku "00041"
    Then assert the new product via API with name "sample book23" and sku "00041"
    When store manager clicks on Delete Product Button for "sample book23" product
    Then delete dialogue shows with alert message "Are you sure? You won't be able to revert this!"
    #just to delete the product - not part of the actual test
    And store manager confirms deletion of Product
    Then store manager should see success message for deleting a product
    And assert the deleted product via API with sku "00041"

  @PositiveDeleteProduct @PositiveDeleteCancel
  Scenario: Store Manager attempts to delete product and then cancels
    Given product is available in the list with name "sample book24", price "10", stock "10", and sku "00042"
    Then assert the new product via API with name "sample book24" and sku "00042"
    When store manager clicks on Delete Product Button for "sample book24" product
    And store manager cancels deletion of Product
    Then assert the product is not deleted via API with sku "00042"
    #just to delete the product - not part of the actual test
    When store manager clicks on Delete Product Button for "sample book24" product
    And delete dialogue shows
    And store manager confirms deletion of Product
    Then store manager should see success message for deleting a product
    And assert the deleted product via API with sku "00042"

  @Smoke @PositiveDeleteProduct @PositiveDeleteDialogue
  Scenario:Store Manager attempts to delete product and sees an alert
    Given product is available in the list with name "sample book25", price "10", stock "10", and sku "00043"
    Then assert the new product via API with name "sample book25" and sku "00043"
    When store manager clicks on Delete Product Button for "sample book25" product
    Then delete dialogue shows
    #just to delete the product - not part of the actual test
    And store manager confirms deletion of Product
    Then store manager should see success message for deleting a product
    And assert the deleted product via API with sku "00042"