package com.bazaarstores.stepDefinitions.store_manager_steps;

import com.bazaarstores.pages.store_manager_pages.ViewCatalogAllProduct;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ViewCatalogStep {

    ViewCatalogAllProduct viewCatalog = new ViewCatalogAllProduct();

//    @Given("User is logged in as Store Manager")
//    public void user_is_logged_in_as_store_manager() {
//    }

    @When("User clicks on {string} from the sidebar")
    public void user_clicks_on_from_the_sidebar(String option) {
        viewCatalog.clickProductsFromSidebar();
    }
    @Then("Products page loads showing a list of products")
    public void products_page_loads_showing_a_list_of_products() {
        Assert.assertTrue(" Products page did not load correctly!", viewCatalog.isProductsPageDisplayed());
    }


    @Given("User is on Products page")
    public void user_is_on_products_page() {
        viewCatalog.clickProductsFromSidebar();
    }

    @Then("All columns Name, Price, Stock, Category, Image, Action are visible")
    public void all_columns_are_visible() {
        Assert.assertTrue(" Not all product columns are visible!", viewCatalog.areAllProductColumnsVisible());
    }




    @Then("Each product entry displays correct details")
    public void each_product_entry_displays_correct_details() {
        Assert.assertTrue("Some product entries are missing details!", viewCatalog.areAllProductDetailsVisible());
    }
}
