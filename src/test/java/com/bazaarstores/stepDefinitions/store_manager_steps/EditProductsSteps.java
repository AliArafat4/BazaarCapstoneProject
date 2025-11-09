package com.bazaarstores.stepDefinitions.store_manager_steps;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.bazaarstores.utilities.ApiUtilities.spec;
import static org.junit.Assert.*;

public class EditProductsSteps {

    AllPages allPages = new AllPages();

    @When("store manager clicks on Edit Product Button for {string} product")
    public void store_manager_clicks_on_edit_product_button(String productName) {
        allPages.getProductsPage().clickEditProductButton(productName);
    }

    @When("store manager change Product {string} to {string}")
    public void store_manager_change_product_name_to(String editedField, String value) {
        allPages.getEditProductPage()
                .clearEditedField(editedField)
                .editProduct(editedField, value);
    }

    @Then("store manager should see success message for editing a product")
    public void store_manager_should_see_success_message_for_editing_a_product() {
        allPages.getProductsPage().isSuccessMessageDisplayed();
    }

    @Given("product is available in the list with name {string}, price {string}, stock {string}, and sku {string}")
    public void product_is_available_in_the_List(String name, String price, String stock, String sku) {
        allPages.getProductsPage().clickAddProductButton();
        allPages.getAddProductsPage()
                .enterProductName(name)
                .enterProductPrice(price)
                .enterProductStoke(stock)
                .enterProductSKU(sku)
                .clickSubmitButton();
        Assert.assertTrue(allPages.getProductsPage().isProductInList(name));

    }

    @Then("store manager should see an error message for missing product {string} field")
    public void store_manager_should_see_an_error_message_for_missing_product_field(String fieldName) {
        Assert.assertTrue(allPages.getEditProductPage().hasErrorMessage(fieldName));
    }

    @And("assert the edited product via API with {string} {string} and sku {string}")
    public void assertTheEditedProductViaAPIWithNameAndSku(String field, String editedValue, String sku) {
        Response response = RestAssured.given(spec()).get("/products");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String actualValue = jsonPath.getString("find{it.sku=='" + sku + "'}." + field);

        if (field.contains("image")) {
            assertNotNull(actualValue);
        } else {
            assertEquals(editedValue, actualValue);
        }

    }

    @And("assert the product wasn't edited via API with {string} {string} and sku {string}")
    public void assertTheProductWasnTEditedViaAPIWithFieldAndSku(String field, String editedValue, String sku) {
        Response response = RestAssured.given(spec()).get("/products");

        JsonPath jsonPath = response.jsonPath();
        String actualValue = jsonPath.getString("find{it.sku=='" + sku + "'}." + field);

        assertNotEquals(editedValue, actualValue);
    }

    @And("store manager should see the product in the products list with name {string} and {string} {string}")
    public void storeManagerShouldSeeTheProductInTheProductsListWithNameAndPrice(String name, String field, String value) {
        Assert.assertTrue(allPages.getProductsPage().isEditedProductInPage(name, value));

    }
}