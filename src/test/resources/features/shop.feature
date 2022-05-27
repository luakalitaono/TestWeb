@shop
Feature: User Shop
  Scenario: Logged user buys 3 items in the site
    Given user is signed in successfully with data 'login_success'
    Then user goes to list page and select item 'Printed Chiffon Dress' and fills data 'chiffon_dress'
    And press add to cart
    Then user selects next item 'Blouse' and fills data 'blouse'
    And press add to cart
    Then selects next item 'Printed Dress'
    And proceed to checkout
    And user follows steps until complete the shop
    Then the message order confirmation should appear
