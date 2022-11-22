package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.H07_Expected_Pojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Homework07 extends ReqresBaseUrl {
      /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    public void homework07() {
        //Set the url
        spec.pathParam("first","users");

        //Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("name","morpheus");
        expectedData.put("job","leader");

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));



    }
}
