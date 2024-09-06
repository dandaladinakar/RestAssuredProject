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
	
		
//		JSONObject data = new JSONObject();
//		
//		data.put("name", "Dinakar");
//		data.put("job", "QA");
//		Response response = 
//		 
//		RestAssured
//		.given()
//		.contentType(ContentType.JSON)
//		.body(data.toString())
//		.baseUri("https://reqres.in/api/users")
////		.log().headers() //print the Request Headers
////		.log().body() //{Print the Request body
////		.log().all() // Print alll the Details
//		.when()
//		.post()
//		.then() 
//		.log().body()
//		//.log().ifValidationFails()
//		//.body("data.name", Matchers.equalToCompressingWhiteSpace("Dinakar"))
//		.statusCode(201)
//		.extract()
//		.response();
//		
//		int bookingId = response.path("id"); // Changed this to String
//
//	    RestAssured
//	        .given()
//	        .contentType(ContentType.JSON)
//	        .pathParam("BookingID", bookingId)
//	        .baseUri("https://reqres.in/api/users/{bookingId}")
//	        .when()
//	        .get()
//	        .then()
//	        .assertThat()
//	        .statusCode(200);
//	}
	@Test(priority = 2)
	
	public void CreatePet() {
		
		//
        JSONObject data = new JSONObject();
        data.put("name", "Dinakar");
        data.put("job", "QA");

        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(data.toString())
            .baseUri("https://reqres.in/api/users")
            .when()
            .post()
            .then()
            .log().body()
            .statusCode(201)
            .extract()
            .response();

        // Extract the id from the response
        Object idObject = response.path("id");
        System.out.println("Extracted ID Object: " + idObject);

        int userId;
        try {
            // Ensure the id is properly converted to an integer
            userId = Integer.parseInt(idObject.toString());
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse 'id': " + idObject);
            e.printStackTrace();
            return;
        }

        System.out.println("User ID: " + userId); // Print the userId for debugging

        // Get the created pet by ID
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .pathParam("userId", userId) // Use pathParam to substitute {userId} in the URL
            .baseUri("https://reqres.in/api/users/{idObject}") // Correctly use pathParam placeholder
            .when()
            .get()
            .then()
            .assertThat()
            .statusCode(200);
    }
}