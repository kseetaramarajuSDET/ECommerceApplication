@all
Feature: User Registration

  To access the application, a new user should be able to register by providing valid details.

  Background:
    Given user is on the registration page

  @register @register_TC_001 @smoke @regression
  Scenario: Register by entering all mandatory valid details
    When user enters all mandatory fields
    And user agrees to the privacy policy
    And user clicks on the continue button
    Then user should see a success message

  @register @register_TC_002 @smoke @regression
  Scenario: Register by not entering all mandatory valid details
    When user leaves all mandatory fields empty
    And user clicks on the continue button
    Then user should see error messages for required fields

