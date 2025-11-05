package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminDashboardPage extends BasePage {

    private final By storesLink=By.xpath("//a/span[text()='Store']");
    private final By storesTable=By.xpath("//table[contains(@class,'table')]");
    private final By tableColumns=By.xpath("//table[contains(@class,'table')]/thead//th");
    private final By editStoreBtn=By.xpath("//table[contains(@class,'table')]/tbody//td/button//a");
    private final By deleteStoreBtn=By.xpath("//table[contains(@class,'table')]/tbody//td/button[contains(@onClick,\"confirmDelete\")]");
    private final String[] cloumns={"Name","Description","Location", "Admin Name",  "Actions"};

    public void clickStoresLink() {
        click(storesLink);
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
}
