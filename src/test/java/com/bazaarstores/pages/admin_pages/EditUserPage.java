package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    Faker faker = new Faker();

    public void editName() {
        //findElement(email).clear();
        findElement(name).sendKeys(faker.name().fullName());
    }

    public void editRole() {
        String role = findElement(this.role).getText().trim();

        if (role.equalsIgnoreCase("Customer")) {
            selectByVisibleText(this.role, "Admin");
        } else if (role.equalsIgnoreCase("Admin")) {
            selectByVisibleText(this.role, "Customer");
        } else {
            // fallback to Customer if role is something else or unexpected
            selectByVisibleText(this.role, "Customer");
        }

    }

    public void editEmail() {
        findElement(email).clear();
        findElement(email).sendKeys(faker.internet().emailAddress());
    }

    public void enterInvalidEmail(String invalidEmail) {
        findElement(email).clear();
        findElement(email).sendKeys(invalidEmail);
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
