Feature: Validate Foreign currency exchange rates api with currency conversion

@RegressionTest
Scenario: Validate Latest Foreign Exchange rates API
  Given user set Latest ForeignExchangeRates api 
  When user calls "LatestForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "EUR"
  And user validate "date" in response body as Latest date
  And user validate count of total currencies under "rates" in response body is "32"

@RegressionTest
Scenario: Validate Latest Foreign Exchange rates API
  Given user set Latest ForeignExchangeRates api with param "base" as "USD"
  When user calls "LatestForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "USD"
  And user validate "date" in response body as Latest date
  And user validate count of total currencies under "rates" in response body is "33"
    
@RegressionTest
Scenario: Validate Latest Foreign Exchange rates API
  Given user set Latest ForeignExchangeRates api with param "symbols" as "USD"
  When user calls "LatestForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "EUR"
  And user validate "date" in response body as Latest date
  And user validate count of total currencies under "rates" in response body is "1"
  And user validate "rates" target currency in response body as "USD" 
 
@RegressionTest
Scenario: Validate Latest Foreign Exchange rates API
  Given user set Latest ForeignExchangeRates api with param "base" as "USD" & "symbols" as "EUR"
  When user calls "LatestForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "USD"
  And user validate "date" in response body as Latest date
  And user validate count of total currencies under "rates" in response body is "1"
  And user validate "rates" target currency in response body as "EUR"
  
@RegressionTest @NegativeScenarioTest
Scenario: Validate Latest Foreign Exchange rates API
  Given user set Latest ForeignExchangeRates api with param "symbols" as "EUR"
  When user calls "LatestForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 400
  And user receive "error" validation message "Symbols 'EUR' are invalid for date" in response body
  	
@RegressionTest
Scenario: Validate Specific date Foreign Exchange Rates 
  Given user set Specific date ForeignExchangeRates api 
  When user calls "SpecificDateForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "EUR"
  And user validate count of total currencies under "rates" in response body is "32"
  And user validate "date" in response body as "2020-12-31" date
    
@RegressionTest
Scenario: Validate Specific date Foreign Exchange Rates 
  Given user set Specific date ForeignExchangeRates api with param "base" as "USD"
  When user calls "SpecificDateForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "USD"
  And user validate count of total currencies under "rates" in response body is "33"
  And user validate "date" in response body as "2020-12-31" date
   
@RegressionTest
Scenario: Validate Specific date Foreign Exchange Rates 
  Given user set Specific date ForeignExchangeRates api with param "symbols" as "USD"
  When user calls "SpecificDateForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "EUR"
  And user validate count of total currencies under "rates" in response body is "1"
  And user validate "date" in response body as "2020-12-31" date
  And user validate "rates" target currency in response body as "USD" 
  
@RegressionTest
Scenario: Validate Specific date Foreign Exchange Rates 
  Given user set Specific date ForeignExchangeRates api with param "base" as "USD" & "symbols" as "EUR"
  When user calls "SpecificDateForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 200
  And user validate "base" currency in response body as "USD"
  And user validate count of total currencies under "rates" in response body is "1"
  And user validate "date" in response body as "2020-12-31" date
  And user validate "rates" target currency in response body as "EUR"
 
@RegressionTest @NegativeScenarioTest
Scenario: Validate Specific date Foreign Exchange Rates 
  Given user set Specific date ForeignExchangeRates api with param "symbols" as "EUR"
  When user calls "SpecificDateForeignExchangeRates" api with "Get" https request
  Then user receive valid HTTP response code 400
  And user receive "error" validation message "Symbols 'EUR' are invalid for date 2021-01-01." in response body