package com.bazaarstores.stepDefinitions.admin_steps;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.ApiHelper;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import com.bazaarstores.pages.AllPages;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddNewUserSteps extends BasePage {

    AllPages pages = new AllPages();
    public static String attemptedName;
    public static String attemptedEmail;


    @When("admin navigates to the Users page")
    public void admin_navigates_to_the_users_page() {
        pages.getAdminUsersPage().goToUsersPage();
    }

    @When("clicks on Add Users button")
    public void clicks_on_add_users_button() {

        pages.getAdminUsersPage().clickAddUsers();
    }


    // Positive Test
    @And("admin enters Name, valid@email.com, Role, Password, and PasswordConfirmation")
    public void adminEntersNameValidEmailComRolePasswordAndPasswordConfirmation() {
        Faker faker = new Faker();
        attemptedName = faker.name().fullName();
        attemptedEmail = faker.internet().emailAddress();
        String role = "Store Manager";
        String password="Password.12345";
        String ConfirmPassword="Password.12345";

        pages.getAddUserPage().fillUserData(attemptedName, attemptedEmail,role,password,ConfirmPassword);
    }

    // Negative Tests
    @And("enters invalid user data:")
    public void entersInvalidUserData(DataTable table) {

        Map<String, String> user = table.asMaps(String.class, String.class).get(0);

        // coerce nulls to empty string (trim for safety)
        String name = user.get("Name") == null ? "" : user.get("Name").trim();
        String email = user.get("Email") == null ? "" : user.get("Email").trim();
        String role = user.get("Role") == null ? "" : user.get("Role").trim();
        String password = user.get("Password") == null ? "" : user.get("Password").trim();
        String confirmPassword = user.get("ConfirmPassword") == null ? "" : user.get("ConfirmPassword").trim();

        attemptedName = name.isEmpty() ? null : name;
        AddNewUserSteps.attemptedEmail = email.isEmpty() ? null : email;

        pages.getAddUserPage().fillUserData(name,email,role,password,confirmPassword);

        if (!role.isEmpty()) {
            pages.getAddUserPage().selectRole(role);
        }

    }

    @And("capture current users count")
    public void captureCurrentUsersCount() {
        ApiHelper.captureUsersCountBeforeAdd();
    }

    @When("clicks on Submit button")
    public void clicks_on_button() {

        pages.getAddUserPage().clickSubmit();
    }

    @Then("a success message should appear")
    public void a_success_message_should_appear() {
        String actualMessage = pages.getAddUserPage().getSuccessMessage();
        Assert.assertEquals(actualMessage, "User created successfully!");
    }

    @Then("an error message {string} should appear")
    public void anErrorMessageShouldAppear(String expectedErrorMessage) {

        List<String> actualMessages = pages.getAddUserPage().getAllValidationMessages();

        // Split the expected messages by ";" (for multiple expected messages)
        List<String> expectedMessages = Arrays.stream(expectedErrorMessage.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        for (String expected : expectedMessages) {
            boolean found = actualMessages.stream().anyMatch(actual -> actual.contains(expected));
            assertTrue("Expected message not found: " + expected + "\nActual: " + actualMessages, found);
        }
    }


    @Then("admin should be able to see the ADD USERS button")
    public void adminShouldBeAbleToSeeTheADDUSERSButton() {
       assertTrue(pages.getAdminUsersPage().addUsersIsVisible());
    }

    @Then("admin should navigates to the Add Users page")
    public void adminShouldNavigatesToTheAddUsersPage() {
        assertTrue(pages.getAddUserPage().isOnAddUserPage());

    }

    @Then("admin should be able to see the Submit button")
    public void adminShouldBeAbleToSeeTheSubmitButton() {
      assertTrue(pages.getAddUserPage().isSubmitButtonVisible());
    }

    @Then("admin should see the new user in the users list")
    public void adminShouldSeeTheNewUserInTheUsersList() {
            Assert.assertTrue(pages.getAdminUsersPage().isUserInList(AddNewUserSteps.attemptedEmail));

    }
}


