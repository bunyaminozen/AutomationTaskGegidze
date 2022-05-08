Feature: Login feature

  Background:
    Given the user is on the Login page;

  Scenario: When valid credentials is entered, the user logs in successfully
    When the user enter "mustangford1923@gmail.com"  into the email input box
    And the user click on the "Log in" button
    And the user enter "FordHarrison"  into the password input box
    And the user click on the "Log in" button
    Then the user should navigate to "Account Overview - Paysera" page
    And the displayed email address on the account should be "mustangford1923@gmail.com"

  Scenario Outline: When INVALID EMAIL is entered, the user can't login and the error message is displayed
    When the user enter "<invalid email>"  into the email input box
    And the user click on the "Log in" button
    Then the user should navigate to "Log in to your account - Paysera" page
    And the error message "The specified user could not be found" should be displayed
    Examples:
      | invalid email            |
      | mustangford192@gmail.com |
      | mustangford1923gmail.com |


  Scenario Outline: When INVALID PASSWORD is entered, the user can't login and the error message is displayed
    When the user enter "mustangford1923@gmail.com"  into the email input box
    And the user click on the "Log in" button
    And the user enter "<invalid password>"  into the password input box
    And the user click on the "Log in" button
    Then the user should navigate to "Log in to your account - Paysera" page
    And the error message "Incorrect password. Please try again." should be displayed
    Examples:
      | invalid password |
      | Fordharrison     |
      | fordharrison     |

