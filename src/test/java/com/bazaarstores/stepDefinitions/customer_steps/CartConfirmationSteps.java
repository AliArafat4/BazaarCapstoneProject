package com.bazaarstores.stepDefinitions.customer_steps;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.pages.customer_pages.CustomerCartConfirmation;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class CartConfirmationSteps {

    AllPages allPages = new AllPages();
    CustomerCartConfirmation cartPage = allPages.getCustomerCartConfirmation();

    @When("User clicks the Confirm Cart button")
    public void user_clicks_the_confirm_cart_button() {
        cartPage.clickConfirmCartButton();
    }
    @Then("A success message should appear with text {string} and an OK button")
    public void a_success_message_should_appear_with_text_and_an_ok_button(String expectedMessage) {
        String actualMessage = cartPage.getFullSuccessMessage();
        Assert.assertEquals("Success message did not match", expectedMessage, actualMessage);
        Assert.assertTrue("OK button is not displayed", cartPage.isOkButtonDisplayed());
        cartPage.clickOkButton();
    }

//    @Then("The View Cart button should not be visible")
//    public void the_view_cart_button_should_not_be_visible() {
//        Assert.assertFalse("View Cart button should be hidden", cartPage.isViewCartButtonVisible());
//    }

    @Then("The View Cart button should not be visible")
    public void the_view_cart_button_should_not_be_visible() {
        Assert.assertFalse("View Cart button should be hidden", cartPage.isViewCartButtonVisible());
    }



    @Given("User has not added any product to the cart")
    public void user_has_not_added_any_product_to_the_cart() {
        if (cartPage.isViewCartButtonVisible()) {
            cartPage.clickViewCartButton();

            cartPage.clickConfirmCartButton();
            cartPage.clickOkButton();
        }
    }




}
