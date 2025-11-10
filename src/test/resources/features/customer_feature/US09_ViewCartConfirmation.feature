@US09 @Customer @Regression
Feature: US09 - Confirm cart contents as a customer
  Background:
    Given user goes to homepage
    And user enters email "customer@sda.com" and password "Password.12345"
    And user clicks login button
    And user should be logged in successfully

  @US09_TC001
  Scenario: Verify that the customer can confirm the cart contents
    When User hovers over the cart icon
    And User clicks the View Cart button
    And User clicks the Confirm Cart button
    Then A success message should appear with text "Success! Your order has been received successfully." and an OK button

  @US09_TC002
  Scenario: Verify that “View Cart” button is hidden when the cart is empty
    Given User has not added any product to the cart
    When User hovers over the cart icon
    Then The View Cart button should not be visible