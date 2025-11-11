@DeleteUser @Regression
Feature: delete a from the system

  Background:
    Given user goes to home page
    And user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @US17TC01 @Positive @KnownIssue @Smoke
  Scenario: Admin can delete a user successfully
    Given the intended user exists
    When admin locate the intended user by email
    And click on delete button
    And click on confirm the delete button
    Then a success message should appear to confirm deletion
    And confirm deletion success via API

  @US17TC02 @Positive
  Scenario: Admin can delete a user successfully
    Given the intended user exists
    When admin locate the intended user by email
    And click on delete button
    And click on cancel button to cancel the deletion
    Then confirm deletion failure via API

    @US17TC03 @Positive @Smoke
      Scenario: Verify delete button is visible and clickable
      When admin navigates to the Users page
      Then admin should be able to see the delete buttons
      When clicks on delete button for any user
      Then admin should see the delete confirmation alert

      @US17TC04 @Negative @KnownIssue
        Scenario: Attempt to delete currently logged in admin
        When admin navigates to the Users page
        And locate the admin user by email
        And click on delete button to delete admin and confirm
        Then an error message should appear to prevent admin deletion
        And confirm admin deletion failure via API

        @US17TC05 @Negative @KnownIssue
          Scenario: Attempting to login with deleted account should fail
          Given the intended user exists
          When admin locate the intended user by email
          And click on delete button
          And click on confirm the delete button
          And admin logout
          And enter the credentials for deleted account
          And user clicks login button
          Then user should see error message and fail to login
