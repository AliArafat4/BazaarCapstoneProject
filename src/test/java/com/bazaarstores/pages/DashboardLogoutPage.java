package com.bazaarstores.pages;

import com.bazaarstores.pages.login_pages.LoginPage;
import org.openqa.selenium.By;

public class DashboardLogoutPage extends BasePage {

    // Profile icon
    private final By profileIcon = By.cssSelector(".profile-icon");

    // Log Out link
    private final By logoutButton = By.cssSelector("a.logout");

    // Click the user profile icon (top right)
    public void clickProfileIcon() {
        click(profileIcon);
    }

    // Click the "Log Out" button
    public LoginPage clickLogoutButton() {
        click(logoutButton);
        return new LoginPage();
    }

    // Verify if the "Log Out" button is visible
    public boolean isLogoutButtonVisible() {
        return isDisplayed(logoutButton);
    }
}
