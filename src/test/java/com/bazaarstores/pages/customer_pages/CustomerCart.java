package com.bazaarstores.pages.customer_pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CustomerCart {

    WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public CustomerCart() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart-icon")
    public WebElement cartIcon;

    @FindBy(css = ".cart-dropdown")
    public WebElement cartDropdown;

    @FindBy(css = ".cart-item")
    public List<WebElement> cartItems;

    @FindBy(css = ".cart-subtotal span:last-child")
    public WebElement cartSubtotal;

    @FindBy(css = ".cart-button.view-cart")
    public WebElement viewCartButton;

    @FindBy(css = ".product-flex")
    public List<WebElement> productsInCartPage;

    // Check if full cart page loaded
    public boolean isCartPageLoaded() {
        return !productsInCartPage.isEmpty();
    }
//
//    // Hover over cart icon
//    public void hoverOverCartIcon() {
//        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
//        actions.moveToElement(cartIcon).perform();
//        wait.until(ExpectedConditions.visibilityOf(cartDropdown));
//
//        // Wait for items if cart not empty
//        List<WebElement> items = driver.findElements(By.cssSelector(".cart-item"));
//        if (!items.isEmpty()) {
//            wait.until(ExpectedConditions.visibilityOfAllElements(items));
//        } else {
//            // Short wait if cart empty
//            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
//            shortWait.until(d -> true);
//        }
//    }


    //new
    public void hoverOverCartIcon() {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(cartIcon).perform();
        wait.until(ExpectedConditions.visibilityOf(cartDropdown));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wait for items if cart not empty
        List<WebElement> items = driver.findElements(By.cssSelector(".cart-item"));
        if (!items.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfAllElements(items));
        }
    }


    // Check mini cart visibility
    public boolean isMiniCartVisible() {
        try {
            return cartDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

//    // Click View Cart button
//    public void clickViewCartButton() {
//        ((org.openqa.selenium.JavascriptExecutor) driver)
//                .executeScript("arguments[0].scrollIntoView(true);", viewCartButton);
//        viewCartButton.click();
//        wait.until(ExpectedConditions.visibilityOfAllElements(productsInCartPage));
//    }

    //new
    public void clickViewCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", viewCartButton); // استخدم JS click
        wait.until(ExpectedConditions.visibilityOfAllElements(productsInCartPage));
    }


    // Remove first item from mini cart
    public void removeFirstItem() {
        List<WebElement> itemsBefore = driver.findElements(By.cssSelector(".cart-item"));
        if (!itemsBefore.isEmpty()) {
            WebElement firstItemRemoveBtn = itemsBefore.get(0).findElement(By.cssSelector(".remove-item"));
            wait.until(ExpectedConditions.elementToBeClickable(firstItemRemoveBtn)).click();
            wait.until(d -> driver.findElements(By.cssSelector(".cart-item")).size() < itemsBefore.size());
        }
    }

    // Get number of items in mini cart
    public int getNumberOfItemsInCart() {
        return cartItems.size();
    }

    // Get cart subtotal after removal
    public double getCartSubtotalAfterRemoval() {
        hoverOverCartIcon(); // ensure mini cart is open
        try {
            if (cartItems.isEmpty()) return 0.0;
            String subtotalText = cartSubtotal.getText().replace("$", "").trim();
            if (subtotalText.isEmpty() || subtotalText.equals("0.00")) return 0.0;
            return Double.parseDouble(subtotalText);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
