package com.bazaarstores.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static com.bazaarstores.utilities.ApiUtilities.spec;

/**
 * Small reusable API helper for user-count capture and simple queries.
 * Note: uses static fields so tests should run single-threaded or use scenario-local storage.
 */
public class ApiHelper {

    private static Integer usersBeforeAddCount = null;

    /**
     * Capture the current number of users from the API and store it for later comparison.
     * Call this BEFORE attempting to create a new user in the UI if you plan to assert "no new users" later.
     */
    public static void captureUsersCountBeforeAdd() {
        Response response = RestAssured.given(spec()).get("/users");
        //response.prettyPrint();

        List<?> users = response.jsonPath().getList("$");
        usersBeforeAddCount = users == null ? 0 : users.size();
    }

    /**
     * Returns the captured count (or null if not captured).
     */
    public static Integer getUsersBeforeAddCount() {
        return usersBeforeAddCount;
    }

    /**
     * Fetch fresh /users response and return as io.restassured.response.Response for further checks.
     */
    public static Response fetchAllUsersResponse() {
        return RestAssured.given(spec()).get("/users");
    }

    /**
     * Clear stored state (useful between scenarios if using static state)
     */
    public static void clearCapturedState() {
        usersBeforeAddCount = null;
    }
}
