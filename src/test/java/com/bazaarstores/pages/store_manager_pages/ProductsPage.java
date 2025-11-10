package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

import static com.bazaarstores.utilities.Driver.getDriver;

public class ProductsPage extends BasePage {

    private final By addProductButton = By.xpath("//a[normalize-space()='ADD PRODUCT']");
    //    private final By productsHeader = By.xpath("//h3[normalize-space()='Products']");
    private final By successMsg = By.xpath("//div[@class='toast-title']");


    private final By deleteDialogue = By.xpath("//div[@role='dialog']");
    private final By alertMsg1 = By.xpath("//h2[@id ='swal2-title']");
    private final By alertMsg2 = By.xpath("//div[@id = 'swal2-html-container'] ");
    private final By confirmDeleteButton = By.xpath("//button[normalize-space()='Yes, delete it!']");
    private final By cancelDeleteButton = By.xpath("//button[normalize-space()='Cancel']");


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
        waitUntilElementIsFullyInView(editButton, 5);
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

    public ProductsPage clickDeleteProductButton(String productName) {
        By deleteButton = By.xpath("//tbody/tr[td[normalize-space(.)='" + productName + "']]/td[6]/button[2]");
        scrollToElement(deleteButton);
        waitUntilElementIsFullyInView(deleteButton, 5);
        click(deleteButton);

        return this;
    }

    public boolean isDisplayedDeleteDialogue() {
        return isDisplayed(deleteDialogue);
    }

    public ProductsPage clickConfirmDeleteButton() {
        click(confirmDeleteButton);
        return this;
    }

    public ProductsPage clickCancelDeleteButton() {
        click(cancelDeleteButton);
        return this;
    }

    public boolean alertMessage(String alertMsg) {
        String msg1 = findElement(alertMsg1).getText();
        String msg2 = findElement(alertMsg2).getText();
        return alertMsg.contains(msg1) && alertMsg.contains(msg2);
    }
}