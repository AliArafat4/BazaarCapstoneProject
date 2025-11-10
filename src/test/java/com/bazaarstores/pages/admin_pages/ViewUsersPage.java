package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;

public class ViewUsersPage {


    private By usersTable = By.xpath("//table[@class='table table-bordered mb-0']");
    private By usersNames = By.xpath("//table//tr/td[1]");
    private By usersEmails = By.xpath("//table//tr/td[2]");
    private By usersActions = By.xpath("//table//tr/td[3]");

    private By searchBar =By.xpath("//input[@type='text']");
    private By searchButton =By.xpath("//button[@type='submit']");

    private By errorMessage = By.xpath("//*[text()='No users found.']");




    public boolean getUsersTable() {
        return Driver.getDriver().findElement(usersTable).isDisplayed();
    }

    public boolean isUsersNamesVisible() {
        return Driver.getDriver().findElement(usersNames).isDisplayed();
    }


    public boolean isUsersEmailsVisible() {
        return Driver.getDriver().findElement(usersEmails).isDisplayed();
    }

    public boolean getUsersActions() {
        return Driver.getDriver().findElement(usersActions).isDisplayed();
    }

    public ViewUsersPage typeInSearchBar(String text) {
        Driver.getDriver().findElement(searchBar).sendKeys(text);
        return this;
    }


    public String getSearchBarAttribute() {
        return Driver.getDriver().findElement(searchBar).getAttribute("value");
    }

    public String getUserEmail() {
        return Driver.getDriver().findElement(usersEmails).getText();
    }

    public String getErrorMessage() {
        return Driver.getDriver().findElement(errorMessage).getText();
    }

    public ViewUsersPage clickSearchButton() {
        Driver.getDriver().findElement(searchButton).click();
        return this;
    }

}
