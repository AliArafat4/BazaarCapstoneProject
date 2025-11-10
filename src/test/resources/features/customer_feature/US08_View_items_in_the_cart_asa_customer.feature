@US08 @Customer @Regression
Feature: US08 - View items in the cart as a customer

  Background:
    Given user goes to homepage
    And user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    And user should be logged in successfully

  @US08_TC001
  Scenario: Verify that hovering over the cart icon displays the mini cart with all items, prices, and total
    When User hovers over the cart icon
    Then The mini cart should appear with all items, prices, and total cost
    And The View Cart button should be visible

  @US08_TC002
  Scenario: Verify that removing an item decreases the number of products in the mini cart
    When User hovers over the cart icon
    And User removes an item from the mini cart

  @US08_TC003
  Scenario: Verify that clicking View Cart button navigates to the full cart page
    When User hovers over the cart icon
    And User clicks the View Cart button
    Then System navigates to the full cart page with all items displayed

  @US08_TC004
  Scenario: Verify that removing an item updates total price
    When User hovers over the cart icon
    And Item should be removed and total price updated



