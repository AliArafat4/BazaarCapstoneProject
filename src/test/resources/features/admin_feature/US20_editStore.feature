@Admin @EditStore
Feature: Edit existing store details as an Admin.


  Background:
    Given user goes to home page
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

@Positive
  Scenario Outline: Verify that the Admin can successfully edit store details with valid inputs.
    When the admin clicks on the Stores button
    And clicks on the Edit Store button
    And the admin edits an existing store with details: "<Name>", "<Location>", "<Admin Name>", "<Description>"
    And the admin clicks on the Submit button
    Then the system should display a success message: Store updated successfully.
    And the updated store should appear in the store list
    And verify that the store is updated via the API

    Examples:
      | Name       | Location | Admin Name      | Description        |
      | My Store 1 | Jeddah   | Store Manager   | Updated bookstore  |




  @Negative
  Scenario Outline: Verify that the system shows an error for missing inputs during edit
    When the admin clicks on the Stores button
    And clicks on the Edit Store button
    And the admin edits an existing store leaving the "<Field>" field empty
    And the admin clicks on the Submit button
    Then the system should display a validation message for the missing fields during edit
    And verify that the store is not updated via API

    Examples:
      | Field       |
      | Name        |
      | Location    |
      | Admin       |
      | Description |
      | All fields  |


  @Positive
  Scenario Outline: Verify that updating one field updates successfully
    When the admin clicks on the Stores button
    And clicks on the Edit Store button
    And the admin edits an existing store, updating the "<Field>" field with value "<Value>"
    And the admin clicks on the Submit button
    Then the system should display a success message: Store updated successfully.
    And verify that the field is updated via the API

    Examples:
      | Field       | Value           |
      | Name        | Updated StoreA  |
      | Location    | Makkah          |
      | Admin       | Store Manager   |
      | Description | New collection  |


