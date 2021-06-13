import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class Base {

	public static void main(String[] args) {
		// given -- all input detail
		// when -- Submit the API / type of Request / resource and http method
		// then -- validate the response
		
		// Test Case -- Validate the add Place API
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given ().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json").
		body(payload.addPlace()).when().post("/maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).body("scope",equalTo ("APP"))
			.header("Server", "Apache/2.4.18 (Ubuntu)");
		

	}

}
