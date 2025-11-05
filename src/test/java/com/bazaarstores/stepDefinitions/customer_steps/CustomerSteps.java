package com.bazaarstores.stepDefinitions.customer_steps;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class CustomerSteps {


    AllPages pages = new AllPages();


    @When("customer can see products names")
    public void customer_can_see_products_names() {
        assertTrue(pages.getCustomerMainPage().productsNamesIsVisible());
    }


    @When("Customer can see products under three seconds")
    public void customerCanSeeProductsUnderThreeSeconds() {
        long start = System.currentTimeMillis();
        pages.getCustomerMainPage().productsIsDisplayed();
        long elapsed = System.currentTimeMillis() - start;
        assertTrue( elapsed  <= 3000);
    }


    @When("customer can see products")
    public void customerCanSeeProducts() {
        assertTrue(pages.getCustomerMainPage().productsIsDisplayed());
    }


    @When("customer can see products Prices")
    public void customerCanSeeProductsPrices() {
        assertTrue(pages.getCustomerMainPage().productsPricesIsVisible());
    }

    @When("customer can see products Images")
    public void customerCanSeeProductsImages() {
        assertTrue(pages.getCustomerMainPage().productsImagesIsVisible());
    }
}
