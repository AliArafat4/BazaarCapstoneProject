@AddUser
Feature: add new user

  Background:
    Given user goes to home page
    And user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @TC001 @PositiveAddUser
  Scenario: Successfully add a new user
    When admin navigates to the Users page
    And clicks on Add Users button
    And admin enters Name, valid@email.com, Role, Password, and PasswordConfirmation
    And clicks on Submit button
    Then a success message should appear
    And assert the user addition via API




