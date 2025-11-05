package com.bazaarstores.pages.adminPages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;

public class AdminUsersPage extends BasePage {

    private final By usersButton = By.cssSelector("a[href*='users']");
    private final By addUsersButton = By.cssSelector("a[href*='create']");

    public AdminUsersPage goToUsersPage (){
        click(usersButton);
        return this;
    }

    public AdminUsersPage clickAddUsers() {
        click(addUsersButton);
        return this;
    }

}
