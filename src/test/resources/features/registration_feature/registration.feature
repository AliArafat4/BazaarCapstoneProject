@Registration @Regression
Feature: Registration Feature

@HappyPathRegistration
  Scenario: Registration Happy Path
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "valid@email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sign up button
    And assert the registration via API

  @NegativeRegistration
  Scenario: Registration Negative
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "invalid_email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sign up button
    Then user should see invalid email error message
    And assert the registration via API using email "invalid_email.com"

  @EmptyField
  Scenario: Registration with empty name field
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "faker@email.com"
    And user enters full name for sign up ""
    And user enters password for sign up "test123"
    And user enters confirm password for sign up "test123"
    And user clicks the sign up button
    Then user should see error message "The name field is required."

  @InvalidName
  Scenario: Registration with invalid name (contains symbols or numbers)
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "faker@email.com"
    And user enters full name for sign up "John@123"
    And user enters password for sign up "test123"
    And user enters confirm password for sign up "test123"
    And user clicks the sign up button


  @ShortPassword
  Scenario: Registration with short password
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "faker@email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up "123"
    And user enters confirm password for sign up "123"
    And user clicks the sign up button
    Then user should see error message "The password field must be at least 6 characters."

  @MismatchedPassword
  Scenario: Registration with mismatched confirm password
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "testuser1@email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up "test123"
    And user enters confirm password for sign up "test456"
    And user clicks the sign up button
    Then user should see error message "The password field confirmation does not match."


