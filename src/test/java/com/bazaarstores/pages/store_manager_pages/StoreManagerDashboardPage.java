package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class StoreManagerDashboardPage extends BasePage {


    private final By storeManagerHeader = By.xpath("//span[normalize-space()='Store Manager']");
    private final By productsButton = By.xpath("//span[normalize-space()='Products']");


    public Boolean isStoreManagerHeaderDisplayed() {

        return isDisplayed(storeManagerHeader);
    }

    public StoreManagerDashboardPage clickProductsButton() {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", Driver.getDriver().findElement(productsButton));

//        click(productsButton);
        return this;

    }


}