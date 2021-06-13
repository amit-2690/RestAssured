import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	@Test
	public void SumofCourses()
	{
		JsonPath js=new JsonPath(payload.CourseFee());
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		int count = js.getInt("courses.size ()");
		System.out.println(count);
		int totalsum = 0;
		for (int i = 0; i< count; i++)
		{
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			totalsum = totalsum+amount;
			
		}
		System.out.println("Total Sum is "+ totalsum);
		int totalpurchaseamount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(totalsum, totalpurchaseamount);
	}

}
