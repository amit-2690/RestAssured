import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
	JsonPath js=new JsonPath(payload.CourseFee());
			//Print No of courses returned by API
			
	
	// 1. Print No of courses returned by API
	int count=	js.getInt("courses.size()");
	System.out.println(count);
	
	//2.Print Purchase Amount
	
	int purchaseamount = js.getInt("dashboard.purchaseAmount");
	System.out.println(purchaseamount);
	
	//3. Print Title of the first course
	
	System.out.println("1st course title is "+js.get("courses[0].title").toString());
	
	
	//4. Print All course titles and their respective Prices
	
	for (int i =0;i <count;i++ )
	{
		String coursetitle = js.get("courses["+i+"].title");
		int courseprice = js.getInt("courses["+i+"].price");
		System.out.println("Course Title and course price at "+ i+"th position is -->"+coursetitle +": " +courseprice);
			
	}
	
	//5. Print no of copies sold by RPA Course
	
	 System.out.println("Print no of copies sold by RPA Course");
	
	for (int i = 0;i< count;i++)
	{
		String courseTitle = js.get("courses["+i+"].title");
		{
			if (courseTitle.equalsIgnoreCase("RPA"))
			{
				int coursecopies= js.get("courses["+i+"].copies");
				System.out.println("RPA coursecopies no are "+coursecopies);
				break;
			}
		}
	}
		

	}

}
