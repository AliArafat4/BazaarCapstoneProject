package com.bazaarstores.stepDefinitions.adminSteps;

import com.bazaarstores.pages.BasePage;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import com.bazaarstores.pages.AllPages;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AddNewUserSteps extends BasePage {

    AllPages pages = new AllPages();
    public static String fullName;
    public static String email;


    @When("admin navigates to the Users page")
    public void admin_navigates_to_the_users_page() {
       pages.getAdminUsersPage().goToUsersPage();
    }
    @When("clicks on Add Users button")
    public void clicks_on_add_users_button() {
        pages.getAdminUsersPage().clickAddUsers();
    }

//    @When("enters valid user details:")
//    public void enters_valid_user_details(DataTable dataTable) {
//
//        Map<String, String> user = dataTable.asMaps(String.class, String.class).get(0);
//
//        pages.getAddUserPage().fillUserData();
//
//
//    }

    // Positive Test
    @And("admin enters Name, valid@email.com, Role, Password, and PasswordConfirmation")
    public void adminEntersNameValidEmailComRolePasswordAndPasswordConfirmation() {
        Faker faker = new Faker();
        fullName= faker.name().fullName();
        email = faker.internet().emailAddress();
        String role = "Store Manager";

        pages.getAddUserPage().fillUserData(fullName,email,role);
    }

    // Negative Tests
    @And("admin enters valid@email.com, Role, Password, and PasswordConfirmation without Name")
    public void adminEntersValidEmailComRolePasswordAndPasswordConfirmationWithoutName() {



    }



    @When("clicks on Submit button")
    public void clicks_on_button() {
        pages.getAddUserPage().clickSubmit();
    }

    @Then("a success message should appear")
    public void a_success_message_should_appear() {
        String actualMessage = pages.getAddUserPage().getSuccessMessage();
        Assert.assertEquals(actualMessage, "User created successfully!");
    }



}


