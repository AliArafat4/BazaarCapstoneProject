@Regression @AddProduct
Feature:
  Store Manager Add Product Feature

  Background:
    Given user is in home page
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button

  @Smoke @PositiveAddProduct
  Scenario: Store Manager adds a new product with all information successfully
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    And store manager enters product name "Sample Book"
    And store manager sets product price "10.00"
    And store manager sets product stock "100"
    And store manager sets product SKU "00001"
    And store manager sets product category "Books"
    And store manager sets product manufacturer "Books Company inc."
    And store manager uploads product image "book.png"
    And store manager sets product discount "1.00"
    And store manager enters product description "This is a sample book."
    And store manager clicks on Submit button
    Then store manager should see success message for adding product
    And store manager get redirected to Products page
    And store manager should see the new product in the products list with name "Sample Book"
    And assert the new product via API with name "Sample Book" and sku "00001"

  @Smoke @PositiveAddProduct
  Scenario: Store Manager adds a new product with only required information successfully
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    And store manager enters product name "Sample Book"
    And store manager sets product price "10.00"
    And store manager sets product stock "100"
    And store manager sets product SKU "00002"
    And store manager clicks on Submit button
    Then store manager should see success message for adding product
    And store manager get redirected to Products page
    And store manager should see the new product in the products list with name "Sample Book"
    And assert the new product via API with name "Sample Book" and sku "00002"

  @Smoke @NegativeAddProduct @MissingData
  Scenario Outline: Store Manager fails to add a new product due to missing information
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    And store manager enters product name '<ProductName>'
    And store manager sets product price "<Price>"
    And store manager sets product stock "<Stock>"
    And store manager sets product SKU "<SKU>"
    And store manager clicks on Submit button
    Then store manager should see error message for missing product info "<Error field>"
    And assert the product wasn't added via API with sku "<SKU>"
    Examples:
      | ProductName |  | Price | Stock | SKU   | Error field |  |
      |             |  |       |       |       | all         |  |
      |             |  | 10.00 | 100   | 00003 | name        |  |
      | Sample Book |  |       | 100   | 00004 | price       |  |
      | Sample Book |  | 10.00 |       | 00005 | stock       |  |
      | Sample Book |  | 10.00 | 100   |       | sku         |  |

  @Smoke @NegativeAddProduct @SkuTaken
  Scenario: Store Manager adds a new product with taken sku
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    And store manager enters product name "Sample Book"
    And store manager sets product price "10.00"
    And store manager sets product stock "100"
    And store manager sets product SKU "3945165"
    And store manager clicks on Submit button
    Then store manager should see error message for taken sku "The sku has already been taken."
    And assert the the product wasn't added via API with name "Sample Book" and sku "3945165"

    #ADDED ZeroNumbers test cases
  @Smoke @NegativeAddProduct @NegativeNumbers @ZeroNumbers @KnownIssue
  Scenario Outline: Store Manager fails to add a new product due to negative numbers input
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    And store manager enters product name 'Sample Book'
    And store manager sets product price "<Price>"
    And store manager sets product stock "<Stock>"
    And store manager sets product SKU "<sku>"
    And store manager sets product discount "<Discount>"
    And store manager clicks on Submit button
    Then store manager should see error message for negative number "<Error field>"
    And assert the product wasn't added via API with sku "SKU" and name "Sample Book"
    Examples:
      | Price |  | Stock | Discount | sku   | Error field     |  |
      | -10   |  | 10    | 10       | 00006 | price           |  |
      | -10   |  | -10   | 10       | 00007 | price, stock    |  |
      | -10   |  | -10   | -10      | 00008 | all             |  |
      | 10    |  | -10   | 10       | 00009 | stock           |  |
      | 10    |  | -10   | -10      | 00010 | stock, discount |  |
      | 10    |  | 10    | -10      | 00011 | discount        |  |
      | 0     |  | 10    | 10       | 00012 | price           |  |
      | 10    |  | 0     | 10       | 00013 | stock           |  |
      | 0     |  | 0     | 10       | 00014 | price, stock    |  |

  @Smoke @NegativeAddProduct @InvalidImageFormat
  Scenario: Store Manager adds a new product with all information successfully
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    And store manager enters product name "Sample Book"
    And store manager sets product price "10.00"
    And store manager sets product stock "100"
    And store manager sets product SKU "00015"
    And store manager uploads product image "book.txt"
    And store manager clicks on Submit button
    Then store manager should see error message for invalid image format "The image field must be an image."
    Then store manager should see error message for invalid image format "The image field must be a file of type: jpeg, png, jpg, gif, svg."
    And assert the the product wasn't added via API with name "Sample Book" and sku "00015"

  @Smoke @PositiveAddProduct @PageTitle @KnownIssue
  Scenario: Verify Add product page title
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    Then store manager should see the page title is "Add Product"

  @Smoke @PositiveAddProduct @ClickableFields
  Scenario: Verify all fields are clickable in add product page
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    Then store manager should be able to click all input fields on Add Product page

  @Smoke @PositiveAddProduct @VisibleFields
  Scenario: Verify all fields are visible in add product page
    Given store manager is logged in
    When store manager navigates to Products section
    And store manager clicks on ADD PRODUCT button
    Then store manager should be able to see all input fields on Add Product page