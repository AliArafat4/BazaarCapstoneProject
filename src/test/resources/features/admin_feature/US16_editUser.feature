@EditUser @Regression
Feature: Edit user's data

  Background:
    Given user goes to home page
    And user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @US16TC01 @Positive
  Scenario: Admin can update user's Name successfully
    Given the intended user exists
    When admin locate the intended user by email
    And click on edit button
    And update the name
    And clicks on Submit button to confirm update
    Then a success message should appear to confirm update
    And assert the name update via API

  @US16TC02 @Positive
  Scenario: Admin can update user's Role successfully
    Given the intended user exists
    When admin locate the intended user by email
    And click on edit button
    And update the role
    And clicks on Submit button to confirm update
    Then a success message should appear to confirm update
    And assert the role update via API

  @US16TC03 @Positive
  Scenario: Admin can update user's Email successfully
    Given the intended user exists
    When admin locate the intended user by email
    And click on edit button
    And update the email
    And clicks on Submit button to confirm update
    Then a success message should appear to confirm update
    And assert the email update via API

  @InvalidEmailUpdate @Negative @zz
  Scenario Outline: Update user's Email to invalid format
    Given the intended user exists
    When admin locate the intended user by email
    And click on edit button
    When update the users to invalid email "<email>"
    And clicks on Submit button to confirm update
    Then an error message should appear to confirm update failure
    And assert the email update failed via API

    Examples:
      | email                           |
      | testingUpdateEmailexample.com   |
      | testingUpdateEmail@@example.com |


  @US16TC10 @InvalidEmailUpdate @Negative @KnownIssue
  Scenario: Update user's Email to invalid format without domain extension
    Given the intended user exists
    When admin locate the intended user by email
    And click on edit button
    When update the users email to one without domain extension
    And clicks on Submit button to confirm update
    Then an error message should appear to confirm update without domain extension failed
    And assert the email update without domain extension failed via API


  @US16TC06 @Positive
  Scenario: Verify edit button is visible and clickable
    When admin navigates to the Users page
    Then admin should be able to see the edit buttons
    When clicks on edit button for any user
    Then admin should navigates to the Edit Users page

    @US16TC04 @Positive
      Scenario: Admin can update user's password successfully
      Given the intended user exists
      When admin locate the intended user by email
      And click on edit button
      And update the password
      And rewrite the password in confirmation field
      And clicks on Submit button to confirm update
      Then a success message should appear to confirm update
      And assert the password update via API


  @US16TC05 @Negative @KnownIssue
  Scenario: Admin attempt to update user's password with mismatched confirmation
    Given the intended user exists
    When admin locate the intended user by email
    And click on edit button
    And update the password
    And write a different password in confirmation field
    And clicks on Submit button to confirm update
    Then an error message should appear to alert the admin to enter a valid confirmation
    And assert the password did not update via API

  @US16TC07 @Positive
  Scenario: Admin navigating to the last page doesn't save changed data
    Given the intended user exists
    When admin locate the intended user by email
    And click on edit button
    And update the name
    And navigate to the last page
    Then verify that the API does not confirm or store the new changes


