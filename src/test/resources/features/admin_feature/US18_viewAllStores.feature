@Admin
Feature:Admin can view all stores.


  Background:
    Given user goes to home page
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully


  @Positive @KnownIssue @Smoke @Regression
  Scenario:
    When the admin clicks on the Stores button
    Then all stores should be displayed in a table
    And the table should have columns: Store Name, Location, Admin Name, Description, and Actions
    And the Actions column should contain two buttons: Edit and Delete
    And assert that stores are retrieved via the API

