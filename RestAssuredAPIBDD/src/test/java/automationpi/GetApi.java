package automationpi;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.When;
import com.github.javafaker.Faker;
import com.test.apitesting.utils.BaseTest;
import com.test.apitesting.utils.pojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class GetApi extends BaseTest
{
	
	
	@Test(priority = 1)
	public void getAllBooking()
{
		
		Response response =
	RestAssured 
	.given()
	.contentType(ContentType.JSON)
	.baseUri("https://reqres.in/api/users/2")
	.when()
	.get()
	.then().and().log().all().and().statusCode(200).statusLine("HTTP/1.1 200 OK")
	.extract()
	.response();
		Assert.assertTrue(response.getBody().asString().contains("id"));
}
	
		
}