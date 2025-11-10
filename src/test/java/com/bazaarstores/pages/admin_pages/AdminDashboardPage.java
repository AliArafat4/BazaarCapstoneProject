package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminDashboardPage extends BasePage {

    private final By storesLink = By.xpath("//a/span[text()='Store']");
    private final By storesTable = By.xpath("//table[contains(@class,'table')]");
    private final By tableColumns = By.xpath("//table[contains(@class,'table')]/thead//th");
    private final By editStoreBtn = By.xpath("//table[contains(@class,'table')]/tbody//td/button//a");
    private final By deleteStoreBtn = By.xpath("//table[contains(@class,'table')]/tbody//td/button[contains(@onClick,\"confirmDelete\")]");
    private final By addStoreBtn = By.xpath("//a[text()='ADD STORE']");
    private final By successToast = By.xpath("//div[contains(text(),'Store created successfully')]");
    private final By updateSuccessToast = By.xpath("//div[contains(text(),'Store updated successfully')]");
    private final By deleteSuccessToast = By.xpath("//div[contains(text(),'Store deleted successfully')]");
    private final By errorToast = By.xpath("//div[contains(text(),'Store creation failed')]");
    private final By updateErrorToast = By.xpath("//div[contains(text(),'Store update failed')]");
    private final By deleteDialog = By.xpath("//div[@aria-labelledby='swal2-title']");
    private final By confirmDelete = By.xpath("//button[text()='Yes, delete it!']");
    private final By cancelDelete = By.xpath("//button[text()='Cancel']");
    private String storeLocator = "//table//tbody//tr[td[contains(normalize-space(.),'%s')]]";
    private String deleteStoreByName = "//td[text()='%s']/following-sibling::*[3]/button[contains(@onclick,\"confirmDelete\")]";
    private String updateStoreByName = "//td[text()='%s']/following-sibling::*[3]/button/a";

    private final By usersButton = By.xpath("//span[text()='Users']"); // Youmna's addition

    private final String[] cloumns = {"Name", "Description", "Location", "Admin Name", "Actions"};

    public AdminDashboardPage clickStoresLink() {
        clickWithJS(storesLink);
        return this;

    }

    public AddEditStorePage clickAddStore() {
        click(addStoreBtn);
        return new AddEditStorePage();
    }

    public AddEditStorePage clickEditStore(String name) {
        clickWithJS(By.xpath(String.format(updateStoreByName, name)));
        return new AddEditStorePage();
    }

    public AdminDashboardPage clickDeleteStore(String name) {
        clickWithJS(By.xpath(String.format(deleteStoreByName, name)));
        return this;
    }

    public boolean isStoresTableDisplayed() {
        return isDisplayed(storesTable);
    }

    public boolean verifyAllColumnsDisplayed() {
        List<WebElement> columns = findElements(tableColumns);
        if (columns.size() < 5) {
            return false;
        } else {
            for (int i = 0; i < 5; i++) {
                if (!columns.get(i).getText().equalsIgnoreCase(cloumns[i]))
                    return false;
            }
            return true;
        }
    }

    public boolean verifyActionsBtnsDisplayed() {
        return (isDisplayed(deleteStoreBtn) && isDisplayed(editStoreBtn));
    }

    public String getSuccessMessage() {
        return getText(successToast);
    }

    public String getUpdateSuccessMessage() {
        return getText(updateSuccessToast);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorToast);
    }

    public boolean isUpdateErrorMessageDisplayed() {
        return isDisplayed(updateErrorToast);
    }

    public boolean isDeleteMessageDisplayed() {
        return isDisplayed(deleteSuccessToast);
    }

    public boolean isStoreDisplayed(String text) {
        return isDisplayed(By.xpath(String.format(storeLocator, text)));
    }

    public boolean isDialogDisplayed() {
        return isDisplayed(deleteDialog);
    }

    public void clickCancelDelete() {
        click(cancelDelete);
    }

    public void clickConfirmDelete() { click(confirmDelete); }

    public ViewUsersPage clickUsersMenu() {
        Driver.getDriver().findElement(usersButton).click();
        return new ViewUsersPage();
    }
}