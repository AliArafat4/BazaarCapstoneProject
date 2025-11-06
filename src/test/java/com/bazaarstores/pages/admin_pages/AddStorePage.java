package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

public class AddStorePage extends BasePage {


    private final By nameInput=By.xpath("//*[@id='first-name-column']");
    private final By locationInput=By.xpath("//*[@id='location-id-column']");
    private final By adminSelect=By.xpath("//select[@id=\"admin-column\"]");
    private final By submitBtn=By.xpath("//button[text()='Submit']");
    private final By descriptionInput = By.id("tinymce");
    private final By descriptionFrame = By.id("default_ifr");
    private final By validationMessage = By.xpath("//li[contains(text(),'is required')]");


    public AddStorePage enterName(String name){
        System.out.println(name);
        sendKeys(nameInput,name);
        return this;
    }


    public AddStorePage enterLocation(String location){
        sendKeys(locationInput,location);
        return this;
    }

    public AddStorePage selectAdmin(String admin){
        if(admin.isEmpty()) return this;
        selectByVisibleText(adminSelect,admin);
     return this;
    }


    public AddStorePage submitForm() {
        clickWithJS(submitBtn);
        return this;
    }
    public AddStorePage enterDescription(String description) {

        switchToFrame(descriptionFrame);
        sendKeys(descriptionInput, description);
        switchToDefaultContent();
        return this;
    }

    public boolean isValidationMessageDisplayed() {
    return isDisplayed(validationMessage);
    }


    public AddStorePage fillStoreData(String name, String location, String admin,String description) {
        return this
                .enterName(name)
                .enterLocation(location)
                .selectAdmin(admin)
                .enterDescription(description);
    }
}
