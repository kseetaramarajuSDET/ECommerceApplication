@all
Feature: Product Search Functionality

  As a user
  I want to search for products
  So that I can view details and make a purchase

  Background:
    Given user landed in dashboard page


  @search @search_TC_001 @smoke @regression
  Scenario: Search for an existing product
    When user enters "mac" in the search box
    And user clicks on the search button
    Then user should see search results related to "mac"


  @search @search_TC_002 @regression
  Scenario: Search for a non-existent product
    When user enters "asdlkjasdlj" in the search box
    And user clicks on the search button
    Then user should see a product not found error message

  @search @search_TC_003 @regression
  Scenario: Search without entering any product name
    When user leaves the search box empty
    And user clicks on the search button
    Then user should see a product not found error message
