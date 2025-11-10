@Registration
Feature: Registration Feature

  @HappyPathRegistration
  Scenario: US01_TC001 - Registration Happy Path
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "valid@email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sign up button
    Then user should see success message for registration
    And assert the registration via API using email "valid@email.com"


  @EmptyField
  Scenario: US01_TC002 - Registration with empty name field
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "faker"
    And user enters full name for sign up ""
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sign up button
    Then user should see error message "The name field is required."


  @NegativeRegistration
  Scenario: US01_TC003 - Registration with invalid email
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "invalid_email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sign up button
    Then user should see invalid email error message
    And assert the registration via API using email "invalid_email.com"


  @InvalidName
  Scenario: US01_TC004 - Registration with invalid name (contains symbols or numbers)
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "faker"
    And user enters full name for sign up "John@123"
    And user enters password for sign up
    And user enters confirm password for sign up
    And user clicks the sign up button
    Then user should see error message "The name field must contain only letters."


  @ShortPassword
  Scenario: US01_TC005 - Registration with short password
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "faker"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up "123"
    And user enters confirm password for sign up "123"
    And user clicks the sign up button
    Then user should see error message "The password field must be at least 6 characters."


  @MismatchedPassword
  Scenario: US01_TC006 - Registration with mismatched confirm password
    Given user goes to home page
    When User clicks registration link
    And user enters email for sign up "testuser1@email.com"
    And user enters full name for sign up "John Doe"
    And user enters password for sign up "test123"
    And user enters confirm password for sign up "test456"
    And user clicks the sign up button
    Then user should see error message "The password field confirmation does not match."
