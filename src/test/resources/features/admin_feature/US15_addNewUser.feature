@AddUser @TestZahra
Feature: add new user

  Background:
    Given user goes to home page
    And user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @US15TC01 @Positive
  Scenario: Successfully add a new user
    When admin navigates to the Users page
    And clicks on Add Users button
    And admin enters Name, valid@email.com, Role, Password, and PasswordConfirmation
    And clicks on Submit button
    Then a success message should appear
    And assert the user addition via API



  @Negative @testAdd
  Scenario Outline: Add user with invalid data
    When admin navigates to the Users page
    And clicks on Add Users button
    And capture current users count
    And enters invalid user data:
      | Name   | Email          | Role          | Password   | ConfirmPassword   |
      | <Name> | <Email>        | <Role>        | <Password> | <ConfirmPassword> |
    And clicks on Submit button
    Then an error message "<ErrorMessage>" should appear
    And assert the invalid user addition via API

    Examples:
      | Name  | Email                   | Role          | Password       | ConfirmPassword | ErrorMessage                                   |
      |       | missingField@sda.com    | Store Manager | Password.12345 | Password.12345  | The name field is required.                    |
      | Zahra |                         | Admin         | Password.12345 | Password.12345  | The email field is required.                   |
      | Sara  | missingField@sda.com    |               | Password.12345 | Password.12345  | The role field is required.                    |
      | Zahra | missingField@sda.com    | Store Manager |                | Password.12345  | The password field is required.                |
      | Mary  | missingField@sda.com    | Store Manager | Password.12345 |                 | The password field confirmation does not match.|
      | John  | mismatchPassword@sda.com| Customer      | Password.12345 | Password.123443 | The password field confirmation does not match.|
      | Jack  | customer@sda.com        | Customer      | Password.12345 | Password.12345  | The email has already been taken.              |
      |       |                         |               |                |                 | The name field is required. ; The email field is required. ; The role field is required. ; The password field is required. |
      | Zahra | adminsda.com            | Store Manager | Password.12345 | Password.12345  | Please include an '@' in the email address.    |
      | Zahra | admin@@sda.com          | Store Manager | Password.12345 | Password.12345  | A part following '@' should not contain the symbol '@'.    |
      | Zahra | missingDomain@sda       | Store Manager | Password.12345 | Password.12345  | missing domain extension (.com)                |


  @US15TC13 @Negative @testAdd @KnownIssue
  Scenario: Add user with invalid email without domain extension
    When admin navigates to the Users page
    And clicks on Add Users button
    And capture current users count
    And admin enters Name, email@example, Role, Password, and PasswordConfirmation
    And clicks on Submit button
    Then an error message should appear to prevent user addition
    And assert the invalid email account was not created addition via API



  @US15TC11 @Positive
    Scenario: Verify ADD USERS button is visible and clickable
      When admin navigates to the Users page
      Then admin should be able to see the ADD USERS button
      When clicks on Add Users button
      Then admin should navigates to the Add Users page

  @US15TC14 @Positive
  Scenario: Verify Submit button in Add Users page is visible and clickable
    When admin navigates to the Users page
    Then admin should be able to see the Submit button
    When clicks on Add Users button
    Then admin should navigates to the Add Users page
    And admin enters Name, valid@email.com, Role, Password, and PasswordConfirmation
    And clicks on Submit button
    Then a success message should appear
    And assert the user addition via API



  @US15TC10 @Positive
  Scenario: Added user is showing in the users list successfully
    When admin navigates to the Users page
    And clicks on Add Users button
    And admin enters Name, valid@email.com, Role, Password, and PasswordConfirmation
    And clicks on Submit button
    Then admin should see the new user in the users list
    And assert the user addition via API
