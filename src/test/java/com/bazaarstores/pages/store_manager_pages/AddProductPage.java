package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

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

    private final String iframeName = "default_ifr";

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
        File file = new File("src/test/resources/images/" + image);
        String absolutePath = file.getAbsolutePath();
        sendKeys(productImageInput, absolutePath);
        return this;
    }

    public AddProductPage enterProductDiscount(String discount) {
        sendKeys(productDiscountInput, discount);
        return this;
    }

    public AddProductPage enterProductDescription(String description) {

        getDriver().switchTo().frame(iframeName);
        sendKeys(productDescriptionInput, description);

        getDriver().switchTo().parentFrame();
        return this;
    }

    public AddProductPage clickSubmitButton() {
        click(submitButton);
        return this;
    }

    public boolean isErrorMessageDisplayed(String missingInfo) {
        By errorMessage = By.xpath("//li[contains(text(), 'field is required')]");
        String error = findElement(errorMessage).getText();

        if (error.contains(missingInfo) || missingInfo.equals("all")) {
            return isDisplayed(errorMessage);
        }
        return false;
    }

    public boolean hasErrorMessage(String errorMsg) {
        By errorMessage = By.xpath("//li[contains(text(), '" + errorMsg + "')]");
        return isDisplayed(errorMessage);
    }

//    public boolean takenSKUError(String errorMsg) {
//        By errorMessage = By.xpath("//li[contains(text(), '" + errorMsg + "')]");
//        return isDisplayed(errorMessage);
//    }

    public boolean negativeNumberError(String errorField) {
        By errorMessage = By.xpath("//li[contains(text(), '" + errorField + "')]");
        return isDisplayed(errorMessage);

//        if (error.contains(errorField) || errorField.equals("all")) {
//            return isDisplayed(errorMessage);
//        }
//        return false;

    }

//    public boolean invalidImageFormatErrorMessage(String errorMsg) {
//        By errorMessage = By.xpath("//li[contains(text(), '" + errorMsg + "')]");
//        return isDisplayed(errorMessage);
//    }

    public boolean isPageTitleDisplayed(String pageTitle) {
        String actualTitle = getDriver().getTitle();
        return pageTitle.equals(actualTitle);
    }

    public boolean areAllInputFieldsClickable() {
        return isEnabled(productNameInput) &&
                isEnabled(productPriceInput) &&
                isEnabled(productStockInput) &&
                isEnabled(productSKUInput) &&
                isEnabled(productCategoryInput) &&
                isEnabled(productManufacturerInput) &&
                isEnabled(productImageInput) &&
                isEnabled(productDiscountInput) &&
                isIframeElementEnabled(productDescriptionInput, iframeName) &&
                isEnabled(submitButton);
    }

    public boolean areAllInputFieldsVisible() {
        return isDisplayed(productNameInput) &&
                isDisplayed(productPriceInput) &&
                isDisplayed(productStockInput) &&
                isDisplayed(productSKUInput) &&
                isDisplayed(productCategoryInput) &&
                isDisplayed(productManufacturerInput) &&
                isDisplayed(productImageInput) &&
                isDisplayed(productDiscountInput) &&
                isIframeElementVisible(productDescriptionInput, iframeName) &&
                isDisplayed(submitButton);
    }
}