package FreshRestAssured.TestingRA;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.hamcrest.core.IsEqual;
import static org.hamcrest.Matchers.equalTo;
import com.relevantcodes.extentreports.LogStatus;

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
		ExtentReportManager.createReport();
	}
	
	@Test
	public void getListOfUsers() {
		
		ExtentReportManager.test = ExtentReportManager.reports.startTest("getListOfUsers", "Gets the list of Users");
		try {
			
			RestAssured.baseURI = "https://dummy.restapiexample.com/";
			ExtentReportManager.test.log(LogStatus.INFO, "Configured the BASE URL", RestAssured.baseURI);
			//String message = given().when().get("api/v1/employees").then().spec(spec).extract().path("message");
			ExtentReportManager.test.log(LogStatus.INFO, "Make a GET API call", "api/v1/employees");
			//ExtentReportManager.test.log(LogStatus.INFO, "Extract the response message", message);
			//System.out.println(message);
			ExtentReportManager.test.log(LogStatus.INFO, "Expected Message -> ", "Successfully! All records has been fetched.");
			//ExtentReportManager.test.log(LogStatus.INFO, "Actual Message -> ", message);
			given().contentType(ContentType.JSON).when().get("api/v1/employees").then().body("message", equalTo("Successfully! All records has been fetched.")) ;
			
			ExtentReportManager.test.log(LogStatus.PASS, "Result", "PASS");
			
		}
		
		catch(Exception ex){
			
			ExtentReportManager.test.log(LogStatus.ERROR, "Exception Occured", ex.getMessage());
			ExtentReportManager.test.log(LogStatus.FAIL , "TC Failed", ex.getMessage());
			
		}
		
		
		/*if (message.equals("Successfully! All records has been fetched.")) {
			System.out.println("Test case passed");
		}
		else
		{
			System.out.println("Test case failed");
		}*/
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void CreateUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("CreateUser", "Create An User");
		JSONObject params = new JSONObject();
		params.put("name", "Jacob");
		params.put("age", "20");
		params.put("salary", "200000");
		ExtentReportManager.test.log(LogStatus.INFO, "Configured the JSON request body ", params.toJSONString());
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		ExtentReportManager.test.log(LogStatus.INFO, "Configured the BASE URL", RestAssured.baseURI);
		ExtentReportManager.test.log(LogStatus.INFO, "Make a POST API call", "api/v1/create");
		Response response = given().when().header("Content-Type", "application/json").body(params.toJSONString()).post("api/v1/create");
		
		ExtentReportManager.test.log(LogStatus.INFO, "Expected Status Code ", "200");
		ExtentReportManager.test.log(LogStatus.INFO, "Actual Status Code ", Integer.toString(response.statusCode()));
		


	}
	
	
	@Test
	public void getSpecificUser() {
		//ResponseSpecification spec2 = RestAssured.expect();
		//spec2.contentType(ContentType.JSON);
		//spec2.statusCode(200);
		ExtentReportManager.test = ExtentReportManager.reports.startTest("getSpecificUser", "Fetch record of One user");
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		ExtentReportManager.test.log(LogStatus.INFO, "Configured the BASE URL", RestAssured.baseURI);
		ExtentReportManager.test.log(LogStatus.INFO, "Make a GET API call", "api/v1/employee/<id>");
		ExtentReportManager.test.log(LogStatus.INFO, "Employee ID ", "22");
		ExtentReportManager.test.log(LogStatus.INFO, "Expected Message -> ", "Successfully! Record has been fetched.");
		try {
		given().when().get("api/v1/employee/22").then().assertThat().body("message", IsEqual.equalTo("Successfully! Record has been fetched."));
		//given().when().get("api/v1/employees").then().assertThat().body("message", IsEqual.equalTo("Successfully! All records has been fetched.")) ;
		//ExtentReportManager.test.log(LogStatus.INFO, "Actual Result", message);
		}
		
		catch(Exception ex){
			
			ExtentReportManager.test.log(LogStatus.ERROR, "Exception Occured", ex.getMessage());
			ExtentReportManager.test.log(LogStatus.FAIL , "TC Failed", ex.getMessage());
			
		}
		
		
		
	}
	
	
	@Test
	public void DeleteUser() {
		
		
		ExtentReportManager.test = ExtentReportManager.reports.startTest("DeleteSpecificUser", "Delete An User record");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		ExtentReportManager.test.log(LogStatus.INFO, "Configured the BASE URL", RestAssured.baseURI);
		ExtentReportManager.test.log(LogStatus.INFO, "Make a DELETE API call", "/api/v1/delete/<id>");
		ExtentReportManager.test.log(LogStatus.INFO, "Employee ID ", "2");
		ExtentReportManager.test.log(LogStatus.INFO, "Expected Message -> ", "Successfully! Record has been deleted");
		try {
		given().delete("/api/v1/delete/2").then().assertThat().body("message", IsEqual.equalTo("Successfully! Record has been deleted"));
		}
		
		catch(Exception ex){
			
			ExtentReportManager.test.log(LogStatus.ERROR, "Exception Occured", ex.getMessage());
			ExtentReportManager.test.log(LogStatus.FAIL , "TC Failed", ex.getMessage());
			
		}
		
		

	}
	
	
	@Test
	public void UpdateUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("UpdateUser", "Update An User record");

		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		
		ExtentReportManager.test.log(LogStatus.INFO, "Configured the BASE URL", RestAssured.baseURI);
		ExtentReportManager.test.log(LogStatus.INFO, "Make a PUT API call", "/api/v1/update/<id>");
		ExtentReportManager.test.log(LogStatus.INFO, "Employee ID ", "21");
		ExtentReportManager.test.log(LogStatus.INFO, "Expected Message -> ", "Successfully! Record has been updated.");
		try {
		given().put("/api/v1/update/21").then().assertThat().body("message", IsEqual.equalTo("Successfully! Record has been updated."));
		}
		
		catch(Exception ex){
			
			ExtentReportManager.test.log(LogStatus.ERROR, "Exception Occured", ex.getMessage());
			ExtentReportManager.test.log(LogStatus.FAIL , "TC Failed", ex.getMessage());
			
		}
		
		
	}
	
	
	@AfterClass
	public void endReport() {
		
		ExtentReportManager.reports.flush();
	}
	
	

	

}
