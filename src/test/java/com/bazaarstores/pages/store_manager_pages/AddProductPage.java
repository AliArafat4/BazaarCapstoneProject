package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static com.bazaarstores.utilities.Driver.getDriver;

public class AddProductPage extends BasePage {

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


    public AddProductPage enterProductName(String name) {
        sendKeys(productNameInput, name);

        return this;
    }

    public AddProductPage enterProductPrice(String price) {
        sendKeys(productPriceInput, price);
        return this;
    }

    public AddProductPage enterProductStoke(String stoke) {
        sendKeys(productStockInput, stoke);
        return this;
    }

    public AddProductPage enterProductSKU(String sku) {
        sendKeys(productSKUInput, sku);
        return this;
    }

    public AddProductPage selectProductCategory(String category) {
        switch (category) {
            case "Electronics", "Books", "Clothing":
                selectByVisibleText(productCategoryInput, category);
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
        return this;
    }

    public AddProductPage enterProductManufacturer(String manufacturer) {
        sendKeys(productManufacturerInput, manufacturer);
        return this;
    }

    public AddProductPage enterProductImage(String image) {
        File file = new File("src/test/resources/images/book.png");
        String absolutePath = file.getAbsolutePath();
        sendKeys(productImageInput, absolutePath);
        return this;
    }

    public AddProductPage enterProductDiscount(String discount) {
        sendKeys(productDiscountInput, discount);
        return this;
    }

    public AddProductPage enterProductDescription(String description) {

        getDriver().switchTo().frame("default_ifr");
        sendKeys(productDescriptionInput, description);

        getDriver().switchTo().parentFrame();
        return this;
    }

    public AddProductPage clickSubmitButton() {
        click(submitButton);
        return this;
    }


}