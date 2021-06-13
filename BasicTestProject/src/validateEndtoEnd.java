import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUseableMethods;
import files.payload;

public class validateEndtoEnd {

	//Add Place --> Update Place with New Address --> Get Place to validate if New Address is present in the response;
	
	public static void main(String[]args) {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
		.body(payload.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo ("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
		
		System.out.println("Response is "+response);
		
		JsonPath js = new JsonPath (response);// For Parsing Json
		
		//System.out.println("js value is "+js);
		String placeId = js.getString("place_id");
		System.out.println("Place ID is "+ placeId);
		
		// Update Place
		
		System.out.println("Update Place is ***************");
		
		String placeAddress = "Deen Dayal Road, Bararuni";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+placeAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		System.out.println("Get Place is ***************");
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().statusCode(200).extract().response().asString();
		
		//JsonPath js1 = new JsonPath (getPlaceResponse);
		
		JsonPath js1= ReUseableMethods.rawToJson(getPlaceResponse);
		String newaddress = js1.getString("address");
		
		System.out.println(newaddress);
		
		Assert.assertEquals(newaddress, placeAddress);

	}

}
