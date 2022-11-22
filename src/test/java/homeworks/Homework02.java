package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Homework02 extends ReqresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */

    @Test
    public void homework2() {
        spec.pathParams("first","users","second",23);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        assertEquals(404,response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found",response.getStatusLine());
        assertEquals("cloudflare",response.header("server"));

        JsonPath jsonPath=response.jsonPath();

        jsonPath.prettyPrint();

        response.then().assertThat().body("isEmpty()", Matchers.is(true));


    }

}
