Feature: Online Currency Exchange Features

  Background:
    Given the user is on the "OnlineCurrencyExchange" page;


  Scenario: When user fills "BUY" amount, "SELL" amount box is being emptied and vice versa
    Then the "Sell" amount input box should be filled
    And the "Buy" amount input box should be empty
    When the user enter "100" into the "Buy" amount input box
    Then the "Sell" amount input box should be empty
    When the user enter "100" into the "Sell" amount input box
    Then the "Buy" amount input box should be empty


  Scenario: When user choose any country, the currency option and rates is updated
    When the user select "United Kingdom" from country options
    Then "GBP" should be displayed as respective currency
    And rates should be updated according to the currency option
    When the user select "Bulgaria" from country options
    Then "BGN" should be displayed as respective currency
    And rates should be updated according to the currency option
    When the user select "Ukraine" from country options
    Then "UAH" should be displayed as respective currency
    And rates should be updated according to the currency option

  @wip
  Scenario:When bank provider's exchange amount is lower than provided by Paysera, a text box is displayed, representing the loss
    When the user select "Bulgaria" from country options
    Then the loss amount for the currency should be displayed properly


  Scenario: When user clicks on the related dropdown, all provided countries and languages are displayed
    When the user click on the flag icon at the bottom of the page
    And the user click on the "countries-dropdown" box
    Then all provided options should be displayed in the "countries-dropdown"
    When the user click on the "languages-dropdown" box
    Then all provided options should be displayed in the "languages-dropdown"

  Scenario Outline: When user apply any filter, results is displayed according to the filter
    When the user enter "200" into the "Sell" amount input box
    And the user click on the box of currency options for "Sell"
    And the user select "<sell currency>" from the currency  list
    And the user click on the box of currency options for "Buy"
    And the user select "<buy currency>" from the currency  list
    And the user click on the Filter button
    Then displayed currency should be the same with "<buy currency>"
    When the user enter "200" into the "Buy" amount input box
    And the user click on the Filter button
    Then displayed currency should be the same with "<sell currency>"

    Examples:
      | sell currency | buy currency |
      | USD           | EUR          |
      | GBP           | TRY          |































