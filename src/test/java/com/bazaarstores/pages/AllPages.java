package com.bazaarstores.pages;

import com.bazaarstores.pages.adminPages.AddUserPage;
import com.bazaarstores.pages.adminPages.AdminUsersPage;

public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private AdminUsersPage adminUsersPage;
    private AddUserPage addUserPage;



    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public RegistrationPage getRegistrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage();
        }
        return registrationPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public AdminUsersPage getAdminUsersPage() {
        if (adminUsersPage == null) {
            adminUsersPage = new AdminUsersPage();
        }
        return adminUsersPage;
    }


    public AddUserPage getAddUserPage() {
        if (addUserPage == null) {
            addUserPage = new AddUserPage();
        }
        return addUserPage;
    }
}
