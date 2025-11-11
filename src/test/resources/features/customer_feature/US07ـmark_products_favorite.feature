@US07 @Customer @Regression
Feature: Mark Products as Favorites as a Customer

  Background:
    Given user goes to homepage
    And user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    And user should be logged in successfully

  @US07_TC001 @Smoke
  Scenario: Verify that the heart icon appears on hover and can be marked as favorite
    When user hovers and clicks heart icon on product 1
    Then heart icon should appear on product 1
    Then heart icon should turn red for product 1
    And product should be added to My Favorites page

  @US07_TC002
  Scenario: Verify that clicking the heart icon again shows error if product is already favorited
    When user hovers and clicks heart icon on product 1
    And user clicks the heart icon again on the same product
    Then error message should be displayed "Product is already in favorites."

  @US07_TC003 @Smoke
  Scenario: Verify that the user can favorite a second product
    When user hovers and clicks heart icon on product 2
    Then heart icon should turn red for product 2
    And product should be added to My Favorites page

  @US07_TC004
  Scenario: Verify that the user can remove a product from My Favorites
    When user goes to My Favorites page
    And user removes the product from favorites at position 1
    Then product should be removed from My Favorites page
