import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUseableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;


public class DynamicJson {
	
	@Test(dataProvider = "BooksData")
	public void AddBook(String isbn, String aisle)
	{
		// to avoid SSL Handshake Exception --> Certificate issue.
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response = given().log ().all().header("Content-Type","application/json")
		.body(payload.AddBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().asString();
		
		JsonPath js = ReUseableMethods.rawToJson(response);
		String id = js.get("ID");
		
		System.out.println("Book ID is "+ id);
		
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData ()
	{
		//multidimensional array = Collection of array;
		return new Object [] [] {{"abcde","0099"},{"stuvw","0011"},{"defgh","0111"}};
	}

}
