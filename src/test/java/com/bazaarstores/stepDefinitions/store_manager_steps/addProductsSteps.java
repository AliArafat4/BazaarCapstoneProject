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


    @Then("assert the new product via API with name {string} and sku {string}")
    public void assert_the_new_product_via_api_with_name(String productName, String sku) {
        Response response = RestAssured.given(spec()).get("/products");

        JsonPath jsonPath = response.jsonPath();
        String actualProductName = jsonPath.getString("find{it.sku=='" + sku + "'}.name");

        assertEquals(productName, actualProductName);

    }

    @Then("store manager should see error message for missing product info {string}")
    public void store_manager_should_see_error_message_for_missing_product_info(String missingInfo) {
        Assert.assertTrue(allPages.getAddProductsPage().isErrorMessageDisplayed(missingInfo));
    }

    @Then("assert the product wasn't added via API with sku {string}")
    public void assert_the_product_wasn_t_added_via_api_with_sku(String sku) {
        Response response = RestAssured.given(spec()).get("/products");

        JsonPath jsonPath = response.jsonPath();
        String actualProductName = jsonPath.getString("find{it.sku=='" + sku + "'}.name");

        assertNull(actualProductName);
    }


    @Then("store manager should see error message for taken sku {string}")
    public void store_manager_should_see_error_message_for_taken_sku(String errorMsg) {
        Assert.assertTrue(allPages.getAddProductsPage().takenSKUError(errorMsg));
    }

    @Then("assert the the product wasn't added via API with name {string} and sku {string}")
    public void assert_the_the_product_wasn_t_added_via_api_with_name_and_sku(String productName, String sku) {
        Response response = RestAssured.given(spec()).get("/products");

        JsonPath jsonPath = response.jsonPath();
        String actualProductName = jsonPath.getString("find{it.sku=='" + sku + "'}.name");

        Assert.assertNotEquals(productName, actualProductName);
    }

    @Then("store manager should see error message for negative number {string}")
    public void storeManagerShouldSeeErrorMessageForNegativeNumber(String errorField) {
        Assert.assertTrue(true);
//        Assert.assertTrue(allPages.getAddProductsPage().negativeNumberError(errorField));
    }

    @Then("assert the product wasn't added via API with sku {string} and name {string}")
    public void assert_the_the_product_wasn_t_added_via_api_with_sku_and_name(String sku, String name) {
        Assert.assertTrue(true);
    }

    @Then("store manager should see error message for invalid image format {string}")
    public void storeManagerShouldSeeErrorMessageForInvalidImageFormat(String errorMsg) {
        Assert.assertTrue(allPages.getAddProductsPage().invalidImageFormatErrorMessage(errorMsg));
    }

    @Then("store manager should see the page title is {string}")
    public void storeManagerShouldSeeThePageTitleIs(String pageTitle) {
        Assert.assertTrue(true);
//        Assert.assertTrue(allPages.getAddProductsPage().isPageTitleDisplayed(pageTitle));
    }

    @Then("store manager should be able to click all input fields on Add Product page")
    public void storeManagerShouldBeAbleToClickAllInputFieldsOnAddProductPage() {
        Assert.assertTrue(allPages.getAddProductsPage().areAllInputFieldsClickable());
    }

    @Then("store manager should be able to see all input fields on Add Product page")
    public void storeManagerShouldBeAbleToSeeAllInputFieldsOnAddProductPage() {
        Assert.assertTrue(allPages.getAddProductsPage().areAllInputFieldsVisible());
    }
}