@Admin
Feature:Admin can view all stores.



  @Positive @KnownIssue @Smoke @Regression
  Scenario: Verify that an Admin can view all stores in the system
    Given user goes to home page
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully
    And the admin clicks on the Stores button
    Then all stores should be displayed in a table
    And the table should have columns: Store Name, Location, Admin Name, Description, and Actions
    And the Actions column should contain two buttons: Edit and Delete
    And assert that stores are retrieved via the API


    @Negative
  Scenario Outline: Verify that only admins can view stores in the system
    Given user goes to home page
    When user enters email "<user>" and password "<password>"
    And user clicks login button
    Then the stores page should not be visible to the user
    And verify that stores are not retrieved via the API by the "<user>"

    Examples:
      | user                 | password        |
      | storemanager@sda.com | Password.12345  |
      | customer@sda.com     | Password.12345  |






