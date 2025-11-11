@Admin @AddStore @Regression
Feature: Add a new store as an Admin.
  1- Admin can add a new store with valid inputs.
  2- Missing fields show an error. (Name, Description, Location, Admins)
  3- Successfully added stores appear in the stores list.


  Background:
    Given user goes to home page
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @Positive @Smoke
  Scenario Outline: Verify that an Admin can add a new store with valid inputs
    When the admin clicks on the Stores button
    And clicks on the Add Store button
    And the admin adds a new store with details: "<Name>", "<Location>", "<Admin Name>", "<Description>"
    And the admin clicks on the Submit button
    Then the system displays a success message: Store created successfully.
    And the new store should appear in the store list
    And verify that the store is created via API


    Examples:
      | Name       | Location | Admin Name    | Description                           |
      | Book Haven | Riyadh   | Store Manager | A bookstore for educational materials |



  @Negative
  Scenario Outline: Verify that missing required fields show error messages
    When the admin clicks on the Stores button
    And clicks on the Add Store button
    And the admin adds a new store with details: "<Name>", "<Location>", "<Admin Name>", "<Description>"
    And the admin clicks on the Submit button
    Then the system should display a validation message for the missing fields
    Then the store should not be added to the stores table
    And verify that the store is not created via API

    Examples:
      | Name       | Location | Admin Name    | Description                              |
      |             | Riyadh   | Store Manager | A bookstore for educational materials    |
      | StoreA      |          | Store Manager | A bookstore for educational materials    |
      | StoreB      | Riyadh   |               | A bookstore for educational materials    |
      | StoreC      | Riyadh   | Store Manager |                                          |
      |             |          |               |                                          |


    @Negative
    Scenario Outline: Verify that the system allows only text input in the Name, Location, and Description fields
      When the admin clicks on the Stores button
      And clicks on the Add Store button
      And the admin adds a new store with details: "<Name>", "<Location>", "<Admin Name>", "<Description>"
      And the admin clicks on the Submit button
      Then the system should display a validation message: Only letters are allowed.
      Then the store with invalid data should not be added to the stores table
      And verify that the store with invalid data is not created via API

      Examples:
        | Name       | Location | Admin Name    | Description                              |
        | 12345      | Riyadh   | Store Manager | A bookstore for educational materials    |
        | StoreQA    | 12345    | Store Manager | A bookstore for educational materials    |
        | StoreE     | Riyadh   | Store Manager | @12345                                   |
        | @12345     | 12345    | Store Manager | @@@12345                                 |




