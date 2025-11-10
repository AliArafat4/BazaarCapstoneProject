package com.bazaarstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By dashboard = By.xpath("//div[@class='products-grid']");
    private final By profileIcon = By.cssSelector(".profile-icon");
    private final By logoutButton = By.cssSelector(".logout");

    public DashboardPage() {
        super();
    }

    public void clickProfileLink() {
        click(profileIcon);
    }

    public LoginPage clickLogout() {
        click(logoutButton);
        return new LoginPage();
    }

    public boolean isDashboardPageDisplayed() {
        return isDisplayed(dashboard);
    }
    public boolean isProfileVisitChartDisplayed() {
        By profileVisitChart = By.xpath("//div[@class='card-body']");
        return isDisplayed(profileVisitChart);
    }

}
