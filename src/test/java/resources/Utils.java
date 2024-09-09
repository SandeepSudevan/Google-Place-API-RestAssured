package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("log.txt"));

			req = new RequestSpecBuilder().setBaseUri(globalValues("baseURL")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String globalValues(String url) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\Eclipse project\\CucumberAPIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(url);
	}
	
	public String json(Response respone,String key) {
		String res = respone.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
		 
	}

}
