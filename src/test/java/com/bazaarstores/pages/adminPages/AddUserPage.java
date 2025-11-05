package com.bazaarstores.pages.adminPages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.ConfigReader;
import org.openqa.selenium.By;

public class AddUserPage extends BasePage {

    private final By name = By.id("first-name-column");
    private final By email = By.id("email-id-column");
    private final By role = By.id("role-id-column");
    private final By password = By.xpath("//input[@name='password']");
    private final By passwordConfirmation = By.xpath("//input[@name='password_confirmation']");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By successToast = By.xpath("//div[contains(text(),'User created successfully')]");

    // Faker faker = new Faker();

    public AddUserPage enterName(String name) {
        sendKeys(this.name, name);
        return this;
    }

    public AddUserPage enterEmail(String email) {
        sendKeys(this.email, email);
        return this;
    }

    public AddUserPage selectRole(String role) {
        selectByVisibleText(this.role, role);
        return this;
    }

    public AddUserPage enterPassword() {
        sendKeys(this.password, ConfigReader.getDefaultPassword());
        return this;
    }

    public AddUserPage enterPasswordConfirmation() {
        sendKeys(this.passwordConfirmation, ConfigReader.getDefaultPassword());
        return this;
    }

    public AddUserPage clickSubmit() {
        click(submitButton);
        return this;
    }


    public AddUserPage fillUserData(String name, String email, String role) {
        return this
                .enterName(name)
                .enterEmail(email)
                .selectRole(role)
                .enterPassword()
                .enterPasswordConfirmation();
    }


    public String getSuccessMessage() {
        return getText(successToast);
    }
}