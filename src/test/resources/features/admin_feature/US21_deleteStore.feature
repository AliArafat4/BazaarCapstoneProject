@Admin @Regression
Feature: Delete a store as an Admin


  Background:
    Given user goes to home page
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully
    When the admin clicks on the Stores button
    And clicks on the Add Store button
    And the admin adds a new store with fake data
    And the admin clicks on the Submit button


  Scenario: Verify that the Admin can delete a store after confirming the action
    When the admin clicks the Delete button of the store
    Then a confirmation dialog should appear
    When the admin clicks the Confirm button
    Then a "Store deleted" message should be displayed
    Then the store should be deleted
    And verify the deletion via the API


  Scenario: Verify that canceling the delete action keeps the store
    When the admin clicks the Delete button of the store
    Then a confirmation dialog should appear
    When the admin clicks the Cancel button
    Then the store should not be deleted
    And verify the store still exists via the API