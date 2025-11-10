@US010 @StoreManager @Regression
Feature: View the catalog of all products as a Store Manager

  Background:
    Given user is in home page
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button

  @US010_TC001
  Scenario: Verify that the store manager can view the Products page from the sidebar
#    Given User is logged in as Store Manager
    When User clicks on "Products" from the sidebar
    Then Products page loads showing a list of products

  @US010_TC002
  Scenario: Verify that all product columns are visible
    Given User is on Products page
    Then All columns Name, Price, Stock, Category, Image, Action are visible

  @US010_TC003
  Scenario: Verify that each product entry displays correct details
    Given User is on Products page
    Then Each product entry displays correct details
