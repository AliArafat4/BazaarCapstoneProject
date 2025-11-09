package com.bazaarstores.stepDefinitions;

import com.bazaarstores.stepDefinitions.admin_steps.AddNewUserSteps;
import com.bazaarstores.utilities.ApiHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static com.bazaarstores.stepDefinitions.RegistrationSteps.email;
import static com.bazaarstores.stepDefinitions.RegistrationSteps.fullName;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApiSteps {


    @When("assert the registration via API")
    public void validate_the_registration_via_api() {

        Response response = RestAssured.given(spec()).get("/users");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("find{it.email=='" + email + "'}.name");
        String actualEmail = jsonPath.getString("find{it.email=='" + email + "'}.email");

        assertEquals(email, actualEmail);
        assertEquals(fullName, actualName);
    }


    @And("assert the registration via API using email {string}")
    public void assertTheRegistrationViaAPIUsingEmail(String email) {

        Response response = RestAssured.given(spec()).get("/users");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.name"));
        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.email"));

    }

    @And("assert the user addition via API")
    public void assertTheUserAdditionViaAPI() {
        Response response = RestAssured.given(spec()).get("/users");
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

    /**
     * Optional: clear stored state between scenarios if you use static fields and run scenarios sequentially.
     */
    @And("clear api helper state")
    public void clearApiHelperState() {
        ApiHelper.clearCapturedState();
        AddNewUserSteps.attemptedEmail = null;
        AddNewUserSteps.attemptedName = null;
    }

    @Then("assert the update via API")
    public void assert_the_update_via_api() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
