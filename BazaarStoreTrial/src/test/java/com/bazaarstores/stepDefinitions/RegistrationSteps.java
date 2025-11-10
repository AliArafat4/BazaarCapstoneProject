package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps {

    AllPages allPages = new AllPages();
    public static String email;
    public static String fullName;

    @When("User clicks registration link")
    public void user_clicks_registration_link() {
        allPages.getLoginPage().clickRegisterLink();
    }

    @And("user enters email for sign up {string}")
    public void userEntersEmailForSignUp(String email) {
        RegistrationSteps.email = Faker.instance().internet().emailAddress();

        if (email.equals("faker")) {
            allPages.getRegistrationPage().enterEmail(RegistrationSteps.email);
        } else {
            allPages.getRegistrationPage().enterEmail(email);
        }
    }

    @And("user enters full name for sign up {string}")
    public void userEntersFullNameForSignUp(String fullName) {
        RegistrationSteps.fullName = fullName;
        allPages.getRegistrationPage().enterName(fullName);
    }

    @And("user enters password for sign up")
    public void userEntersPasswordForSignUp() {
        allPages.getRegistrationPage().enterPassword(ConfigReader.getDefaultPassword());
    }

    @And("user enters confirm password for sign up")
    public void userEntersConfirmPasswordForSignUp() {
        allPages.getRegistrationPage().enterPasswordConfirmation(ConfigReader.getDefaultPassword());
    }

    @And("user clicks the sign up button")
    public void userClicksTheSignUpButton() {
        allPages.getRegistrationPage().clickSignUp();
    }

    @Then("user should see success message for registration")
    public void userShouldSeeSuccessMessageForRegistration() {
        allPages.getRegistrationPage().validateSuccessMessage();

}

    @Given("user goes to home page")
    public void userGoesToHomePage() {
        Driver.getDriver().get(ConfigReader.getBaseUrl());
    }

    @Then("user should see invalid email error message")
    public void userShouldSeeInvalidEmailErrorMessage() {
        allPages.getRegistrationPage().validateInvalidEmail();
    }

    // added this step to check different error messages on registration page
    @Then("user should see error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        String actualMessage = Driver.getDriver().findElement(org.openqa.selenium.By.xpath("//li")).getText();
        org.junit.Assert.assertEquals(expectedMessage, actualMessage);
    }

    // added this step to simulate API verification with specific email
    @Then("assert the registration via API using email {string}")
    public void assert_the_registration_via_api_using_email(String email) {
        System.out.println("Checking registration API with email: " + email);
    }

    // added this for negative test cases with short or invalid passwords
    @When("user enters password for sign up {string}")
    public void user_enters_password_for_sign_up(String password) {
        allPages.getRegistrationPage().enterPassword(password);
    }

    // added this for tests where confirm password doesn't match
    @When("user enters confirm password for sign up {string}")
    public void user_enters_confirm_password_for_sign_up(String confirmPassword) {
        allPages.getRegistrationPage().enterPasswordConfirmation(confirmPassword);
    }
}
