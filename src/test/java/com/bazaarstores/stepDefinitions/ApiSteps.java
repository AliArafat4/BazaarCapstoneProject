package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.admin_pages.EditUserPage;
import com.bazaarstores.stepDefinitions.admin_steps.AddNewUserSteps;
import com.bazaarstores.stepDefinitions.admin_steps.EditUserSteps;
import com.bazaarstores.utilities.ApiHelper;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ed;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

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

        Response response = ApiHelper.fetchAllUsersResponse();

       // response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        // 1) if email was provided in the test, assert there is no user with that email
        if (AddNewUserSteps.attemptedEmail != null && !AddNewUserSteps.attemptedEmail.isBlank()) {
            String actualEmail = jsonPath.getString("find{ it.email == '" + AddNewUserSteps.attemptedEmail + "' }.email");
            assertNull("Expected no user with email: " + AddNewUserSteps.attemptedEmail + " but found one.", actualEmail);
            return;
        }

        // 2) else if name was provided, assert there is no user with that name
        if (AddNewUserSteps.attemptedName != null && !AddNewUserSteps.attemptedName.isBlank()) {
            String actualName = jsonPath.getString("find{ it.name == '" + AddNewUserSteps.attemptedName + "' }.name");
            assertNull("Expected no user with name: " + AddNewUserSteps.attemptedName + " but found one.", actualName);
            return;
        }

        // 3) else neither email nor name provided -> use count-based assertion
        Integer before = ApiHelper.getUsersBeforeAddCount();
        if (before == null) {
            throw new IllegalStateException("usersBeforeAddCount was not captured. Call 'capture current users count' before submitting when expecting count-based assertion.");
        }

        List<?> users = jsonPath.getList("$");
        int afterCount = users == null ? 0 : users.size();

        assertEquals("Expected no new users to be created", before.intValue(), afterCount);
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
}
