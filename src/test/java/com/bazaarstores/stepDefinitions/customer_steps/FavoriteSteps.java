package com.bazaarstores.stepDefinitions.customer_steps;
import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class FavoriteSteps {

    AllPages allPages = new AllPages();

    @When("user logs in as a customer")
    public void user_logs_in_as_a_customer() {
        allPages.getLoginPage()
                .enterEmail(ConfigReader.getCustomerEmail())
                .enterPassword(ConfigReader.getDefaultPassword())
                .clickLoginButton();

        Assert.assertTrue(allPages.getDashboardPage().isDashboardPageDisplayed());
    }

    @When("user hovers over a product")
    public void user_hovers_over_a_product() {
        allPages.getCustomerFavorite().hoverOverFirstProduct();
        allPages.getCustomerFavorite().storeFirstProductName();
    }

    @Then("heart icon should appear on the product")
    public void heart_icon_should_appear_on_the_product() {
        Assert.assertTrue(allPages.getCustomerFavorite().isHeartIconDisplayed());
    }

    @When("user clicks the heart icon")
    public void user_clicks_the_heart_icon() {
        allPages.getCustomerFavorite().clickHeartIcon();
    }

    @Then("heart icon should turn red indicating the product is marked as favorite")
    public void heart_icon_should_turn_red() {
        String color = allPages.getCustomerFavorite().getHeartIconColor();
        String expectedColor = "rgba(255, 68, 68, 1)"; // اللون #ff4444
        Assert.assertEquals(expectedColor, color);
    }


    //2
    @Then("product should be added to My Favorites page")
    public void product_should_be_added_to_my_favorites_page() {
        boolean isAdded = allPages.getCustomerFavorite().isProductInFavoritesPage();
        Assert.assertTrue("Product should appear in My Favorites page", isAdded);
    }


    //3
    @When("user clicks the heart icon again on the same product")
    public void user_clicks_the_heart_icon_again_on_the_same_product() {
        allPages.getCustomerFavorite().clickHeartIcon();
    }


    @Then("error message should be displayed {string}")
    public void error_message_should_be_displayed(String expectedMessage) {
        String actualMessage = allPages.getCustomerFavorite().getFullErrorMessage();
        Assert.assertTrue("Expected message not found. Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));

        allPages.getCustomerFavorite().clickCoolButton();
    }

    //4
    @When("user clicks on My Favorites from the header")
    public void user_clicks_on_my_favorites_from_the_header() {
        allPages.getCustomerFavorite().clickMyFavoritesLink();
    }


//5


    @When("user goes to My Favorites page")
    public void user_goes_to_my_favorites_page() {
        allPages.getCustomerFavorite().clickMyFavoritesLink();
    }

    @When("user removes the product from favorites")
    public void user_removes_the_product_from_favorites() {
        allPages.getCustomerFavorite().removeFirstProductFromFavorites();
    }

    @Then("product should be removed from My Favorites page")
    public void product_should_be_removed_from_my_favorites_page() {
        boolean isRemoved = allPages.getCustomerFavorite().isProductRemovedFromFavorites();
        Assert.assertTrue("Product should be removed from My Favorites page", isRemoved);
    }




}


