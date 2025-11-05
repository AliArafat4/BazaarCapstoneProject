package com.bazaarstores.pages;


import com.bazaarstores.pages.store_manager_pages.AddProductPage;
import com.bazaarstores.pages.store_manager_pages.ProductsPage;
import com.bazaarstores.pages.store_manager_pages.StoreManagerDashboardPage;

import com.bazaarstores.pages.customer_pages.CustomerMainPage;
import com.bazaarstores.pages.register_pages.RegistrationPage;
import com.bazaarstores.pages.adminPages.AddUserPage;
import com.bazaarstores.pages.adminPages.AdminUsersPage;


public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;

    private StoreManagerDashboardPage storeManagerDashboardPage;
    private ProductsPage productsPage;
    private AddProductPage addProductsPage;

    private CustomerMainPage customerMainPage;

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


    public StoreManagerDashboardPage getStoreManagerDashboardPage() {
        if (storeManagerDashboardPage == null) {
            storeManagerDashboardPage = new StoreManagerDashboardPage();
        }
        return storeManagerDashboardPage;
    }

    public ProductsPage getProductsPage() {
        if (productsPage == null) {
            productsPage = new ProductsPage();
        }
        return productsPage;
    }

    public AddProductPage getAddProductsPage() {
        if (addProductsPage == null) {
            addProductsPage = new AddProductPage();
        }
        return addProductsPage;
    }

    public CustomerMainPage getCustomerMainPage() {
        if (customerMainPage == null) {
            customerMainPage = new CustomerMainPage();
        }
        return customerMainPage;
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