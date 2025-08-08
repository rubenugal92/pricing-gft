Feature: Pricing API E2E Tests

Feature: Pricing karate tests

  Background:
    * url baseUrl
    * path 'api', 'prices'

  Scenario: Get price at 10:00 on 14 June for product 35455 brand 1
    Given param brandId = 1
    And param productId = 35455
    And param date = '2020-06-14T10:00:00'
    When method GET
    Then status 200
    And match response.price == 35.50

  Scenario: Price not found returns 404
    Given param brandId = 999
    And param productId = 999999
    And param date = '2020-01-01T00:00:00'
    When method GET
    Then status 404
    And match response == {"timestamp":null,"status":404,"message":"No price found for brandId=999, productId=999999 at date=2020-01-01T00:00"}

