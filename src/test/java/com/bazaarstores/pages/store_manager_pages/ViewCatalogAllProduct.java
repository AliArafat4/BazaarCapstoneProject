package com.bazaarstores.pages.store_manager_pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ViewCatalogAllProduct {

    WebDriver driver;

    public ViewCatalogAllProduct() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    // Sidebar link for "Products"
    public WebElement getProductsSidebarLink() {
        return driver.findElement(By.xpath("//a[contains(.,'Products')]"));
    }

    // Click on "Products" link in sidebar
    public void clickProductsFromSidebar() {
        getProductsSidebarLink().click();
    }

    // Verify that Products page is displayed
    public boolean isProductsPageDisplayed() {
        try {
            WebElement productsHeader = driver.findElement(By.xpath("//h3[text()='Products']"));
            return productsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean areAllProductColumnsVisible() {
        try {
            List<WebElement> headers = driver.findElements(By.xpath("//table[contains(@class,'table-bordered')]/thead/tr/th"));

            String[] expectedColumns = {"NAME", "PRICE", "STOCK", "CATEGORY", "IMAGE", "ACTION"};

            if (headers.size() != expectedColumns.length) return false;

            for (int i = 0; i < expectedColumns.length; i++) {
                String headerText = headers.get(i).getText().trim();
                if (!headerText.equalsIgnoreCase(expectedColumns[i])) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //Verify that each product entry displays details
    public boolean areAllProductDetailsVisible() {
        try {
            List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'table-bordered')]/tbody/tr"));

            if (rows.isEmpty()) return false;

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() != 6) return false;
                // Name, Price, Stock, Category
                for (int i = 0; i <= 3; i++) {
                    String text = cells.get(i).getText().trim();
                    if (text.isEmpty()) return false;
                }
                WebElement img = cells.get(4).findElement(By.tagName("img"));
                if (!img.isDisplayed() || img.getAttribute("src").isEmpty()) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
