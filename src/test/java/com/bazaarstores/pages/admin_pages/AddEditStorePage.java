package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

import static com.bazaarstores.utilities.Driver.getDriver;
import static com.bazaarstores.stepDefinitions.admin_steps.EditStoreSteps.adminId;
import static org.junit.Assert.fail;

public class AddEditStorePage extends BasePage {


    private final By nameInput=By.xpath("//*[@id='first-name-column']");
    private final By locationInput=By.xpath("//*[@id='location-id-column']");
    private final By adminSelect=By.xpath("//select[@id=\"admin-column\"]");
    private final By submitBtn=By.xpath("//button[text()='Submit']");
    private final By descriptionInput = By.id("tinymce");
    private final By descriptionFrame = By.id("default_ifr");
    private final By validationMessage = By.xpath("//li[contains(text(),'is required')]");
    private final String adminOption = "//select[@id='admin-column']/option[contains(text(),'%s')]";


    public AddEditStorePage enterName(String name){
        sendKeys(nameInput,name);
        return this;
    }

    public AddEditStorePage clearName(){
        getDriver().findElement(nameInput).clear();
        return this;
    }


    public AddEditStorePage enterLocation(String location){
        sendKeys(locationInput,location);
        return this;
    }

    public AddEditStorePage selectAdmin(String admin){
        if(admin.isEmpty()) return this;
        selectByVisibleText(adminSelect,admin);
        adminId=getAttribute(By.xpath(String.format(adminOption,admin)),"value");
     return this;
    }


    public AddEditStorePage submitForm() {
        clickWithJS(submitBtn);
        return this;
    }
    public AddEditStorePage enterDescription(String description) {

        switchToFrame(descriptionFrame);
        sendKeys(descriptionInput, description);
        switchToDefaultContent();
        return this;
    }

    public boolean isValidationMessageDisplayed() {
    return isDisplayed(validationMessage);
    }


    public AddEditStorePage fillStoreData(String name, String location, String admin, String description) {
        return this
                .enterName(name)
                .enterLocation(location)
                .selectAdmin(admin)
                .enterDescription(description);
    }

    public AddEditStorePage clearField(String field) {

        switch (field.toLowerCase()) {
            case "name":
                clearName();
                break;

            case "location":
                clearLocation();
                break;

            case "admin":
                clearAdmin();
                break;

            case "description":
                clearDescription();
                break;

            case "all fields":
                clearAdmin().clearName().
                        clearDescription()
                        .clearLocation();
                break;

            default:
                fail();


        }
        return this;
    }

    private AddEditStorePage clearDescription() {
        switchToFrame(descriptionFrame);
        getDriver().findElement(descriptionInput).clear();
        switchToDefaultContent();
        return this;
    }

    private AddEditStorePage clearAdmin() {
        selectByIndex(adminSelect,0);
        return this;

    }

    private AddEditStorePage clearLocation() {
        getDriver().findElement(locationInput).clear();
        return this;
    }

    public void updateOneField(String field, String value) {
        switch (field.toLowerCase()) {
            case "name":
               enterName(value);
                break;

            case "location":
               enterLocation(value);
                break;

            case "admin":
                selectAdmin(value);
                break;

            case "description":
               enterDescription(value);
                break;

            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }

    }
}
