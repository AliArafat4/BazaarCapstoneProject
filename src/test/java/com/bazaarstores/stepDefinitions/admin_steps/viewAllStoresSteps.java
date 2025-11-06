package com.bazaarstores.stepDefinitions.admin_steps;
import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static org.junit.Assert.*;

public class viewAllStoresSteps {

    AllPages allPages= new AllPages();

    @When("the admin clicks on the Stores button")
    public void theAdminClicksOnTheStoresButton() {
        allPages.getAdminDashboardPage().clickStoresLink();

    }

    @Then("all stores should be displayed in a table")
    public void allStoresShouldBeDisplayedInATable() {
        assertTrue(allPages.getAdminDashboardPage().isStoresTableDisplayed());

    }

    @And("the table should have columns: Store Name, Location, Admin Name, Description, and Actions")
    public void theTableShouldHaveColumnsStoreNameLocationAdminNameDescriptionAndActions() {
        assertTrue(allPages.getAdminDashboardPage().verifyAllColumnsDisplayed());
    }

    @And("the Actions column should contain two buttons: Edit and Delete")
    public void theActionsColumnShouldContainTwoButtonsEditAndDelete() {
        assertTrue(allPages.getAdminDashboardPage().verifyActionsBtnsDisplayed());
    }

    @And("assert that stores are retrieved via the API")
    public void assertThatStoresAreRetrievedViaTheAPI() {
        Response response = RestAssured.given(spec()).get("/stores");

        List<Map<String, Object>> stores = response.jsonPath().getList("$");

        for (Map<String, Object> store : stores) {
            assertFalse(store.get("name").toString().isEmpty());
            assertFalse(store.get("description").toString().isEmpty());
            assertFalse(store.get("admin_id").toString().isEmpty());
            assertFalse(store.get("location").toString().isEmpty());
        }
    }
}
