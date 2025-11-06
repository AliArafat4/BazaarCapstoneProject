package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

public class StoreManagerDashboardPage extends BasePage {


    private final By dashboardButton = By.xpath("//span[normalize-space()='Dashboard']");
    private final By productsButton = By.xpath("//span[normalize-space()='Products']");
    private final By successMsg = By.xpath("//div[@class='toast-title']");

    public Boolean isDashboardButtonDisplayed() {
        return isDisplayed(dashboardButton);
    }

    public StoreManagerDashboardPage clickProductsButton() {
        click(productsButton);
        return this;

    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMsg);
    }


}