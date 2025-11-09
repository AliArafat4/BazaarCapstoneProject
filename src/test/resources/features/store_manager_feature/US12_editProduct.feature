@Regression @EditProduct
Feature:
  Store Manager Edit Product Feature

  Background:
    Given user is in home page
    When user enters email "storemanager@sda.com" and password "Password.12345"
    And user clicks login button
    Then store manager is logged in
    When store manager navigates to Products section

  @Smoke @PositiveEditProduct @PositiveEditName
  Scenario: Store Manager edits a product name successfully
    Given product is available in the list with name "sample book1", price "10", stock "10", and sku "00020"
    Then assert the new product via API with name "sample book1" and sku "00020"
    When store manager clicks on Edit Product Button for "sample book1" product
    And store manager change Product "name" to "Edited Product Name"
    And store manager clicks on Submit button
    Then store manager should see success message for editing a product
    And store manager get redirected to Products page
    And store manager should see the new product in the products list with name "Edited Product Name"
    And assert the edited product via API with "name" "Edited Product Name" and sku "00020"

  @Smoke @NegativeEditProduct @NegativeEditName @EmptyName
  Scenario: Store Manager fails to edits a product name duo to empty name
    Given product is available in the list with name "sample book2", price "10", stock "10", and sku "00021"
    Then assert the new product via API with name "sample book2" and sku "00021"
    When store manager clicks on Edit Product Button for "sample book2" product
    And store manager change Product "name" to ""
    And store manager clicks on Submit button
    Then store manager should see an error message for missing product "name" field
    And assert the product wasn't edited via API with "name" "" and sku "00021"


  @Smoke @PositiveEditProduct @PositiveEditPrice
  Scenario: Store Manager edits a product price successfully
    Given product is available in the list with name "sample book3", price "10", stock "10", and sku "00022"
    Then assert the new product via API with name "sample book3" and sku "00022"
    When store manager clicks on Edit Product Button for "sample book3" product
    And store manager change Product "price" to "20"
    And store manager clicks on Submit button
    Then store manager should see success message for editing a product
    And store manager get redirected to Products page
    And store manager should see the product in the products list with name "sample book3" and "price" "20.00"
    And assert the edited product via API with "price" "20.00" and sku "00022"

  @Smoke @NegativeEditProduct @NegativeEditPrice @EmptyPrice
  Scenario: Store Manager fails to edits a product duo to empty price
    Given product is available in the list with name "sample book5", price "10", stock "10", and sku "00024"
    Then assert the new product via API with name "sample book5" and sku "00024"
    When store manager clicks on Edit Product Button for "sample book5" product
    And store manager change Product "price" to ""
    And store manager clicks on Submit button
    Then store manager should see an error message for missing product "price" field
    And assert the product wasn't edited via API with "price" "" and sku "00024"


#    TODO: MAKE IT PASS
  @Smoke @NegativeEditProduct @NegativeEditPrice @KnownIssue @ZeroAndNegativeEditPrice
  Scenario Outline: Store Manager fails to edits a product price duo to negative or zero price
    Given product is available in the list with name "<book name>", price "10", stock "10", and sku "<sku>"
    Then assert the new product via API with name "<book name>" and sku "<sku>"
    When store manager clicks on Edit Product Button for "<book name>" product
    And store manager change Product "price" to "<price value>"
    And store manager clicks on Submit button
    Then store manager should see an error message for missing product "price" field
    And assert the product wasn't edited via API with "price" "<price value>" and sku "<sku>"
    Examples:
      | book name    | price value | sku   |  |
      | sample book6 | 0.00        | 00023 |  |
      | sample book7 | -20.00      | 00024 |  |

    ###########################################
  @Smoke @PositiveEditProduct @PositiveEditStock
  Scenario: Store Manager edits a product stock successfully
    Given product is available in the list with name "sample book8", price "10", stock "10", and sku "00025"
    Then assert the new product via API with name "sample book8" and sku "00025"
    When store manager clicks on Edit Product Button for "sample book8" product
    And store manager change Product "stock" to "20"
    And store manager clicks on Submit button
    Then store manager should see success message for editing a product
    And store manager get redirected to Products page
    And store manager should see the product in the products list with name "sample book8" and "stock" "20"
    And assert the edited product via API with "stock" "20" and sku "00025"

  @Smoke @NegativeEditProduct @NegativeEditStock @EmptyStock
  Scenario: Store Manager fails to edit product duo to empty stock
    Given product is available in the list with name "sample book9", price "10", stock "10", and sku "00026"
    Then assert the new product via API with name "sample book9" and sku "00026"
    When store manager clicks on Edit Product Button for "sample book9" product
    And store manager change Product "stock" to ""
    And store manager clicks on Submit button
    Then store manager should see an error message for missing product "stock" field
    And assert the product wasn't edited via API with "stock" "" and sku "00026"


#    TODO: MAKE IT PASS
  @Smoke @NegativeEditProduct @NegativeEditStock @KnownIssue @ZeroAndNegativeEditStock
  Scenario Outline: Store Manager fails to edit product duo to negative or zero stock
    Given product is available in the list with name "<book name>", price "10", stock "10", and sku "<sku>"
    Then assert the new product via API with name "<book name>" and sku "<sku>"
    When store manager clicks on Edit Product Button for "<book name>" product
    And store manager change Product "stock" to "<stock value>"
    And store manager clicks on Submit button
    Then store manager should see an error message for missing product "stock" field
    And assert the product wasn't edited via API with "stock" "<stock value>" and sku "<sku>"
    Examples:
      | book name     | stock value | sku   |  |
      | sample book10 | 0           | 00027 |  |
      | sample book11 | -20         | 00028 |  |


  @Smoke @PositiveEditProduct @PositiveEditSKU
  Scenario: Store Manager edits a product SKU successfully
    Given product is available in the list with name "sample book12", price "10", stock "10", and sku "00029"
    Then assert the new product via API with name "sample book12" and sku "00029"
    When store manager clicks on Edit Product Button for "sample book12" product
    And store manager change Product "sku" to "00030"
    And store manager clicks on Submit button
    Then store manager should see success message for editing a product
    And store manager get redirected to Products page
    And store manager should see the product in the products list with name "sample book12" and "stock" "10"
    And assert the edited product via API with "sku" "00030" and sku "00030"

  @Smoke @NegativeEditProduct @NegativeEditSKU @EmptySKU
  Scenario: Store Manager fails to edit product duo to empty SKU
    Given product is available in the list with name "sample book13", price "10", stock "10", and sku "00031"
    Then assert the new product via API with name "sample book13" and sku "00031"
    When store manager clicks on Edit Product Button for "sample book13" product
    And store manager change Product "sku" to ""
    And store manager clicks on Submit button
    Then store manager should see an error message for missing product "sku" field
    And assert the product wasn't edited via API with "sku" "" and sku "00031"

  @Smoke @NegativeEditProduct @NegativeEditSKU @TakenSKU
  Scenario: Store Manager fails to edit product duo to empty SKU
    Given product is available in the list with name "sample book14", price "10", stock "10", and sku "00032"
    Then assert the new product via API with name "sample book14" and sku "00032"
    When store manager clicks on Edit Product Button for "sample book14" product
    And store manager change Product "sku" to "397497"
    And store manager clicks on Submit button
    Then store manager should see error message for taken sku "The sku has already been taken."
    And assert the product wasn't edited via API with "name" "sample book14" and sku "397497"

  @Smoke @PositiveEditProduct @PositiveEditCategory
  Scenario: Store Manager edits a product Category successfully
    Given product is available in the list with name "sample book15", price "10", stock "10", and sku "00033"
    Then assert the new product via API with name "sample book15" and sku "00033"
    When store manager clicks on Edit Product Button for "sample book15" product
    And store manager change Product "category" to "2"
    And store manager clicks on Submit button
    Then store manager should see success message for editing a product
    And store manager get redirected to Products page
    And store manager should see the product in the products list with name "sample book15" and "Category" "Books"
    And assert the edited product via API with "category_id" "2" and sku "00033"

  @Smoke @PositiveEditProduct @PositiveEditManufacturer
  Scenario: Store Manager edits a product Manufacturer successfully
    Given product is available in the list with name "sample book16", price "10", stock "10", and sku "00034"
    Then assert the new product via API with name "sample book16" and sku "00034"
    When store manager clicks on Edit Product Button for "sample book16" product
    And store manager change Product "manufacturer" to "New Manufacturer"
    And store manager clicks on Submit button
    Then store manager should see success message for editing a product
    And store manager get redirected to Products page
    And assert the edited product via API with "manufacturer" "New Manufacturer" and sku "00034"

  @Smoke @PositiveEditProduct @PositiveEditImage
  Scenario: Store Manager edits a product Image successfully
    Given product is available in the list with name "sample book17", price "10", stock "10", and sku "00035"
    Then assert the new product via API with name "sample book17" and sku "00035"
    When store manager clicks on Edit Product Button for "sample book17" product
    And store manager change Product "image" to "book.png"
    And store manager clicks on Submit button
    Then store manager should see success message for editing a product
    And store manager get redirected to Products page
    And assert the edited product via API with "image_url" "book.png" and sku "00035"

  @Smoke @NegativeEditProduct @NegativeEditImage
  Scenario: Store Manager edits a product Image successfully
    Given product is available in the list with name "sample book18", price "10", stock "10", and sku "00036"
    Then assert the new product via API with name "sample book18" and sku "00036"
    When store manager clicks on Edit Product Button for "sample book18" product
    And store manager change Product "image" to "book.txt"
    And store manager clicks on Submit button
    Then store manager should see an error message for missing product "image" field
    And assert the product wasn't edited via API with "name" "sample book14" and sku "397497"