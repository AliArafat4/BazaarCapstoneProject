@EditUser
Feature: Edit user's data

  Background:
    Given user goes to home page
    And user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @US16TC01 @Positive
  Scenario: Admin can update user's Name successfully
    Given a user exists for editing
    When admin locate the intended user by email
    And click on edit button
    And update the name
    And clicks on Submit button to confirm update
    Then a success message should appear to confirm update

  @US16TC02 @Positive
  Scenario: Admin can update user's Role successfully
    Given a user exists for editing
    When admin locate the intended user by email
    And click on edit button
    And update the role
    And clicks on Submit button to confirm update
    Then a success message should appear to confirm update

  @US16TC03 @Positive
  Scenario: Admin can update user's Email successfully
    Given a user exists for editing
    When admin locate the intended user by email
    And click on edit button
    And update the email
    And clicks on Submit button to confirm update
    Then a success message should appear to confirm update

  @InvalidEmailUpdate @Negative
  Scenario Outline: Update user's Email to invalid format
    Given a user exists for editing
    When admin locate the intended user by email
    And click on edit button
    When update the users to invalid email "<email>"
    And clicks on Submit button to confirm update
    Then an error message should appear to confirm update failure

    Examples:
      | email                           |
      | testingUpdateEmailexample.com   |
      | testingUpdateEmail@@example.com |
      | testingUpdateEmail@example      |

  @US16TC06 @Positive
  Scenario: Verify edit button is visible and clickable
    When admin navigates to the Users page
    Then admin should be able to see the edit buttons
    When clicks on edit button for any user
    Then admin should navigates to the Edit Users page
