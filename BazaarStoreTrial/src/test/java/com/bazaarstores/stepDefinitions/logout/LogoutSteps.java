package com.bazaarstores.stepDefinitions.logout;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogoutSteps {

    AllPages allPages = new AllPages();

    @Given("user is logged in and dashboard page is open")
    public void user_is_logged_in_and_dashboard_page_is_open() {
        allPages.getLoginPage().navigateToLoginPage();
        allPages.getLoginPage()
                .enterEmail(ConfigReader.getCustomerEmail())
                .enterPassword(ConfigReader.getDefaultPassword())
                .clickLoginButton();
        Assert.assertTrue("Dashboard page should be displayed",
                allPages.getDashboardPage().isDashboardPageDisplayed());
    }

    @When("user clicks the user icon on the top right")
    public void user_clicks_the_user_icon_on_the_top_right() {
        allPages.getDashboardPage().clickProfileLink();
    }

    @And("user selects {string} from the dropdown")
    public void user_selects_from_the_dropdown(String option) {
        if (option.equalsIgnoreCase("Log Out") || option.equalsIgnoreCase("Logout")) {
            allPages.getDashboardPage().clickLogout();
        }
    }

    @Then("system should log the user out")
    public void system_should_log_the_user_out() {
        Assert.assertTrue("User should be logged out and redirected to Login Page",
                allPages.getLoginPage().isLoginPageDisplayed());
    }

    @Then("user should be redirected to the Login Page")
    public void user_should_be_redirected_to_the_login_page() {
        Assert.assertTrue("Login page should be displayed after logout",
                allPages.getLoginPage().isLoginPageDisplayed());
    }

    @When("user clicks browser back button")
    public void user_clicks_browser_back_button()  {
        Driver.getDriver().navigate().back();
        Driver.getDriver().navigate().refresh();
        Assert.assertTrue("User was able to access Dashboard after logout!",
                allPages.getLoginPage().isLoginPageDisplayed());

    }

    @Then("system should keep the user on the Login Page")
    public void system_should_keep_the_user_on_the_login_page() {
        Assert.assertTrue("User was able to access Dashboard after logout!",
                allPages.getLoginPage().isLoginPageDisplayed());
    }

}
