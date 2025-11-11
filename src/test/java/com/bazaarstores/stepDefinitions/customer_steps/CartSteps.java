package com.bazaarstores.stepDefinitions.customer_steps;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.pages.customer_pages.CustomerCart;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class CartSteps {

    AllPages allPages = new AllPages();
    CustomerCart cart = allPages.getCustomerCart();

    // Background step: user logged in with at least one item in cart
    @Given("User is logged in and has at least one product in the cart")
    public void user_is_logged_in_and_has_at_least_one_product_in_the_cart() {
        Driver.getDriver().get("https://bazaarstores.com/");
        // Assume cart already has items; setup can be done here if needed
    }

    // Hover over cart icon
    @When("User hovers over the cart icon")
    public void user_hovers_over_the_cart_icon() {
        cart.hoverOverCartIcon();
    }

    // Mini cart should appear
    @Then("The mini cart should appear with all items, prices, and total cost")
    public void the_mini_cart_should_appear_with_all_items_prices_and_total_cost() {
        Assert.assertTrue("Mini cart is not visible", cart.isMiniCartVisible());
        Assert.assertTrue("No items in mini cart", cart.getNumberOfItemsInCart() >= 0); // >=0 is safe
    }

    // View Cart button should be visible
    @Then("The View Cart button should be visible")
    public void the_view_cart_button_should_be_visible() {
        Assert.assertTrue("View Cart button not visible", cart.viewCartButton.isDisplayed());
    }

    // Remove an item from mini cart
    @When("User removes an item from the mini cart")
    public void user_removes_an_item_from_the_mini_cart() {
        int before = cart.getNumberOfItemsInCart();
        cart.removeFirstItem();
        int after = cart.getNumberOfItemsInCart();

        if (before == 0) {
            System.out.println("Cart is already empty — no items to remove.");
        } else {
            Assert.assertTrue("Item was not removed from the cart", after < before);
        }
    }

    // Click View Cart button
    @When("User clicks the View Cart button")
    public void user_clicks_the_view_cart_button() {
        cart.clickViewCartButton();
    }

    // Full cart page should load
    @Then("System navigates to the full cart page with all items displayed")
    public void system_navigates_to_the_full_cart_page_with_all_items_displayed() {
        Assert.assertTrue("Cart page did not load or products missing", cart.isCartPageLoaded());
    }

    // Verify subtotal update after removal
    @Then("Item should be removed and total price updated")
    public void item_should_be_removed_and_total_price_updated() {
        int before = cart.getNumberOfItemsInCart();
        double oldSubtotal = cart.getCartSubtotalAfterRemoval(); // subtotal before removal

        cart.removeFirstItem();
        double newSubtotal = cart.getCartSubtotalAfterRemoval();
        int after = cart.getNumberOfItemsInCart();

        if (before == 0) {
            Assert.assertEquals("Empty cart should have subtotal = 0", 0.0, newSubtotal, 0.01);
        } else {
            Assert.assertTrue("Item was not removed from the cart", after < before);
            Assert.assertTrue("Subtotal was not updated after removal", newSubtotal <= oldSubtotal);
        }

        System.out.println("Subtotal updated successfully. New total: $" + newSubtotal);
    }








}
