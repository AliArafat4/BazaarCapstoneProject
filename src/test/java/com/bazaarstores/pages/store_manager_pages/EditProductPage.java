package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

import java.io.File;

import static com.bazaarstores.utilities.Driver.getDriver;

public class EditProductPage extends BasePage {

    private final By productNameInput = By.id("name-column");
    private final By productPriceInput = By.id("price-column");
    private final By productStockInput = By.id("stock-column");
    private final By productSKUInput = By.id("sku-column");

    private final By productCategoryInput = By.id("category-column");
    private final By productManufacturerInput = By.id("manufacturer-column");
    private final By productImageInput = By.id("image-url-column");
    private final By productDiscountInput = By.id("discount-column");

    private final By productDescriptionInput = By.id("tinymce");

    private final By submitButton = By.xpath("//button[@type='submit']");

    private final String iframeName = "default_ifr";

    public EditProductPage clearEditedField(String EditedField) {
        switch (EditedField.toLowerCase()) {
            case "name":
                findElement(productNameInput).clear();
                break;
            case "price":
                findElement(productPriceInput).clear();
                break;
            case "stock":
                findElement(productStockInput).clear();
                break;
            case "sku":
                findElement(productSKUInput).clear();
                break;
            case "category", "image":
                break;
            case "manufacturer":
                findElement(productManufacturerInput).clear();
                break;
            case "discount":
                findElement(productDiscountInput).clear();
                break;
            case "description":
                getDriver().switchTo().frame(iframeName);
                findElement(productDescriptionInput).clear();
                getDriver().switchTo().parentFrame();
                break;
        }

        return this;
    }

    public EditProductPage editProduct(String EditedField, String value) {
        switch (EditedField.toLowerCase()) {
            case "name":
                sendKeys(productNameInput, value);
                break;
            case "price":
                sendKeys(productPriceInput, value);
                break;
            case "stock":
                sendKeys(productStockInput, value);
                break;
            case "sku":
                sendKeys(productSKUInput, value);
                break;
            case "category":
                selectByValue(productCategoryInput, value);
                break;
            case "manufacturer":
                sendKeys(productManufacturerInput, value);
                break;
            case "image":
                File file = new File("src/test/resources/images/" + value);
                String absolutePath = file.getAbsolutePath();
                sendKeys(productImageInput, absolutePath);
                break;
            case "discount":
                sendKeys(productDiscountInput, value);
                break;
            case "description":
                getDriver().switchTo().frame(iframeName);
                sendKeys(productDescriptionInput, value);
                getDriver().switchTo().parentFrame();
                break;
        }

        return this;

    }

    public EditProductPage clickSubmitButton(String productName) {
        click(submitButton);
        return this;
    }

    public boolean hasErrorMessage(String errorField) {
        By errorMessage = By.xpath("//li[contains(text(), '" + errorField + "')]");
        return isDisplayed(errorMessage);
    }

}