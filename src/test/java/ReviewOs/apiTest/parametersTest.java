package ReviewOs.apiTest;


import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;





import org.testng.annotations.Test;

public class parametersTest {
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
    @Test
    public void pathTest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl+"/us/{zip}");

        // response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //  And Server header is cloudflare
        assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));
        //   And Report-To header exists
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        // post code is 22031
        assertEquals(response.path("\'post code\'"),"22031");
        assertEquals(response.path("country"),"United States");

        // country abbreviation is US
        assertEquals(response.path("\'country abbreviation\'"),"US");
        // place name is Fairfax
        assertEquals(response.path("places[0].\'place name\'"),"Fairfax");
        // second way
        assertEquals(response.path("places.\'place name\'[0]"),"Fairfax");

        // JSONPATH method to verify body
        // state is Virginia
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getString("places[0].state"),"Virginia");

        // latitude is 38.8604
        assertEquals(jsonPath.getString("places[0].latitude"),"38.8604");
    }
}
