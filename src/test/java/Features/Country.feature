Feature: This feature file is for Testing API using Karate Tool

  Background:
    * def config = read('classpath:test-config.json')

  @Test_Count_01 @CountryTest
  Scenario: To fetch country details without parametrization

    Given url 'https://restcountries.eu/rest/v2/all'
    When method GET
    Then status 200

  @Test_Count_02 @CountryTest
  Scenario: To fetch country details with parametrization

    Given url config.countryapiURL
    When method GET
    Then status 200

  @Test_Count_03 @CountryTest
  Scenario: To validate response value of India

    Given url config.countryapiURL
    When method GET
    Then status 200
    #Then match response.name contains {"name" : "India"}
    * def countryName = "India"
    * def countryResponse = karate.jsonPath(response, "$[?(@.name == '" + countryName + "')]")
    * match countryResponse[0].capital == 'New Delhi'
    * match countryResponse[0].area == 3287590.0
    * match countryResponse[0].demonym == 'Indian'
