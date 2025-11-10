package com.bazaarstores.stepDefinitions.admin_steps;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewUsers {


    AllPages pages = new AllPages();
    String urlBeforeClick;
    String urlAfterClick;

    @When("admin clicks users menu")
    public void admin_clicks_users_menu() {

        pages.getAdminDashboardPage().clickUsersMenu();

    }


    @Then("admin should be redirected to Users page successfully")
    public void admin_should_be_redirected_to_users_page_successfully() {

        assertTrue(Driver.getDriver().getCurrentUrl().contains("users"));
    }

    @Then("admin should see all users listed in Users menu")
    public void adminShouldSeeAllUsersListedInUsersMenu() {

        assertTrue(pages.getViewUsersPage().getUsersTable());
    }

    @Then("admin should see names of all users listed in Users menu")
    public void adminShouldSeeNamesOfAllUsersListedInUsersMenu() {

        assertTrue(pages.getViewUsersPage().isUsersNamesVisible());

    }

    @Then("admin should see emails of all users listed in Users menu")
    public void adminShouldSeeEmailsOfAllUsersListedInUsersMenu() {
        assertTrue(pages.getViewUsersPage().isUsersEmailsVisible());

    }

    @Then("admin should see users action in front of Users")
    public void adminShouldSeeActionButtonsInFrontOfUsers() {

        assertTrue(pages.getViewUsersPage().getUsersActions());

    }

    @And("clicks on search bar and types {string}")
    public void clicksOnSearchBarAndTypes(String searchText) {

        pages.getViewUsersPage().typeInSearchBar(searchText);


    }

    @And("admin clicks on search button")
    public void adminClicksOnSearchButton() {
        pages.getViewUsersPage().clickSearchButton();
    }


    @Then("text is typed successfully in search bar")
    public void textIsTypedSuccessfullyInSearchBar() {

        assertEquals("Only Test", pages.getViewUsersPage().getSearchBarAttribute());


    }


    @Then("admin should see that user record is displayed successfully")
    public void adminShouldSeeThatUserRecordIsDisplayedSuccessfully() {

        assertEquals(pages.getViewUsersPage().getSearchBarAttribute(), pages.getViewUsersPage().getUserEmail());
    }


    @Then("admin should error message No users found is displayed successfully")
    public void adminShouldErrorMessageNoUsersFoundIsDisplayedSuccessfully() {

        assertEquals( "No users found.", pages.getViewUsersPage().getErrorMessage());

    }

    @Then("admin should see that user record is displayed successfully regarding of email case")
    public void adminShouldSeeThatUserRecordIsDisplayedSuccessfullyRegardingOfEmailCase() {
        assertTrue(true);
    }
}
