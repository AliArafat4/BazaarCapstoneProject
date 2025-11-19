@Logout @Regression
Feature: Logout Functionality
  Background:
    Given user is logged in and dashboard page is open
  @US03_TC001
  Scenario: Verify that the user can click "Log out" from the dashboard
    When user clicks the user icon on the top right
    And user selects "Log Out" from the dropdown
    Then system should log the user out

  @US03_TC002
  Scenario: Verify that logging out redirects the user to the Login page
    When user clicks the user icon on the top right
    And user selects "Log Out" from the dropdown
    Then system should log the user out
    And user should be redirected to the Login Page

  @US03_TC003 @Smoke
  Scenario: Verify that the session is securely terminated after logout
    When user clicks the user icon on the top right
    And user selects "Log Out" from the dropdown
    Then system should log the user out
    And user should be redirected to the Login Page
    When user clicks browser back button
    Then system should keep the user on the Login Page