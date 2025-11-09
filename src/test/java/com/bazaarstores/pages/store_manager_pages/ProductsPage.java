package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

import static com.bazaarstores.utilities.Driver.getDriver;

public class ProductsPage extends BasePage {

    private final By addProductButton = By.xpath("//a[normalize-space()='ADD PRODUCT']");
    private final By productsHeader = By.xpath("//h3[normalize-space()='Products']");
    private final By successMsg = By.xpath("//div[@class='toast-title']");

    public ProductsPage clickAddProductButton() {
        click(addProductButton);
        return this;
    }


    public boolean isProductInList(String productName) {
        By productInList = By.xpath("//td[normalize-space()='" + productName + "']");
        return isDisplayed(productInList);
    }

    public ProductsPage clickEditProductButton(String productName) {
        By editButton = By.xpath("//tbody/tr[td[normalize-space(.)='" + productName + "']]/td[6]/button[1]");
        scrollToElement(editButton);
        click(editButton);
        return this;
    }

    public boolean isInProductsPage() {
        return getDriver().getCurrentUrl().contains("products");
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMsg);
    }

    public boolean isEditedProductInPage(String productName, String value) {
//        By productInList = By.xpath("//td[normalize-space()='" + productName + "']");
        By productInList = By.xpath("//tr[td[normalize-space()='" + productName + "'] and td[normalize-space()='" + value + "']]");

        return isDisplayed(productInList);

    }
}