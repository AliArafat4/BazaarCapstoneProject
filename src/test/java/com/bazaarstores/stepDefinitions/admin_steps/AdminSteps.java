package com.bazaarstores.stepDefinitions.admin_steps;
import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.bazaarstores.stepDefinitions.RegistrationSteps.email;
import static com.bazaarstores.stepDefinitions.RegistrationSteps.fullName;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static org.junit.Assert.assertEquals;

public class AdminSteps {

    AllPages allPages= new AllPages();

    @When("the admin clicks on the Stores button")
    public void theAdminClicksOnTheStoresButton() {
        allPages.getAdminDashboardPage().clickStoresLink();

    }

    @Then("all stores should be displayed in a table")
    public void allStoresShouldBeDisplayedInATable() {
        Assert.assertTrue(allPages.getAdminDashboardPage().isStoresTableDisplayed());

    }

    @And("the table should have columns: Store Name, Location, Admin Name, Description, and Actions")
    public void theTableShouldHaveColumnsStoreNameLocationAdminNameDescriptionAndActions() {
        Assert.assertTrue(allPages.getAdminDashboardPage().verifyAllColumnsDisplayed());
    }

    @And("the Actions column should contain two buttons: Edit and Delete")
    public void theActionsColumnShouldContainTwoButtonsEditAndDelete() {
        Assert.assertTrue(allPages.getAdminDashboardPage().verifyActionsBtnsDisplayed());
    }

    @And("assert that stores are retrieved via the API")
    public void assertThatStoresAreRetrievedViaTheAPI() {
        Response response = RestAssured.given(spec()).get("/stores");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();

    }
}
