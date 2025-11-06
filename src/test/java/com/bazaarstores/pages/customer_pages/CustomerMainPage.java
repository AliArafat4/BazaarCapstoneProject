package com.bazaarstores.pages.customer_pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class CustomerMainPage {

    private By products = By.xpath("//div[@class='products-grid']");
    private By productName = By.xpath("//h3[@class='product-name']");
    private By productPrice = By.xpath("//div[@class='product-price']");
    private By productDescription = By.xpath("//p[@class='product-description']");
    private By productImage = By.xpath("//img[@class='product-image']");

    private By cartCount = By.xpath("//span[@class='cart-count'S");

    private String addToCart = "(//button[@class='add-to-cart'])['%s']";
    private String cartItemName = "(//div[@class='cart-item-name'])['%s'] ";



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


    public CustomerMainPage addProductToCart(int i){

        for(int j=1; j<=i; j++) {
            Driver.getDriver().findElement(By.xpath(String.format(addToCart, j))).click();
        }
        return this;
    }



}

