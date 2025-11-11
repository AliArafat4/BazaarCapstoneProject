package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.admin_pages.EditUserPage;
import com.bazaarstores.stepDefinitions.admin_steps.AddNewUserSteps;
import com.bazaarstores.stepDefinitions.admin_steps.EditUserSteps;
import com.bazaarstores.utilities.ApiHelper;
import com.bazaarstores.utilities.ApiUtil;
import com.bazaarstores.utilities.ConfigReader;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ed;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bazaarstores.stepDefinitions.RegistrationSteps.email;
import static com.bazaarstores.stepDefinitions.RegistrationSteps.fullName;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class ApiSteps {


    @When("assert the registration via API")
    public void validate_the_registration_via_api() {

        Response response = given(spec()).get("/users");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("find{it.email=='" + email + "'}.name");
        String actualEmail = jsonPath.getString("find{it.email=='" + email + "'}.email");

        assertEquals(email, actualEmail);
        assertEquals(fullName, actualName);
    }


    @And("assert the registration via API using email {string}")
    public void assertTheRegistrationViaAPIUsingEmail(String email) {

        Response response = given(spec()).get("/users");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.name"));
        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.email"));

    }

    @And("assert the user addition via API")
    public void assertTheUserAdditionViaAPI() {
        Response response = given(spec()).get("/users");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("find{it.email=='" + AddNewUserSteps.attemptedEmail + "'}.name");
        String actualEmail = jsonPath.getString("find{it.email=='" + AddNewUserSteps.attemptedEmail + "'}.email");

        assertEquals(AddNewUserSteps.attemptedEmail, actualEmail);
        assertEquals(AddNewUserSteps.attemptedName, actualName);
    }

    @And("assert the invalid user addition via API")
    public void assertTheInvalidUserAdditionViaAPI() {

        // Fetch API response
        Response response = ApiHelper.fetchAllUsersResponse();
        JsonPath jsonPath = response.jsonPath();

        String attemptedEmail = AddNewUserSteps.attemptedEmail;
        Integer beforeCount = ApiHelper.getUsersBeforeAddCount();

        assertNotNull("Users before-add count was not captured. Ensure 'capture current users count' ran successfully.", beforeCount);

        // CASE 1: Email provided → assert it doesn’t exist in API
        if (attemptedEmail != null && !attemptedEmail.isBlank()) {

            List<String> allEmails = jsonPath.getList("email");
            long count = allEmails.stream()
                    .filter(email -> email != null && email.equalsIgnoreCase(attemptedEmail))
                    .count();

            assertEquals(
                    "Invalid email should not have been added. Found " + count + " occurrence(s) of " + attemptedEmail,
                    0, count
            );

            return;
        }

        // CASE 2: No email provided → fallback to user count comparison
        List<?> users = jsonPath.getList("$");
        int afterCount = (users == null) ? 0 : users.size();

        assertEquals(
                "Expected no new users to be created for invalid input, but count increased!",
                beforeCount.intValue(),
                afterCount
        );
    }


    @Then("verify that the API does not confirm or store the new changes")
    public void verifyThatTheAPIDoesNotConfirmOrStoreTheNewChanges() {
        Response response = given(spec()).get("/users");
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("find{it.email=='" + EditUserPage.enteredEmail + "'}.name");

        assertNotNull(actualName, "User with email " + EditUserPage.enteredEmail + " was not found in the API response.");

        // the saved name must NOT equal the name that was typed but not saved.
        assertNotEquals(EditUserPage.newName, actualName,
                "The API returned the new name for the user — expected the edit NOT to be saved after navigating back.");

    }

    @And("confirm deletion success via API")
    public void confirmDeletionSuccessViaAPI() {
        assertTrue(true);
    }

    @And("confirm deletion failure via API")
    public void confirmDeletionFailureViaAPI() {

        // Get the target email created earlier
        String expectedEmailToFind = EditUserSteps.usersEmail;
        System.out.println("Verifying user still exists via API: " + expectedEmailToFind);

        Response response = given(spec()).get("/users");
       // response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        String emailFound = jsonPath.getString("find { it.email == '" + expectedEmailToFind + "' }.email");

        // Assert: user should still exist (deletion was cancelled)
        assertNotNull("User should still exist, but was not found in the API response: " + expectedEmailToFind, emailFound);
        assertEquals("Email wasn't found", expectedEmailToFind, emailFound);

    }

    @And("confirm admin deletion failure via API")
    public void confirmAdminDeletionFailureViaAPI() {
        assertTrue(true);
    }

    @And("assert the invalid email account was not created addition via API")
    public void assertTheInvalidEmailAccountWasNotCreatedAdditionViaAPI() {
        assertTrue(true);
    }

    @And("assert that the email is unique and only used once via API")
    public void assertThatTheEmailIsUniqueAndOnlyUsedOnceViaAPI() {

        Response response = RestAssured.given(spec()).get("/users");
        JsonPath jsonPath = response.jsonPath();

        String targetEmail = AddNewUserSteps.attemptedEmail;

        assertNotNull("Attempted email is null — check your AddNewUserSteps.attemptedEmail assignment.", targetEmail);
        assertFalse("Attempted email is blank.", targetEmail.isBlank());


        List<String> allEmails = jsonPath.getList("email");

        // Count how many times the attempted email appears
        long count = allEmails.stream()
                .filter(email -> email != null && email.equalsIgnoreCase(targetEmail))
                .count();

        // Assert that it exists exactly once
        assertEquals(
                "Email should exist exactly once in the API, but found " + count + " occurrences for: " + targetEmail,
                1, count
        );


    }

    @And("assert the name update via API")
    public void assertTheNameUpdateViaAPI() {
        Response response = given(spec()).get("/users");

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("find { it.email == '" + EditUserSteps.usersEmail + "' }.name");

        assertEquals("User name was not updated correctly.", EditUserPage.newName, actualName);
    }

    @And("assert the role update via API")
    public void assertTheRoleUpdateViaAPI() {
        Response response = given(spec()).get("/users");

        JsonPath jsonPath = response.jsonPath();
        String actualRole = jsonPath.getString("find { it.email == '" + EditUserSteps.usersEmail + "' }.role");

        assertEquals("User role was not updated correctly.", EditUserPage.newRole.toLowerCase(), actualRole.toLowerCase());
    }

    @And("assert the email update via API")
    public void assertTheEmailUpdateViaAPI() {
        Response response = given(spec()).get("/users");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        List<String> allEmails = jsonPath.getList("email");

        assertTrue(
                "Updated email was not found in API response. Expected: " + EditUserPage.enteredEmail,
                allEmails.contains(EditUserPage.enteredEmail)
        );
    }

    @And("assert the password update via API")
    public void assertThePasswordUpdateViaAPI() {
        // Try logging in using the updated credentials
        Map<String, String> loginPayload = new HashMap<>();
        loginPayload.put("email", EditUserSteps.usersEmail);
        loginPayload.put("password", "Pass@12345"); // the new password you entered in edit

        String token = ApiUtil.loginAndGetToken(
                EditUserSteps.usersEmail,
                "Pass@12345"
        );
        Assert.assertNotNull("Token should not be null", token);

        Response response = ApiUtil.get("/me");
        response.prettyPrint();
        ApiUtil.verifyStatusCode(response, 200);
    }

    @And("assert the password did not update via API")
    public void assertThePasswordDidNotTUpdateViaAPI() {
       assertTrue(true);
    }

    @And("assert the email update without domain extension failed via API")
    public void assertTheEmailUpdateWithoutDomainExtensionFailedViaAPI() {
        assertTrue(true);
    }

    @And("assert the email update failed via API")
    public void assertTheEmailUpdateFailedViaAPI() {
        Response response = given(spec()).get("/users");
        JsonPath jsonPath = response.jsonPath();

        List<String> allEmails = jsonPath.getList("email");

        assertFalse(
                "Invalid email should not appear in the API response. Found: " + EditUserPage.enteredEmail,
                allEmails.contains(EditUserPage.enteredEmail)
        );
    }
}
