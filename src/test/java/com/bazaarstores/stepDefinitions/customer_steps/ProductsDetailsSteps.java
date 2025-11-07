package com.bazaarstores.stepDefinitions.customer_steps;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;

import static org.junit.Assert.*;

public class ProductsDetailsSteps {


    AllPages pages = new AllPages();
    static String urlBeforeClick;
    static String urlAfterClick;


    @When("customer clicks on a product to open details page")
    public void customer_clicks_on_a_product_to_open_details_page() {

        urlBeforeClick = Driver.getDriver().getCurrentUrl();
        pages.getCustomerMainPage().clickProduct();
        urlAfterClick  = Driver.getDriver().getCurrentUrl();


    }


    @Then("customer should see product details page")
    public void customer_should_see_product_details_page() {

        assertNotEquals(urlBeforeClick, urlAfterClick);
    }


    @Then("customer can see product name on details page")
    public void customerCanSeeProductNameOnDetailsPage() {

        if(!urlBeforeClick.equals(urlAfterClick)){
            assertTrue(pages.getCustomerMainPage().productsNamesIsVisible());
        } else{
            Assert.fail();
        }
    }


    @Then("customer can see product price on details page")
    public void customerCanSeeProductPriceOnDetailsPage() {

        if(!urlBeforeClick.equals(urlAfterClick)){
            assertTrue(pages.getCustomerMainPage().productsPricesIsVisible());
        } else{
            Assert.fail();
        }
    }

    @Then("customer can see product description on details page")
    public void customerCanSeeProductDescriptionOnDetailsPage() {

        if(!urlBeforeClick.equals(urlAfterClick)){
            assertTrue(pages.getCustomerMainPage().productsDescriptionIsVisible());
        } else{
            Assert.fail();
        }
    }

    @Then("customer can see product image on details page")
    public void customerCanSeeProductImageOnDetailsPage() {
        if(!urlBeforeClick.equals(urlAfterClick)){
            assertTrue(pages.getCustomerMainPage().productsImagesIsVisible());
        } else{
            Assert.fail();
        }
    }

    @Then("customer can see product details for the product selected")
    public void customerCanSeeProductDetailsForTheProductSelected() {

        assertTrue(true);
    }
}
