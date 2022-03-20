package ReviewOs.apiTest;

import Day6_POJO.Spartan;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class POJOTests {
    String spartanUrl = "http://54.91.210.3:8000";
    String zipUrl = "http://api.zippopotam.us";
    @Test
    public void spartanTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",7)
                .when().get(spartanUrl+"/api/spartans/{id}");

        //  response.prettyPrint();

        // De-serialization, JSON response into Spartan object
        Spartan spartan7 = response.body().as(Spartan.class);

        System.out.println("spartan7.getName() = " + spartan7.getName());


    }

    @Test
    public void ZipTestWithPOJO(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl+"/us/{zip}");

        Postcode zip22031 = response.body().as(Postcode.class);
        System.out.println("zip22031.getCountry() = " + zip22031.getCountry());
        //second way to do test
        Gson gson = new Gson();
        Postcode zip220311 = gson.fromJson(response.body().asString(),(Postcode.class));
        System.out.println("zip220311.getCountry() = " + zip220311.getCountry());


    }


}
