package com.bazaarstores.pages.customer_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class CustomerFavorite extends BasePage {

    private By productsGrid = By.cssSelector("div.products-grid div.product-card");
    private By heartIconLocator = By.cssSelector("div.product-card:first-child div.favorite.favorite-icon i");

    private String lastFavoritedProductName;

    public void hoverOverFirstProduct() {
        WebElement firstProduct = findElement(productsGrid);
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(firstProduct).perform();
    }


    public void storeFirstProductName() {
        WebElement product = findElement(By.cssSelector("div.products-grid div.product-card:first-child .product-name"));
        lastFavoritedProductName = product.getText();
    }


    public void clickHeartIcon() {
        findElement(heartIconLocator).click();
    }

    public boolean isHeartIconDisplayed() {
        return findElement(heartIconLocator).isDisplayed();
    }

    public String getHeartIconColor() {
        WebElement icon = findElement(heartIconLocator);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(driver -> icon.getCssValue("color").equals("rgba(255, 68, 68, 1)"));
        return icon.getCssValue("color");
    }


    //222
    private By favoriteProductCards = By.cssSelector(".product-card");
    private By favoriteProductNameInCard = By.cssSelector(".product-name");


    public boolean isProductInFavoritesPage() {
        Driver.getDriver().get("https://bazaarstores.com/favorites");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(favoriteProductCards));

        List<WebElement> products = findElements(favoriteProductCards);
        for (WebElement product : products) {
            String name = product.findElement(favoriteProductNameInCard).getText();
            if (name.equals(lastFavoritedProductName)) {
                return true;
            }
        }
        return false;
    }



    //3

    private By alertTitle = By.cssSelector("h2.swal2-title");
    private By alertMessage = By.cssSelector("div.swal2-html-container");
    private By alertButton = By.cssSelector("button.swal2-confirm");

    public String getFullErrorMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertTitle));

        String title = findElement(alertTitle).getText().trim();
        String message = findElement(alertMessage).getText().trim();
        return title + " " + message;
    }

    public void clickCoolButton() {
        findElement(alertButton).click();
    }



    //٤
    private By myFavoritesLink = By.xpath("//a[contains(text(),'My Favorites') or contains(text(),'Favorites')]");

    public void clickMyFavoritesLink() {
        findElement(myFavoritesLink).click();
    }

//5
//5

    public void removeFirstProductFromFavorites() {
        List<WebElement> products = findElements(favoriteProductCards);
        if (!products.isEmpty()) {
            WebElement firstProduct = products.get(0);

            WebElement favDiv = firstProduct.findElement(By.cssSelector("div.fav.favorite-icon"));
            lastFavoritedProductName = favDiv.getAttribute("data-product-name");

            Actions actions = new Actions(Driver.getDriver());
            actions.moveToElement(favDiv).perform();
            favDiv.click();
        }
    }


    public boolean isProductRemovedFromFavorites() {
        Driver.getDriver().get("https://bazaarstores.com/favorites");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> products = findElements(favoriteProductCards);

        if (products.isEmpty()) {
            return true;
        }

        for (WebElement product : products) {
            String name = product.findElement(favoriteProductNameInCard).getText();
            if (name.equals(lastFavoritedProductName)) {
                return false;
            }
        }
        return true;
    }
/// ٦


}


