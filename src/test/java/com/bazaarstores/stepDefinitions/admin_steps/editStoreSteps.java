package com.bazaarstores.stepDefinitions.admin_steps;

import com.bazaarstores.pages.AllPages;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.bazaarstores.utilities.ApiUtilities.spec;
import static com.bazaarstores.utilities.Driver.getDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class editStoreSteps {

    AllPages allPages=new AllPages();
    static String storeName;
    static String storeLocation;
    static String storeDescription;
    static String storeAdmin;
    static String storeId;
    public static String adminId;

    static String value;
    static String field;


    @When("the admin edits an existing store with details: {string}, {string}, {string}, {string}")
    public void the_admin_edits_an_existing_store_with_details(String name, String location, String adminName, String description) {

        storeName=name;
        storeLocation=location;
        storeAdmin=adminName;
        storeDescription=description;

        allPages.getAddStorePage().fillStoreData(name,location,adminName,description);

    }
    @Then("the system should display a success message: Store updated successfully.")
    public void the_system_should_display_a_success_message_store_updated_successfully() {
        String successMsg=allPages.getAdminDashboardPage().getUpdateSuccessMessage();
        Assert.assertEquals("Store updated successfully!",successMsg);

    }
    @Then("the updated store should appear in the store list")
    public void the_updated_store_should_appear_in_the_store_list() {

        assertTrue(allPages.getAdminDashboardPage().isStoreDisplayed(storeName));

    }
    @Then("verify that the store is updated via the API")
    public void verify_that_the_store_is_updated_via_the_api() {

        Response response = RestAssured.given(spec()).pathParam("id",storeId).get("/stores/{id}");
        response.then()
                .body("name", equalTo(storeName))
                .body("location", equalTo(storeLocation))
                .body("description", equalTo("<p>"+storeDescription+"</p>"))
                .body("admin_id", equalTo(Integer.parseInt(adminId)));

    }

    @And("clicks on the Edit Store button")
    public void clicksOnTheEditStoreButton() {
        allPages.getAdminDashboardPage().clickEditStore();
        String[] arr =getDriver().getCurrentUrl().split("/");
        storeId=arr[arr.length-1];
    }

    @And("the admin edits an existing store leaving the {string} field empty")
    public void theAdminEditsAnExistingStoreLeavingTheFieldEmpty(String field) {
      allPages.getAddStorePage().clearField(field);

    }

    @And("verify that the store is not updated via API")
    public void verifyThatTheStoreIsNotUpdatedViaAPI() {

        Response response = RestAssured.given(spec()).pathParam("id",storeId).get("/stores/{id}");
        response.then()
                .body("name", notNullValue())
                .body("location", notNullValue())
                .body("description", notNullValue())
                .body("admin_id", notNullValue());

    }

    @Then("the system should display a validation message for the missing fields during edit")
    public void theSystemShouldDisplayAValidationMessageForTheMissingFieldsDuringEdit() {
        Assert.assertTrue( allPages.getAdminDashboardPage().isUpdateErrorMessageDisplayed()
                || allPages.getAddStorePage().isValidationMessageDisplayed()
        );
    }


    @And("the admin edits an existing store, updating the {string} field with value {string}")
    public void theAdminEditsAnExistingStoreUpdatingTheFieldWithValue(String field, String value) {

        editStoreSteps.value =value;
        editStoreSteps.field =field;
        allPages.getAddStorePage().updateOneField(field,value);
    }

    @And("verify that the field is updated via the API")
    public void verifyThatTheFieldIsUpdatedViaTheAPI() {
        Response response = RestAssured.given(spec()).pathParam("id",storeId).get("/stores/{id}");

        if (!field.equalsIgnoreCase("admin")) {
            response.then()
                    .body(field.toLowerCase(), containsString(value));
        } else {
            response.then()
                    .body("admin_id", equalTo(Integer.parseInt(adminId)));
        }

    }


}
