package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.stepDefinitions.admin_steps.EditUserSteps;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

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
       // click(editButton);
       // ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", Driver.getDriver().findElement(editButton));

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        // 1) wait for the row that contains the email
        By rowBy = By.xpath("//td[normalize-space()='" + EditUserSteps.usersEmail + "']/parent::tr");
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(rowBy));

        // 2) inside that row locate the anchor (not the <i>) — clicking anchor is more reliable
        By editAnchorRelative = By.xpath(".//a[contains(@href,'/users/') and .//i[contains(@class,'bi-pencil-square')]]");

        // 3) re-find the edit anchor from the fresh row and wait until clickable
        WebElement editAnchor = wait.until(driver1 -> {
            try {
                WebElement e = row.findElement(editAnchorRelative);
                return (e.isDisplayed() && e.isEnabled()) ? e : null;
            } catch (StaleElementReferenceException | NoSuchElementException ex) {
                return null; // keep waiting
            }
        });

        // 4) click — with small try/catch to recover if stale happens at last moment
        try {
            editAnchor.click();
        } catch (StaleElementReferenceException ex) {
            WebElement freshRow = Driver.getDriver().findElement(rowBy);
            freshRow.findElement(editAnchorRelative).click();
        }

    }

    public boolean editUsersIsVisible() {
        return isDisplayed(editButton);
    }

    public void clickDeleteButton(){
        //click(deleteButton);
        //((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", Driver.getDriver().findElement(deleteButton));

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", deleteBtn);
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
