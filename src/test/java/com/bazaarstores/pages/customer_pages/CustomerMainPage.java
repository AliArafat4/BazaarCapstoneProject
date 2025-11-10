package com.bazaarstores.pages.customer_pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;

public class CustomerMainPage {

    private By products = By.xpath("//div[@class='products-grid']");
    private By productName = By.xpath("//h3[@class='product-name']");
    private By productPrice = By.xpath("//div[@class='product-price']");
    private By productDescription = By.xpath("//p[@class='product-description']");
    private By productImage = By.xpath("//img[@class='product-image']");

    private By cartCount = By.xpath("//span[@class='cart-count']");

    private String addToCart = "(//button[@class='add-to-cart'])[%s]";
    private String cartItemName = "(//div[@class='cart-item-name'])[%s]";
    private String deleteProductFromCart = "(//button[@class='remove-item'])[%s]";



    public Boolean productsIsDisplayed(){

        return Driver.getDriver().findElement(products).isDisplayed();

    }


    public Boolean productsNamesIsVisible(){

        return Driver.getDriver().findElement(productName).isDisplayed();

    }

    public Boolean productsPricesIsVisible(){

        return Driver.getDriver().findElement(productPrice).isDisplayed();

    }

    public Boolean productsImagesIsVisible(){

        return Driver.getDriver().findElement(productImage).isDisplayed();

    }

    public Boolean productsDescriptionIsVisible(){

        return Driver.getDriver().findElement(productDescription).isDisplayed();

    }

    public CustomerMainPage clickProduct(){
        Driver.getDriver().findElement(productName).click();
        return this;
    }


    public CustomerMainPage addProductToCart(int i)  {

        for(int j=1; j<=i; j++) {
            Driver.getDriver().findElement(By.xpath(String.format(addToCart, j))).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public Boolean getCartItemName(int i)  {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(Driver.getDriver().findElement(cartCount)).perform();

        return Driver.getDriver().findElement(By.xpath(String.format(cartItemName, i))).isDisplayed();

    }

    public CustomerMainPage removeItem(int i)  {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(Driver.getDriver().findElement(cartCount)).perform();

        Driver.getDriver().findElement(By.xpath(String.format(deleteProductFromCart, i))).click();
        return this;

    }

    public int getCartCount(){

        return Integer.parseInt(Driver.getDriver().findElement(cartCount).getText());
    }







}

