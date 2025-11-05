package com.bazaarstores.pages;

import com.bazaarstores.pages.store_manager_pages.AddProductPage;
import com.bazaarstores.pages.store_manager_pages.ProductsPage;
import com.bazaarstores.pages.store_manager_pages.StoreManagerDashboardPage;

public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private StoreManagerDashboardPage storeManagerDashboardPage;
    private ProductsPage productsPage;
    private AddProductPage addProductsPage;



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
}