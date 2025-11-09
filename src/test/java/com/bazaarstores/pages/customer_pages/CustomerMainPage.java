package com.bazaarstores.pages.customer_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;




public class CustomerMainPage {

    private By products = By.xpath("//div[@class='products-grid']");
    private By productName = By.xpath("//h3[@class='product-name']");
    private By productPrice = By.xpath("//div[@class='product-price']");
    private By productDescription = By.xpath("//p[@class='product-description']");
    private By productImage = By.xpath("//img[@class='product-image']");


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


}




