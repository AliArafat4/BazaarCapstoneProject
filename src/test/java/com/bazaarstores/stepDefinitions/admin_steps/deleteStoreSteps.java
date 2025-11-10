package com.bazaarstores.stepDefinitions.admin_steps;
import com.bazaarstores.pages.AllPages;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;
import java.util.Map;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static org.junit.Assert.fail;

public class deleteStoreSteps {


    AllPages allPages=new AllPages();
    static String storeName;



    @Then("a confirmation dialog should appear")
    public void a_confirmation_dialog_should_appear() {
        Assert.assertTrue(allPages.getAdminDashboardPage().isDialogDisplayed());
    }

    @When("the admin clicks the Confirm button")
    public void the_admin_clicks_the_confirm_button() {
        allPages.getAdminDashboardPage().clickConfirmDelete();
    }


    @Then("verify the deletion via the API")
    public void verify_the_deletion_via_the_api() {
        Response response = RestAssured.given(spec()).get("/stores");
        List<Map<String, Object>> stores = response.jsonPath().getList("$");
        for (Map<String, Object> store : stores) {
            if(store.get("name").equals(storeName)) {
                fail();
                return;
            }
        }
        Assert.assertTrue(true);

    }

    @When("the admin clicks the Cancel button")
    public void the_admin_clicks_the_cancel_button() {
        allPages.getAdminDashboardPage().clickCancelDelete();

    }

    @Then("verify the store still exists via the API")
    public void verify_the_store_still_exists_via_the_api() {
        Response response = RestAssured.given(spec()).get("/stores");

        List<Map<String, Object>> stores = response.jsonPath().getList("$");

        for (Map<String, Object> store : stores) {
            if(store.get("name").equals(storeName)) {
                Assert.assertTrue(true);
                return;
            }
        }
        fail();

    }


    @Then("a {string} message should be displayed")
    public void aMessageShouldBeDisplayed(String msg) {
        Assert.assertTrue(allPages.getAdminDashboardPage().isDeleteMessageDisplayed());
    }

    @Then("the store should be deleted")
    public void theStoreShouldBeDeleted() {
        Assert.assertFalse(allPages.getAdminDashboardPage().isStoreDisplayed(storeName));

    }

    @When("the admin clicks the Delete button of the store")
    public void theAdminClicksTheDeleteButtonOfTheStore() {
        allPages.getAdminDashboardPage().clickDeleteStore(storeName);

    }

    @Then("the store should not be deleted")
    public void theStoreShouldNotBeDeleted() {
        Assert.assertTrue(allPages.getAdminDashboardPage().isStoreDisplayed(storeName));
    }

    @And("the admin adds a new store with fake data")
    public void theAdminAddsANewStoreWithFakeData() {
        deleteStoreSteps.storeName =new Faker().name().title();
        String location="Jeddah";
        String adminName="Store Manager";
        String description="store description";
        allPages.getAddStorePage().fillStoreData(storeName,location,adminName,description);

    }
}
