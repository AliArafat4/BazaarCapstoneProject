package com.bazaarstores.stepDefinitions.customer_steps;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class FavoriteSteps {

    AllPages allPages = new AllPages();


    // Add Favorite
    @When("user hovers and clicks heart icon on product {int}")
    public void user_hovers_and_clicks_heart_icon_on_product(int position) {
        allPages.getCustomerFavorite().hoverAndClickHeart(position);
    }

    @Then("heart icon should appear on product {int}")
    public void heart_icon_should_appear_on_product(int position) {
        Assert.assertTrue(allPages.getCustomerFavorite().isHeartIconDisplayed(position));
    }

    @Then("heart icon should turn red for product {int}")
    public void heart_icon_should_turn_red_for_product(int position) {
        String color = allPages.getCustomerFavorite().getHeartIconColor(position);
        Assert.assertEquals("rgba(255, 68, 68, 1)", color);
    }

    @Then("product should be added to My Favorites page")
    public void product_should_be_added_to_my_favorites_page() {
        Assert.assertTrue("Product should appear in My Favorites page",
                allPages.getCustomerFavorite().isProductInFavoritesPage());
    }

    // Duplicate Favorite
    @When("user clicks the heart icon again on the same product")
    public void user_clicks_the_heart_icon_again_on_the_same_product() {
        allPages.getCustomerFavorite().hoverAndClickHeart(1);
    }

    @Then("error message should be displayed {string}")
    public void error_message_should_be_displayed(String expectedMessage) {
        String actualMessage = allPages.getCustomerFavorite().getFullErrorMessage();
        Assert.assertTrue("Expected message not found. Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
        allPages.getCustomerFavorite().clickCoolButton();
    }

    // My Favorites Page
    @When("user goes to My Favorites page")
    public void user_goes_to_my_favorites_page() {
        allPages.getCustomerFavorite().clickMyFavoritesLink();
    }

    // Remove Favorite
    @When("user removes the product from favorites at position {int}")
    public void user_removes_the_product_from_favorites_at_position(int position) {
        allPages.getCustomerFavorite().removeProductFromFavorites(position);
    }

    @Then("product should be removed from My Favorites page")
    public void product_should_be_removed_from_my_favorites_page() {
        Assert.assertTrue("Product should be removed from My Favorites page",
                allPages.getCustomerFavorite().isProductRemovedFromFavorites());
    }
}
