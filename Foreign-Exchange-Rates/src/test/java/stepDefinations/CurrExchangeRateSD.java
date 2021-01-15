package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.CurrencyExchangeRate;
import resources.APIResources;
import utils.Utils;

public class CurrExchangeRateSD extends Utils{
	
	Scenario sc;	
	Response response;
	CurrencyExchangeRate cer;
	
	@Before
	public void addDescToReports(Scenario scenario) {
	        this.sc = scenario;
	}

	@Given("user set Latest ForeignExchangeRates api")
	public void user_set_latest_foreign_exchange_rates_api() throws IOException {
	
		reqspec = given().spec(requestSpecification());
		sc.log("Http Request is created Sucessfully ");
						  
	}

	@When("user calls {string} api with {string} https request")
	public void user_calls_api_with_https_request(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);

		if(method.equalsIgnoreCase("POST")){
		
		response =reqspec.when().post(resourceAPI.getResource());
		sc.log(resource+" Http Request Post Sucessfuly");
		}

		else if(method.equalsIgnoreCase("GET")){
			
		response =reqspec.when().get(resourceAPI.getResource());
		sc.log(resource+" Http Request Get Sucessfuly");
		}

		else if(method.equalsIgnoreCase("DELETE")){
			
		response =reqspec.when().delete(resourceAPI.getResource());
		sc.log(resource+" Http Request Post Sucessfuly");
		}	
	  
	}

	@Then("user receive valid HTTP response code {int}")
	public void user_receive_valid_http_response_code(Integer Sucesscode) {
		
		
		response.then().spec(responseSpecification(Sucesscode));
		sc.log("Http Response Code is : "+Sucesscode);
	   
	}
	
	@Then("user validate {string} currency in response body as {string}")
	public void user_validate_currency_in_response_body_as(String key, String value) {
	
		response.then().assertThat().body(key, equalTo(value));
		sc.log("Base Currency in response Body is : "+value);
		
	}

	@Then("user validate {string} in response body as Latest date")
	public void user_validate_in_response_body_as_Latest_date(String key) {
	    
		response.then().assertThat().body(key, equalTo(getyesterdayDate()));
		sc.log("Date for which Foreign currency exchange rates reflect in response : "+getyesterdayDate());
				
	}

	@Then("user validate count of total currencies under {string} in response body is {string}")
	public void user_validate_count_of_total_currencies_under_in_response_body_is(String key, String count) {
		
		String sum = getJsonResponSeValuesCount(response, key+".size()");
		assertEquals(sum,count);
		sc.log("Count of total currencies is : "+sum);
		
	}

	@Given("user set Latest ForeignExchangeRates api with param {string} as {string}")
	public void user_set_latest_foreign_exchange_rates_api_with_param_as(String parameterName, String parameterValues) throws IOException {
		
		reqspec = given().spec(requestSpecification()).param(parameterName, parameterValues);	
		sc.log("Http Request is created Sucessfully with Param "+parameterName+" Values");   
	}

	@Given("user set Latest ForeignExchangeRates api with param {string} as {string} & {string} as {string}")
	public void user_set_latest_foreign_exchange_rates_api_with_param_as_as(String paramName1, String paramValues1, String paramName2, String paramValues2) throws IOException {
	   
		reqspec = given().spec(requestSpecification()).param(paramName1, paramValues1).param(paramName2, paramValues2);
		
		sc.log("Http Request is created Sucessfully with Param "+paramName1+"&"+paramName2+" Values");
		
	}
	
	@Then("user validate {string} target currency in response body as {string}")
	public void user_validate_target_currency_in_response_body_as(String key, String value) {
		
		 String curr =  getJsonResponSeValue(response, key);
		 String currency = curr.substring(1,4);
		 assertEquals(currency, value);
				
		 sc.log("target Currency in response Body is : "+value);	
	}
	
	@Given("user set Specific date ForeignExchangeRates api")
	public void user_set_specific_date_foreign_exchange_rates_api() throws IOException {
	 
		reqspec = given().spec(requestSpecification());
		sc.log("Http Request is created Sucessfully ");
	}

	@Then("user validate {string} in response body as {string} date")
	public void user_validate_in_response_body_as_date(String key, String val) {
	   
		String date = getJsonResponSeValue(response, key);
		assertEquals(date, val);
		sc.log("Date for which Foreign currency exchange rates reflect in response : "+val);
		
	}
	
	@Given("user set Specific date ForeignExchangeRates api with param {string} as {string}")
	public void user_set_specific_date_foreign_exchange_rates_api_with_param_as(String paramName, String paramValues) throws IOException {
		
		reqspec = given().spec(requestSpecification()).param(paramName, paramValues);	
		sc.log("Http Request is created Sucessfully with Param "+paramName+" Values");   
		
	}
	
	
	@Given("user set Specific date ForeignExchangeRates api with param {string} as {string} & {string} as {string}")
	public void user_set_specific_date_foreign_exchange_rates_api_with_param_as_as(String paramName1, String paramValues1, String paramName2, String paramValues2) throws IOException {
		   
			reqspec = given().spec(requestSpecification()).param(paramName1, paramValues1).param(paramName2, paramValues2);
			sc.log("Http Request is created Sucessfully with Param "+paramName1+"&"+paramName2+" Values");
	}

	@Then("user receive {string} validation message {string} in response body")
	public void user_receive_validation_message_in_response_body(String key, String message) {
		
		String valdMsg =  getJsonResponSeValue(response, key);	
		assert(valdMsg.contains(message));
		sc.log("validation message : Symbols 'EUR' are invalid for date xxxx-xx-xx");
	}

	
}
