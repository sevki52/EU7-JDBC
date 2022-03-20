package ReviewOs.apiTest;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class DeserializationExamples {
    /*
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
  post code is 22031
  country  is United States
  country abbreviation is US
  place name is Fairfax
  state is Virginia
  latitude is 38.8604
   */
    String zipUrl = "http://api.zippopotam.us";
}
