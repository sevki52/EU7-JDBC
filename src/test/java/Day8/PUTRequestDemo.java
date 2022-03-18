package Day8;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PUTRequestDemo {
    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }
}
