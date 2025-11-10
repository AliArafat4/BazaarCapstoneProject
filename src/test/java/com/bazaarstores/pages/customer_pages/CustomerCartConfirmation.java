package com.bazaarstores.pages.customer_pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerCartConfirmation {

    private WebDriver driver = Driver.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //Selectors
    private By cartIcon = By.xpath("//div[contains(@class,'cart-icon')]");
    private By viewCartButton = By.xpath("//button[contains(@class,'view-cart')]");
    private By confirmCartButton = By.xpath("//button[@id='clear-all']");
    private By swalTitle = By.xpath("//h2[contains(@class,'swal2-title')]");
    private By swalMessage = By.xpath("//div[contains(@class,'swal2-html-container')]");
    private By okButton = By.xpath("//button[contains(@class,'swal2-confirm')]");

    // Actions
    public void hoverOverCartIcon() {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(driver.findElement(cartIcon)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton));
    }

    public void clickViewCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
    }

    public void clickConfirmCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmCartButton)).click();
    }

    public boolean isViewCartButtonVisible() {
        try {
            return driver.findElement(viewCartButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //SweetAlert2 Methods
    public String getFullSuccessMessage() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(swalTitle));
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(swalMessage));
            return title.getText() + " " + message.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isOkButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(okButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOkButton() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }
}
