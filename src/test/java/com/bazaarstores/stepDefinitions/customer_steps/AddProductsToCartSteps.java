package com.bazaarstores.stepDefinitions.customer_steps;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.*;

public class AddProductsToCartSteps {


    AllPages pages = new AllPages();


    @When("customer clicks on {string} button for a product")
    public void customer_clicks_on_button_for_a_product(String string) {

        pages.getCustomerMainPage().addProductToCart(1);
    }


    @Then("the product should be added to the cart")
    public void the_product_should_be_added_to_the_cart() {



    }


}
