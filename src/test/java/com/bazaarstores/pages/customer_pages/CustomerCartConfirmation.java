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
//    public void hoverOverCartIcon() {
//        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
//        actions.moveToElement(driver.findElement(cartIcon)).perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton));
//    }


    public void hoverOverCartIcon() {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        WebElement cart = driver.findElement(cartIcon);
        actions.moveToElement(cart).perform();
        // JavaScript click ممكن تحط هنا بدل الانتظار التقليدي
        wait.until(ExpectedConditions.presenceOfElementLocated(viewCartButton));
    }


//    public void clickViewCartButton() {
//        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
//    }

    public void clickViewCartButton() {
        WebElement viewCart = wait.until(ExpectedConditions.presenceOfElementLocated(viewCartButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCart);
    }

//    public void clickConfirmCartButton() {
//        wait.until(ExpectedConditions.elementToBeClickable(confirmCartButton)).click();
//    }

    public void clickConfirmCartButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(confirmCartButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
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

//    public void clickOkButton() {
//        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
//    }

public void clickOkButton() {
    WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(okButton));
    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
}

}
