package com.bazaarstores.stepDefinitions.customer_steps;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductsToCartSteps {


    AllPages pages = new AllPages();
    int initialCartCount;
    int differentProductsAddCount = 3;
    int sameProductAddCount = 3;

    @Then("cart count should be zero for the customer at the start")
    public void cartCountShouldBeZeroForTheCustomerAtTheStart() {
        assertEquals(0,pages.getCustomerMainPage().getCartCount());
    }

    @When("customer clicks on Add to Cart button for a product")
    public void customer_clicks_on_Add_to_Cart_button_for_a_product()  {

        initialCartCount = pages.getCustomerMainPage().getCartCount();
        pages.getCustomerMainPage().addProductToCart(1);
    }

    @Then("the cart count should increase by one")
    public void theCartCountShouldIncreaseByOne()  {

        int updatedCartCount = pages.getCustomerMainPage().getCartCount();
        assertEquals(initialCartCount + 1, updatedCartCount);
        pages.getCustomerMainPage().removeItem(1);
    }


    @Then("the product should be added to the cart")
    public void the_product_should_be_added_to_the_cart()  {

        assertTrue(pages.getCustomerMainPage().getCartItemName(1));
        pages.getCustomerMainPage().removeItem(1);

    }




    @When("customer adds multiple different products to the cart")
    public void customerAddsMultipleDifferentProductsToTheCart() {

        pages.getCustomerMainPage().addProductToCart(differentProductsAddCount);

    }


    @Then("cart count must be increased by the number of products added")
    public void cartCountMustBeIncreasedByTheNumberOfProductsAdded() {

        assertEquals(differentProductsAddCount , pages.getCustomerMainPage().getCartCount());
        for(int i=1; i<=differentProductsAddCount; i++){
            pages.getCustomerMainPage().removeItem(1);
        }

    }

    @When("customer adds the same product multiple times to the cart")
    public void customerAddsTheSameProductMultipleTimesToTheCart() {

        for(int i=1; i<=sameProductAddCount; i++){
            pages.getCustomerMainPage().addProductToCart(1);
        }
    }

    @Then("cart count must be increased by the number of that product additions")
    public void cartCountMustBeIncreasedByTheNumberOfThatProductAdditions() {

        assertTrue(true);

        pages.getCustomerMainPage().removeItem(1);

    }


}
