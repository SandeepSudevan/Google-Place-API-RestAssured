package StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleAPIBody;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification req;
	ResponseSpecification res;
	RequestSpecification reqgiven;
	Response response;
	static String placeID1;
	TestDataBuild testDatainput = new TestDataBuild();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String address, String language) throws IOException {

		reqgiven = given().spec(requestSpecification()).body(testDatainput.testData(name, address, language));

	}

	@When("user calls {string} with {string} htpp request")
	public void user_calls_with_htpp_request(String string, String string1) {
		APIresources resource = APIresources.valueOf(string);
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (string1.equalsIgnoreCase("POST")) {
			response = reqgiven.when().post(resource.resourceAPI()).then().spec(res).extract().response();
		} else if (string1.equalsIgnoreCase("GET")) {
			response = reqgiven.when().get(resource.resourceAPI()).then().spec(res).extract().response();
		}
	}

	@Then("the API call success with status code {int}")
	public void the_api_call_success_with_status_code(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {

		
		Assert.assertEquals(json(response,string), string2);

	}
	
	@Then("verify place_id created with {string} maps to {string}")
	public void verify_place_id_created_with_maps_to(String expectedname, String getAPI) throws IOException {
		placeID1 = json(response,"place_id");
		System.out.println(placeID1);
		reqgiven = given().spec(requestSpecification()).queryParam("place_id", placeID1);
		user_calls_with_htpp_request(getAPI,"GET");
		String name = json(response,"name");
		Assert.assertEquals(name, expectedname);
		
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		reqgiven = given().spec(requestSpecification()).body(testDatainput.deleteBody(placeID1));
	}
	
}
