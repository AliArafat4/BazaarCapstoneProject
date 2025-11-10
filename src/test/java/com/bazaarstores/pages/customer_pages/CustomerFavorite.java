package com.bazaarstores.pages.customer_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CustomerFavorite extends BasePage {

    private By favoriteProductCards = By.cssSelector(".product-card");
    private By favoriteProductNameInCard = By.cssSelector(".product-name");
    private By alertTitle = By.cssSelector("h2.swal2-title");
    private By alertMessage = By.cssSelector("div.swal2-html-container");
    private By alertButton = By.cssSelector("button.swal2-confirm");
    private By myFavoritesLink = By.xpath("//a[contains(text(),'My Favorites') or contains(text(),'Favorites')]");
    private String lastFavoritedProductName;

    // #Helper Methods
    private WebElement getProductByXPath(int position) {
        String xpath = "(//div[@class='products-grid']//div[contains(@class,'product-card')])[" + position + "]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    private WebElement getHeartIcon(WebElement product) {
        return product.findElement(By.cssSelector("div.favorite.favorite-icon i"));
    }

    private String getProductName(WebElement product) {
        return product.findElement(By.cssSelector(".product-name")).getText();
    }

    // #Hover and Click
    public void hoverAndClickHeart(int position) {
        WebElement product = getProductByXPath(position);
        new Actions(Driver.getDriver()).moveToElement(product).perform();
        lastFavoritedProductName = getProductName(product);
        getHeartIcon(product).click();
    }

    // #Check Heart Icon
    public boolean isHeartIconDisplayed(int position) {
        WebElement product = getProductByXPath(position);
        return getHeartIcon(product).isDisplayed();
    }

    public String getHeartIconColor(int position) {
        WebElement heart = getHeartIcon(getProductByXPath(position));
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5))
                .until(driver -> heart.getCssValue("color").equals("rgba(255, 68, 68, 1)"));
        return heart.getCssValue("color");
    }

    // #Favorites Page Validation
    public boolean isProductInFavoritesPage() {
        Driver.getDriver().get("https://bazaarstores.com/favorites");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(favoriteProductCards));

        List<WebElement> products = findElements(favoriteProductCards);
        for (WebElement product : products) {
            if (product.findElement(favoriteProductNameInCard).getText().equals(lastFavoritedProductName)) {
                return true;
            }
        }
        return false;
    }

    // #Removed Product Validation
    public boolean isProductRemovedFromFavorites() {
        Driver.getDriver().get("https://bazaarstores.com/favorites");
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(favoriteProductCards));

        List<WebElement> products = findElements(favoriteProductCards);
        if (products.isEmpty()) return true;

        for (WebElement product : products) {
            if (product.findElement(favoriteProductNameInCard).getText().equals(lastFavoritedProductName)) {
                return false;
            }
        }
        return true;
    }

    // #Alerts
    public String getFullErrorMessage() {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(alertTitle));
        String title = findElement(alertTitle).getText().trim();
        String message = findElement(alertMessage).getText().trim();
        return title + " " + message;
    }

    public void clickCoolButton() {
        findElement(alertButton).click();
    }

    // #My Favorites Navigation
    public void clickMyFavoritesLink() {
        findElement(myFavoritesLink).click();
    }

    // #Remove from Favorites
    public void removeProductFromFavorites(int position) {
        WebElement product = getProductByXPath(position);
        WebElement favDiv = product.findElement(By.cssSelector("div.fav.favorite-icon"));
        lastFavoritedProductName = getProductName(product);
        new Actions(Driver.getDriver()).moveToElement(favDiv).perform();
        favDiv.click();
    }
}
