package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminUsersPage extends BasePage {

    private final By usersButton = By.xpath("//a[@href='https://bazaarstores.com/users' and contains(@class, 'sidebar-link')]");
    private final By addUsersButton = By.cssSelector("a[href*='create']");
    private final By searchBar = By.xpath("//input[@name='email']");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By editButton = By.xpath("//following-sibling::td//a[contains(@href, '/users/')]/i[contains(@class, 'bi-pencil-square')]");
    private final By deleteButton = By.xpath("//i[contains(@class, 'bi-trash3')]/ancestor::button");
    private final By confirmDeleteButton = By.xpath("//button[.='Yes, delete it!']");
    private final By cancelDeleteButton = By.xpath("//button[.='Cancel']");
    private final By failureToast = By.xpath("//div[contains(text(),'You cant delete a admin role users!')]");
    private final By dashboardButton = By.xpath("//a[@href='https://bazaarstores.com' and contains(@class, 'sidebar-link')]");
    private final By avtarImg = By.xpath("//img[@id='avatar']");
    private final By logoutButton = By.xpath("//a[@href='https://bazaarstores.com/logout']");



    public AdminUsersPage goToUsersPage (){
        clickWithJS(usersButton);
        return this;
    }

    public AdminUsersPage clickAddUsers() {
        click(addUsersButton);
        return this;
    }

    public boolean addUsersIsVisible(){
        return isDisplayed(addUsersButton);
    }


    public boolean isUserInList(String userEmail) {
        findElement(searchBar).sendKeys(userEmail);
        click(searchButton);
        By userCell = By.xpath("//td[normalize-space()='" + userEmail + "']");
        return isDisplayed(userCell);
    }

    public boolean isAdminInList(String userEmail) {
        findElement(searchBar).sendKeys(userEmail);
        click(searchButton);
        By userCell = By.xpath("//td[normalize-space()='" + userEmail + "']");
        return isDisplayed(userCell);
    }



    public void locateUser(String userEmail){
        findElement(searchBar).sendKeys(userEmail);
        click(searchButton);

    }

    public void clickEditButton(){
        click(editButton);
    }

    public boolean editUsersIsVisible() {
        return isDisplayed(editButton);
    }

    public void clickDeleteButton(){
        click(deleteButton);
    }

    public void clickConfirmDeleteButton(){
        click(confirmDeleteButton);
    }

    public void clickCancelDeleteButton(){
        click(cancelDeleteButton);
    }

    public boolean deleteUsersIsVisible() {
        return isDisplayed(deleteButton);
    }

    public boolean deleteConfirmationAlertIsVisible() {
        return isDisplayed(confirmDeleteButton) && isDisplayed(cancelDeleteButton);
    }

    public String getFailureMessage() {
        return getText(failureToast);
    }

    public void logout() {
        clickWithJS(dashboardButton);
        click(avtarImg);
        click(logoutButton);
    }
}
