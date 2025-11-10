package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminDashboardPage extends BasePage {

    private final By storesLink=By.xpath("//a/span[text()='Store']");
    private final By storesTable=By.xpath("//table[contains(@class,'table')]");
    private final By tableColumns=By.xpath("//table[contains(@class,'table')]/thead//th");
    private final By editStoreBtn=By.xpath("//table[contains(@class,'table')]/tbody//td/button//a");
    private final By deleteStoreBtn=By.xpath("//table[contains(@class,'table')]/tbody//td/button[contains(@onClick,\"confirmDelete\")]");
    private final By addStoreBtn=By.xpath("//a[text()='ADD STORE']");
    private final By successToast = By.xpath("//div[contains(text(),'Store created successfully')]");
    private final By updateSuccessToast = By.xpath("//div[contains(text(),'Store updated successfully')]");
    private final By errorToast = By.xpath("//div[contains(text(),'Store creation failed')]");
    private final By updateErrorToast = By.xpath("//div[contains(text(),'Store update failed')]");
    private String storeLocator ="//table//tbody//tr[td[contains(normalize-space(.),'%s')]]";

    private final By usersButton = By.xpath("//span[text()='Users']"); // Youmna's addition

    private final String[] cloumns={"Name","Description","Location", "Admin Name",  "Actions"};

    public AdminDashboardPage clickStoresLink() {
        clickWithJS(storesLink);
        return this;

    }

    public AddEditStorePage clickAddStore() {
        click(addStoreBtn);
        return new AddEditStorePage();
    }
    public AddEditStorePage clickEditStore() {
        click(editStoreBtn);
        return new AddEditStorePage();
    }

    public boolean isStoresTableDisplayed(){
       return isDisplayed(storesTable);
    }

    public boolean verifyAllColumnsDisplayed() {
        List<WebElement> columns=findElements(tableColumns);
        if (columns.size()<5){
            return false;
        }
        else {
            for(int i=0;i<5;i++) {
                if(! columns.get(i).getText().equalsIgnoreCase(cloumns[i]))
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

    public boolean isStoreDisplayed(String text) {
        return isDisplayed(By.xpath(String.format(storeLocator,text)));
    }

    //Youmna's addition
    public ViewUsersPage clickUsersMenu(){

        Driver.getDriver().findElement(usersButton).click();
        return new ViewUsersPage();
    }
}
