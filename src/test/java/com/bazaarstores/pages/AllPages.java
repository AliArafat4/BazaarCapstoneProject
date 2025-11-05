package com.bazaarstores.pages;

import com.bazaarstores.pages.customer_pages.CustomerMainPage;
import com.bazaarstores.pages.login_pages.LoginPage;
import com.bazaarstores.pages.register_pages.RegistrationPage;

public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private CustomerMainPage customerMainPage;



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

    public CustomerMainPage getCustomerMainPage() {
        if (customerMainPage == null) {
            customerMainPage = new CustomerMainPage();
        }
        return customerMainPage;
    }
}
