@BrowseProducts @Customer @Regression
  Feature: Browse Products As a Customer

    Background:
      Given user goes to home page
      And user enters email "customer@sda.com" and password "Password.12345"
      And user clicks login button
      And user should be logged in successfully

  @US04TC01 @Smoke
  Scenario: Customer Can See Products
    When customer can see products


  @US04TC02
   Scenario: Customer can see products under three seconds
    When Customer can see products under three seconds

  @US04TC03
  Scenario: Customer Can See Products Names
    When customer can see products names


  @US04TC04
  Scenario: Customer Can See Products Prices
    When customer can see products Prices

    @US04TC05
    Scenario: Customer Can See Products Descriptions
      When customer can see products Descriptions

  @US04TC06
  Scenario: Customer Can See Products Images
    When customer can see products Images
