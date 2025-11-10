@Registration
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
    Then user should see success message for registration
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

