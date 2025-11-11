@ViewUsers @Admin @Regression
  Feature: View All Users in the System As an Admin
    Background:
      Given user goes to home page
      And user enters email "admin@sda.com" and password "Password.12345"
      And user clicks login button
      And admin should be logged in successfully

    @US14TC01 @Smoke
    Scenario: Admin can access 'users' from menu
      When admin clicks users menu
      Then admin should be redirected to Users page successfully

    @US14TC02
    Scenario: Admin can see all users in Users menu
      When admin clicks users menu
      Then admin should see all users listed in Users menu


    @US14TC03
    Scenario: Admin can see names of all users in Users menu
      When admin clicks users menu
      Then admin should see names of all users listed in Users menu

    @US14TC04
    Scenario: Admin can see emails of all users in Users menu
      When admin clicks users menu
      Then admin should see emails of all users listed in Users menu

    @US14TC05
    Scenario: Admin can see emails of all users in Users menu
      When admin clicks users menu
      Then admin should see users action in front of Users

    @US14TC06
    Scenario: Admin can type on search bar successfully
      When admin clicks users menu
      And clicks on search bar and types "Only Test"
      And admin clicks on search button
      Then text is typed successfully in search bar

    @US14TC07
    Scenario: Admin can search users by email
      When admin clicks users menu
      And clicks on search bar and types "customer@sda.com"
      And admin clicks on search button
      Then admin should see that user record is displayed successfully

    @US14TC08
    Scenario: Admin can message "No users found" when searching for non-existing user
      When admin clicks users menu
      And clicks on search bar and types "not.found@test.com"
      And admin clicks on search button
      Then admin should error message No users found is displayed successfully

    @US14TC09 @KnownIssue
    Scenario: Verify search bar is case sensitive
      When admin clicks users menu
      And clicks on search bar and types "CUstomer@sda.com"
      And admin clicks on search button
      Then admin should see that user record is displayed successfully regarding of email case
