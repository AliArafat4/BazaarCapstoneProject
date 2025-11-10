package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class EditUserPage extends BasePage {
    private final By name = By.id("first-name-column");
    private final By email = By.id("email-id-column");
    private final By role = By.id("role-id-column");
    private final By password = By.xpath("//input[@name='password']");
    private final By passwordConfirmation = By.xpath("//input[@name='password_confirmation']");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By successToast = By.xpath("//div[contains(text(),'User updated successfully')]");
    private final By pageTitle = By.xpath("//h4[@class='card-title'][.='Edit Users']");

    //Faker faker = new Faker();

    public static String newName = Faker.instance().name().fullName();
    public static String enteredEmail = Faker.instance().internet().emailAddress();
    public static String newRole;

    public void editName() {
        findElement(name).clear();
        findElement(name).sendKeys(newName);
    }

    public void editRole() {
        WebElement roleDropdown = findElement(this.role);
        Select select = new Select(roleDropdown);

        String currentRole = select.getFirstSelectedOption().getText().trim();

        if (currentRole.equalsIgnoreCase("Customer")) {
            select.selectByVisibleText("Admin");  // match exactly what is in HTML
            newRole = "Admin";
        } else if (currentRole.equalsIgnoreCase("Admin")) {
            select.selectByVisibleText("Customer");  // match exactly what is in HTML
            newRole = "Customer";
        } else {
            select.selectByVisibleText("Customer");
            newRole = "Customer";
        }

    }

    public void editEmail() {
        findElement(email).clear();
        findElement(email).sendKeys(enteredEmail);
    }

    public void enterInvalidEmail(String invalidEmail) {
        findElement(email).clear();
        findElement(email).sendKeys(invalidEmail);
    }

    public void enterPassword() {
        findElement(password).sendKeys("Pass@12345");
    }

    public void enterPasswordConfirmation() {
        findElement(passwordConfirmation).sendKeys("Pass@12345");
    }

    public void enterInvalidPasswordConfirmation() {
        findElement(passwordConfirmation).sendKeys("Pass$$1234$");
    }

    public EditUserPage clickSubmit() {
        click(submitButton);
        return this;
    }

    public String getSuccessMessage() {
        return getText(successToast);
    }


    public String getErrorMessage() {
        try {
            WebElement element = findElement(email);
            String message = element.getAttribute("validationMessage");
            if (message != null && !message.isEmpty()) {
                return message;
            }
        } catch (Exception ignored) {
            // skip if not found
        }
        return null;
    }


    public boolean isOnEditUserPage() {
        return isDisplayed(pageTitle) ;
    }



}
