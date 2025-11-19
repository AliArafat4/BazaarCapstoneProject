package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps {

    AllPages pages = new AllPages();
    public static String email;
    public static String fullName;

    @When("User clicks registration link")
    public void user_clicks_registration_link() {

        pages.getLoginPage().clickRegisterLink();
    }


    @And("user enters email for sign up {string}")
    public void userEntersEmailForSignUp(String email) {
        RegistrationSteps.email = Faker.instance().internet().emailAddress();

        if(email.equals("faker")){
            pages.getRegistrationPage().enterEmail(RegistrationSteps.email);
        }else{
            pages.getRegistrationPage().enterEmail(email);
        }


    }

    @And("user enters full name for sign up {string}")
    public void userEntersFullNameForSignUp(String fullName) {
        RegistrationSteps.fullName = fullName;
        pages.getRegistrationPage().enterName(fullName);

    }

    @And("user enters password for sign up")
    public void userEntersPasswordForSignUp() {
        pages.getRegistrationPage().enterPassword(ConfigReader.getDefaultPassword());

    }

    @And("user enters confirm password for sign up")
    public void userEntersConfirmPasswordForSignUp() {
        pages.getRegistrationPage().enterPasswordConfirmation(ConfigReader.getDefaultPassword());

    }

    @And("user clicks the sign up button")
    public void userClicksTheSignUpButton() {
        pages.getRegistrationPage().clickSignUp();

    }

    @Then("user should see success message for registration")
    public void userShouldSeeSuccessMessageForRegistration() {
        //this is a bug!! It is already reported!!
        assert false;

    }


    @Given("user goes to home page")
    public void userGoesToHomePage() {
        Driver.getDriver().get(ConfigReader.getBaseUrl());
    }

    @Then("user should see invalid email error message")
    public void userShouldSeeInvalidEmailErrorMessage() {

        pages.getRegistrationPage().validateInvalidEmail();
    }
    // Added missing step definitions for password and error message scenarios
// lina

    @When("user enters password for sign up {string}")
    public void user_enters_password_for_sign_up(String password) {
        pages.getRegistrationPage().enterPassword(password);
    }

    @When("user enters confirm password for sign up {string}")
    public void user_enters_confirm_password_for_sign_up(String confirmPassword) {
        pages.getRegistrationPage().enterPasswordConfirmation(confirmPassword);
    }

    @Then("user should see error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        String actualMessage = pages.getRegistrationPage().getErrorMessageText();
        org.junit.Assert.assertEquals("Error message did not match", expectedMessage, actualMessage);
    }

}
