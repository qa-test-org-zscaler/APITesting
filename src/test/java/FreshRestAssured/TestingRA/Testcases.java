package FreshRestAssured.TestingRA;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class Testcases {
	
	ResponseSpecification spec = null;
	@BeforeClass
	public void setSpecification() {
		spec = RestAssured.expect();
		spec.contentType(ContentType.JSON);
		spec.statusCode(200);
		spec.statusLine("HTTP/1.1 200 OK");
	}
	
	@Test
	public void getListOfUsers() {
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		String message = given().when().get("api/v1/employees").then().spec(spec).extract().path("message");
		System.out.println(message);
		if (message.equals("Successfully! All records has been fetched.")) {
			System.out.println("Test case passed");
		}
		else
		{
			System.out.println("Test case failed");
		}
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void CreateUser() {
		JSONObject params = new JSONObject();
		params.put("name", "Jacob");
		params.put("age", "20");
		params.put("salary", "200000");
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		Response response = given().header("Content-Type", "application/json").body(params.toJSONString()).post("api/v1/create");
		System.out.println("The status received: " + response.asString());


	}
	
	@Test
	public void getSpecificUser() {
		//ResponseSpecification spec2 = RestAssured.expect();
		//spec2.contentType(ContentType.JSON);
		//spec2.statusCode(200);
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		String message = given().when().get("api/v1/employee/5777").then().spec(spec).extract().path("message");
		System.out.println(message);
		
	}
	
	
	@Test
	public void DeleteUser() {
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		Response response = given().delete("/api/v1/delete/2");
		System.out.println("The status received: " + response.asString());

	}
	
	@Test
	public void UpdateUser() {
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		Response response = given().put("/api/v1/update/21");
		System.out.println("The status received: " + response.asString());
	}
	
	

	

}
