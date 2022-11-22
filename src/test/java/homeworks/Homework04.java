package homeworks;

import base_urls.HerOkuAppBaseUrl;
import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Homework04 extends HerOkuAppBaseUrl {
 /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */

    @Test
    public void homework04() {
        //Set the url
        spec.pathParams("first","booking")
                .queryParams("firstname","Brandon","lastname","Wilson");
        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion
        assertEquals(200,response.getStatusCode());

        assertTrue(response.asString().contains("bookingid"));

    }


}
