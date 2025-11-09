package com.bazaarstores.stepDefinitions.admin_steps;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.pages.admin_pages.EditUserPage;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class EditUserSteps {

    AllPages pages = new AllPages();
    public static String usersName;
    public static String usersEmail;

    @Given("a user exists for editing")
    public void aUserExistsForEditing() {
            String role = "Store Manager";
            String password = "Password.12345";
            String confirmPassword = "Password.12345";

            // generate new user each time (safe)
            Faker faker = new Faker();
            usersEmail = faker.internet().emailAddress();
            usersName = faker.name().fullName();

            // navigate to Users page
            pages.getAdminUsersPage().goToUsersPage();

            // check if user already in list
            boolean userExists = pages.getAdminUsersPage().isUserInList(usersEmail);

            if (!userExists) {
                // create user if not found
                pages.getAdminUsersPage().clickAddUsers();
                pages.getAddUserPage().fillUserData(usersName, usersEmail, role, password, confirmPassword);
                pages.getAddUserPage().clickSubmit();
            }

        }



    @When("admin locate the intended user by email")
    public void admin_locate_the_intended_user_by_email() {
        pages.getAdminUsersPage().locateUser(usersEmail);
    }
    @When("click on edit button")
    public void click_on_edit_button() {
       pages.getAdminUsersPage().clickEditButton();
    }

    @When("update the name")
    public void update_the_name() {
        pages.getEditUserPage().editName();
    }

    @And("update the role")
    public void updateTheRole() {
        pages.getEditUserPage().editRole();
    }

    @And("update the email")
    public void updateTheEmail() {
       pages.getEditUserPage().editEmail();
    }

    @When("update the users to invalid email {string}")
    public void updateTheUsersToInvalidEmail(String invalidEmail) {
        pages.getEditUserPage().enterInvalidEmail(invalidEmail);

    }

//    @When("update the users {string}")
//    public void updateTheUsers(String emailValue) {
//        pages.getEditUserPage().editEmail(emailValue);
//    }

    @And("clicks on Submit button to confirm update")
    public void clicksOnSubmitButtonToConfirmUpdate() {
        pages.getEditUserPage().clickSubmit();
    }

    @Then("a success message should appear to confirm update")
    public void aSuccessMessageShouldAppearToConfirmUpdate() {
        String actualMessage = pages.getEditUserPage().getSuccessMessage();
        Assert.assertEquals(actualMessage, "User updated successfully!");
    }


    @Then("an error message should appear to confirm update failure")
    public void anErrorMessageShouldAppearToConfirmUpdateFailure() {
        String errorMessage = pages.getEditUserPage().getErrorMessage();

        if (errorMessage != null && !errorMessage.isEmpty()) {
            System.out.println(" Error message displayed: " + errorMessage);
            Assert.assertTrue(true); // test passes because an error appeared
        } else {
            Assert.fail(" No validation message appeared — expected an error message.");
        }
    }


    @Then("admin should be able to see the edit buttons")
    public void adminShouldBeAbleToSeeTheEditButtons() {
        assertTrue(pages.getAdminUsersPage().editUsersIsVisible());

    }

    @When("clicks on edit button for any user")
    public void clicksOnEditButtonForAnyUser() {
        pages.getAdminUsersPage().clickEditButton();
    }

    @Then("admin should navigates to the Edit Users page")
    public void adminShouldNavigatesToTheEditUsersPage() {
        assertTrue(pages.getEditUserPage().isOnEditUserPage());
    }
}

