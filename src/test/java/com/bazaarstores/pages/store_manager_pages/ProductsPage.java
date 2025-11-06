package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

import static com.bazaarstores.utilities.Driver.getDriver;

public class ProductsPage extends BasePage {

    private final By addProductButton = By.xpath("//a[normalize-space()='ADD PRODUCT']");
    private final By productsHeader = By.xpath("//h3[normalize-space()='Products']");


    public ProductsPage clickAddProductButton() {
        click(addProductButton);
        return this;
    }


    public boolean isProductInList(String productName) {
        By productInList = By.xpath("//td[normalize-space()='" + productName + "']");
        return isDisplayed(productInList);
    }

    public boolean isInProductsPage() {
        return getDriver().getCurrentUrl().contains("products");
    }
}