package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {

    private final By addProductButton = By.xpath("//a[normalize-space()='ADD PRODUCT']");


    public ProductsPage clickAddProductButton() {
        click(addProductButton);
        return this;
    }


    public boolean isProductInList(String productName) {
        By productInList = By.xpath("//td[normalize-space()='" + productName + "']");
        return isDisplayed(productInList);
    }
}