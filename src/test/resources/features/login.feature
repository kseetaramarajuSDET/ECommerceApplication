@all
Feature: Login Functionality
  Registered User Should Be Able To Login Successfully

  Background:
    Given user landed in dashboard page

  @login @login_TC_001 @regression @smoke
  Scenario Outline: user login with valid credentials
    When user navigates to login page
    And  user enter username "<email>" and password "<password>"
    And  user clicks on login button
    Then user navigates to homepage
    Examples:
      | email                     | password  |
      | kseetaramaraju1@gmail.com | Seeta@123 |


  @login @login_TC_002 @regression @smoke
  Scenario Outline: multiple users login using valid and invalid credentails
    When user navigates to login page
    And  user enter username "<email>" and password "<password>"
    And  user clicks on login button
    Then user login result should be validated "<result>"
    Examples:
      | email                        | password     | result  | type                               |
      | kseetaramaraju1@gmail.com    | Seeta@123    | success | valid email and password           |
      | kseetaramaraju1@gmail.com    | Seeta@12346  | failure | valid email and invalid password   |
      | kseetaramaraju122@gmail.com  | Seeta@123    | failure | invalid email and valid password   |
      | kseetaramaraju7878@gmail.com | Seeta@123898 | failure | invalid email and invalid password |

  @login @login_TC_003 @regression
  Scenario: Login Without Any Credentials
    When user navigates to login page
    And user does not enter any credentials and click on login button
    Then user should get error message