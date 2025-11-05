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
import static org.junit.Assert.assertEquals;

public class addProductsSteps {

    AllPages allPages = new AllPages();

    @Given("user is in home page")
    public void user_is_in_home_page() {
        Driver.getDriver().get(ConfigReader.getBaseUrl());
    }

    @Given("store manager is logged in")
    public void storeManagerIsLoggedIn() {
        allPages.getStoreManagerDashboardPage().isDashboardButtonDisplayed();
    }

    @When("store manager navigates to Products section")
    public void store_manager_navigates_to_products_section() {
        allPages.getStoreManagerDashboardPage().clickProductsButton();

    }

    @When("store manager clicks on ADD PRODUCT button")
    public void store_manager_clicks_on_add_products_button() {
        allPages.getProductsPage().clickAddProductButton();
    }

    @When("store manager enters product name {string}")
    public void store_manager_enters_product_name(String productName) {
        allPages.getAddProductsPage().enterProductName(productName);
    }

    @When("store manager sets product price {string}")
    public void store_manager_sets_product_price(String price) {
        allPages.getAddProductsPage().enterProductPrice(price);

    }

    @When("store manager sets product stock {string}")
    public void store_manager_sets_product_stock(String stock) {
        allPages.getAddProductsPage().enterProductStoke(stock);

    }

    @When("store manager sets product SKU {string}")
    public void store_manager_sets_product_sku(String sku) {
        allPages.getAddProductsPage().enterProductSKU(sku);

    }

    @When("store manager sets product category {string}")
    public void store_manager_sets_product_category(String category) {
        allPages.getAddProductsPage().selectProductCategory(category);

    }

    @When("store manager sets product manufacturer {string}")
    public void store_manager_sets_product_manufacturer(String manufacturer) {
        allPages.getAddProductsPage().enterProductManufacturer(manufacturer);

    }

    @When("store manager uploads product image {string}")
    public void store_manager_uploads_product_image(String imagePath) {
        allPages.getAddProductsPage().enterProductImage(imagePath);

    }

    @When("store manager sets product discount {string}")
    public void store_manager_sets_product_discount(String discount) {
        allPages.getAddProductsPage().enterProductDiscount(discount);

    }

    @When("store manager enters product description {string}")
    public void store_manager_enters_product_description(String description) {
        allPages.getAddProductsPage().enterProductDescription(description);

    }

    @When("store manager clicks on Submit button")
    public void store_manager_clicks_on_submit_button() {
        allPages.getAddProductsPage().clickSubmitButton();

    }

    @Then("store manager should see success message for adding product")
    public void store_manager_should_see_success_message_for_adding_product() {
        Assert.assertTrue(allPages.getStoreManagerDashboardPage().isSuccessMessageDisplayed());
    }

    @Then("store manager get redirected to Products page")
    public void storeManagerGetRedirectedToProductsPage() {
        allPages.getStoreManagerDashboardPage().isDashboardButtonDisplayed();
    }

    @Then("store manager should see the new product in the products list with name {string}")
    public void store_manager_should_see_the_new_product_in_the_products_list_with_name(String productName) {
        Assert.assertTrue(allPages.getProductsPage().isProductInList(productName));
    }


    @Then("assert the new product via API with name {string}")
    public void assert_the_new_product_via_api_with_name(String productName) {
        Response response = RestAssured.given(spec()).get("/products");

        JsonPath jsonPath = response.jsonPath();
        String actualProductName = jsonPath.getString("find{it.name=='" + productName + "'}.name");

        assertEquals(productName, actualProductName);

    }

}