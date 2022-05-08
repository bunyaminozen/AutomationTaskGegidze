Feature: API features
  Scenario: When user send a GET request,you retrieve all supported currencies list
    When the user send a GET request to API in order to retrieve all supported currencies
    Then Status code should be "200"
    And Content-type should be "application/json"
    And the all supported currencies should be retrieved




