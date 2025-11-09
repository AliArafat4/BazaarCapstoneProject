@US07 @Customer @Regression
Feature: Mark Products as Favorites as a Customer

  Background:
    Given user goes to homepage
    And user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    And user should be logged in successfully

  @US07_TC001
  Scenario: Verify that the heart icon appears on hover and can be marked as favorite
    When user hovers over a product
    Then heart icon should appear on the product
    When user clicks the heart icon
    Then heart icon should turn red indicating the product is marked as favorite


  @US07_TC002
  Scenario: Verify that clicking the heart icon adds product to My Favorites page
    When user hovers over a product
    And user clicks the heart icon
    Then heart icon should turn red indicating the product is marked as favorite
    And product should be added to My Favorites page

  @US07_TC003
  Scenario: Verify that clicking the heart icon again shows error if product is already favorited
    When user hovers over a product
    And user clicks the heart icon
    And user clicks the heart icon again on the same product
    Then error message should be displayed "Product is already in favorites."


  @US07_TC004
  Scenario: Verify that the user can access the “My Favorites” list
    When user hovers over a product
    And user clicks the heart icon
    Then heart icon should turn red indicating the product is marked as favorite
    When user clicks on My Favorites from the header
    Then product should be added to My Favorites page


  @US07_TC005
  Scenario: Verify that the user can remove a product from My Favorites
    When user goes to My Favorites page
    And user removes the product from favorites
    Then product should be removed from My Favorites page

