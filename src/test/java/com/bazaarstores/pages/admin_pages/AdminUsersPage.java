package com.bazaarstores.pages.admin_pages;

import com.bazaarstores.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminUsersPage extends BasePage {

    private final By usersButton = By.xpath("//a[@href='https://bazaarstores.com/users' and contains(@class, 'sidebar-link')]");
    private final By addUsersButton = By.cssSelector("a[href*='create']");
    private final By searchBar = By.xpath("//input[@name='email']");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By editButton = By.xpath("//following-sibling::td//a[contains(@href, '/users/')]/i[contains(@class, 'bi-pencil-square')]");


    public AdminUsersPage goToUsersPage (){
        clickWithJS(usersButton);
        return this;
    }

    public AdminUsersPage clickAddUsers() {
        click(addUsersButton);
        return this;
    }

    public boolean addUsersIsVisible(){
        return isDisplayed(addUsersButton);
    }

//    public boolean isUserInList(String userEmail) {
//        By userCell = By.xpath("//td[normalize-space()='" + userEmail + "']");
//        By nextButton = By.xpath("//ul[contains(@class,'pagination')]//a[normalize-space() = '›' or normalize-space() = '>' or normalize-space() = 'Next']");
//
//        // try pages until found or no next
//        while (true) {
//            if (isDisplayed(userCell)) {
//                return true;
//            }
//
//            // try to find a clickable Next button
//            try {
//                WebElement next = findElement(nextButton);
//                if (!next.isDisplayed() || !next.isEnabled()) return false;
//
//                // remember something to detect page change (first row email)
//                String before = "";
//                try {
//                    before = findElement(By.cssSelector("table tbody tr td:nth-child(2)")).getText();
//                } catch (Exception ignored) {}
//
//                // click (JS fallback inside clickWithJS if needed)
//                try { next.click(); } catch (Exception e) { clickWithJS(nextButton); }
//
//                // wait for table to refresh (either user appears or first-cell changes)
//                long end = System.currentTimeMillis() + 5000;
//                while (System.currentTimeMillis() < end) {
//                    if (isDisplayed(userCell)) return true;
//                    if (!before.isEmpty()) {
//                        try {
//                            String now = findElement(By.cssSelector("table tbody tr td:nth-child(2)")).getText();
//                            if (!now.equals(before)) break; // page changed
//                        } catch (Exception ignored) {}
//                    }
//                    try { Thread.sleep(200); } catch (InterruptedException ignored) {}
//                }
//            } catch (Exception e) {
//                // no next button => we're done
//                return false;
//            }
//        }
//    }

    public boolean isUserInList(String userEmail) {
        findElement(searchBar).sendKeys(userEmail);
        click(searchButton);
        By userCell = By.xpath("//td[normalize-space()='" + userEmail + "']");
        return isDisplayed(userCell);
    }



    public void locateUser(String userEmail){
        findElement(searchBar).sendKeys(userEmail);
        click(searchButton);

    }

    public void clickEditButton(){
        click(editButton);
    }

    public boolean editUsersIsVisible() {
        return isDisplayed(editButton);
    }

}
