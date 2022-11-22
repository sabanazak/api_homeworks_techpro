package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework01 extends ReqresBaseUrl {
   /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void homework1() {
        //Set the url
        spec.pathParams("first","users","second",3);

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");

        //System.out.println();
        //System.out.println(ContentType.JSON);
        //System.out.println(response.getContentType());
        //System.out.println();


        //or
        assertEquals(200,response.statusCode());
        assertTrue(response.getContentType().contains(ContentType.JSON.toString()));
        assertEquals("HTTP/1.1 200 OK",response.getStatusLine());
    }



}
