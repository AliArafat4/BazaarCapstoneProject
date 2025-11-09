package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class AddUserPage extends BasePage {

    private final By name = By.id("first-name-column");
    private final By email = By.id("email-id-column");
    private final By role = By.id("role-id-column");
    private final By password = By.xpath("//input[@name='password']");
    private final By passwordConfirmation = By.xpath("//input[@name='password_confirmation']");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By successToast = By.xpath("//div[contains(text(),'User created successfully')]");
    private final By pageTitle = By.xpath("//h4[@class='card-title'][.='Add Users']");

    private final By validationListItems = By.cssSelector("div.alert.alert-danger ul li");



    public AddUserPage enterName(String name) {
        sendKeys(this.name, name);
        return this;
    }

    public AddUserPage enterEmail(String email) {
        sendKeys(this.email, email);
        return this;
    }

    public AddUserPage selectRole(String role) {

        if (role == null || role.trim().isEmpty()) {
            // do nothing (useful for negative tests)
            return this;
        }
        selectByVisibleText(this.role, role);
        return this;
    }

    public AddUserPage enterPassword(String password) {
        sendKeys(this.password, password);
        return this;
    }

    public AddUserPage enterPasswordConfirmation(String password) {
        sendKeys(this.passwordConfirmation, password);
        return this;
    }

    public AddUserPage clickSubmit() {
        click(submitButton);
        return this;
    }


    public AddUserPage fillUserData(String name, String email, String role,String password,String ConfirmPassword) {
        return this
                .enterName(name)
                .enterEmail(email)
                .selectRole(role)
                .enterPassword(password)
                .enterPasswordConfirmation(ConfirmPassword);
    }


    public String getSuccessMessage() {
        return getText(successToast);
    }

    public List<String> getAllValidationMessages() {

        List<WebElement> elements;

        try {
            waitForElementToBeVisible(validationListItems);
            elements = findElements(validationListItems);
        } catch (Exception e) {
            // No elements visible or alert not present yet
            return List.of();
        }

        return elements.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());

    }

    public boolean isOnAddUserPage() {
        return isDisplayed(pageTitle) ;
    }

    public boolean submitButtonIsVisible(){
        return isDisplayed(submitButton);
    }



}