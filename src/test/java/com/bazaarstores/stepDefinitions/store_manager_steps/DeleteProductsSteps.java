package com.bazaarstores.stepDefinitions.store_manager_steps;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.bazaarstores.utilities.ApiUtilities.spec;

public class DeleteProductsSteps {

    AllPages allPages = new AllPages();

    @When("store manager clicks on Delete Product Button for {string} product")
    public void store_manager_clicks_on_delete_product_button_for_product(String productName) {
        allPages.getProductsPage().clickDeleteProductButton(productName);
    }

    @When("delete dialogue shows")
    public void delete_dialogue_shows() {
        Assert.assertTrue(allPages.getProductsPage().isDisplayedDeleteDialogue());
    }

    @When("store manager confirms deletion of Product")
    public void store_manager_confirms_deletion_of_product() {
        allPages.getProductsPage().clickConfirmDeleteButton();
    }

    @Then("store manager should see success message for deleting a product")
    public void store_manager_should_see_success_message_for_deleting_a_product() {
        Assert.assertTrue(allPages.getProductsPage().isSuccessMessageDisplayed());
    }

    @Then("assert the deleted product via API with sku {string}")
    public void assert_the_deleted_product_via_api_with_sku(String sku) {
        Response response = RestAssured.given(spec()).get("/products");

        JsonPath jsonPath = response.jsonPath();
        String actualProductName = jsonPath.getString("find{it.sku=='" + sku + "'}.sku");

        Assert.assertNull(actualProductName);
    }

    @Then("delete dialogue shows with alert message {string}")
    public void deleteDialogueShowsWithAlertMessage(String alertMsg) {
        Assert.assertTrue(allPages.getProductsPage().alertMessage(alertMsg));
    }

    @And("store manager cancels deletion of Product")
    public void storeManagerCancelsDeletionOfProduct() {
        allPages.getProductsPage().clickCancelDeleteButton();
    }

    @Then("assert the product is not deleted via API with sku {string}")
    public void assertTheProductIsNotDeletedViaAPIWithSku(String sku) {
        Response response = RestAssured.given(spec()).get("/products");

        JsonPath jsonPath = response.jsonPath();
        String actualProductName = jsonPath.getString("find{it.sku=='" + sku + "'}.sku");

        Assert.assertNotNull(actualProductName);
    }
}