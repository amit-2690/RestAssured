package OAuthSerilization;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest {

	public static void main(String[] args) {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI="https://rahulshettyacademy.com";

		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		ArrayList<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);
		
		Response res=given().log().all().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response();

		String responseString=res.asString();
		System.out.println(responseString);













	}

}
