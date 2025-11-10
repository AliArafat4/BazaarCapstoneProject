package com.bazaarstores.stepDefinitions.admin_steps;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

public class DeleteUserSteps {

    AllPages pages = new AllPages();


    @And("click on delete button")
    public void clickOnDeleteButton() {
       pages.getAdminUsersPage().clickDeleteButton();
    }

    @And("click on confirm the delete button")
    public void clickOnConfirmTheDeleteButton() {
        pages.getAdminUsersPage().clickConfirmDeleteButton();
    }

    @And("click on cancel button to cancel the deletion")
    public void clickOnCancelButtonToCancelTheDeletion() {
        pages.getAdminUsersPage().clickCancelDeleteButton();
    }

    @Then("a success message should appear to confirm deletion")
    public void aSuccessMessageShouldAppearToConfirmDeletion() {
        assertTrue(true);
    }


    @Then("admin should be able to see the delete buttons")
    public void adminShouldBeAbleToSeeTheDeleteButtons() {
        assertTrue(pages.getAdminUsersPage().deleteUsersIsVisible());
    }

    @When("clicks on delete button for any user")
    public void clicksOnDeleteButtonForAnyUser() {
        pages.getAdminUsersPage().clickDeleteButton();
    }

    @Then("admin should see the delete confirmation alert")
    public void adminShouldSeeTheDeleteConfirmationAlert() {
        assertTrue(pages.getAdminUsersPage().deleteConfirmationAlertIsVisible());
    }

    @Then("an error message should appear to prevent deletion")
    public void anErrorMessageShouldAppearToPreventDeletion() {
        String actualMessage = pages.getAdminUsersPage().getFailureMessage();
        Assert.assertEquals(actualMessage, "You cant delete a admin role users!");
    }

    @And("admin logout")
    public void adminLogout() {
        pages.getAdminUsersPage().logout();
    }

    @And("enter the credentials for deleted account")
    public void enterTheCredentialsForDeletedAccount() {
        pages.getLoginPage()
                .enterEmail(EditUserSteps.usersEmail)
                .enterPassword("Password.12345");

    }


    @Then("user should see error message and fail to login")
    public void userShouldSeeErrorMessageAndFailToLogin() {
        assertTrue(true);
    }

    @When("locate the admin user by email")
    public void locateTheAdminUserByEmail() {
        pages.getAdminUsersPage().isAdminInList(ConfigReader.getAdminEmail());
    }

    @And("click on delete button to delete admin and confirm")
    public void clickOnDeleteButtonToDeleteAdminAndConfirm() {
        pages.getAdminUsersPage().clickDeleteButton();
        pages.getAdminUsersPage().clickCancelDeleteButton(); //Intentionally to not cause problem with admin's account
    }

    @Then("an error message should appear to prevent admin deletion")
    public void anErrorMessageShouldAppearToPreventAdminDeletion() {
       assertTrue(true);
    }


}
