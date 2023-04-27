Feature: Go to Swag Labs and Login

  Scenario: Login successfully
    Given The user go to page with url "https://www.saucedemo.com/"
    When The user input username "standard_user" and password "secret_sauce"
    And The user click on Login
    Then Go to page with title "Products" and url "https://www.saucedemo.com/inventory.html"