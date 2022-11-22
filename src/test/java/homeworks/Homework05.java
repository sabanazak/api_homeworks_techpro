package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Homework05 extends ReqresBaseUrl {
   /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
            "data": {
                "id": 3,
                "name": "true red",
                "year": 2002,
                "color": "#BF1932",
                "pantone_value": "19-1664"
            },
            "support": {
                "url": "https://reqres.in/#support-heading",
                "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
            }
        }
      */

    @Test
    public void homework05() {
        //Set the url
        spec.pathParams("first","unknown","second",3);

        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //1.Way
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id",equalTo(3),
                        "data.name",equalTo("true red"),
                        "data.year",equalTo(2002),
                        "data.color",equalTo("#BF1932"),
                        "data.pantone_value",equalTo("19-1664"),
                        "support.url",equalTo("https://reqres.in/#support-heading"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        //2.Way
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json");

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getString("data.color"));

        //Soft Assertion
        //1st: Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();

        //2nd: Do Assertion
        softAssert.assertEquals(jsonPath.getInt("data.id"),3,"data.id did not match");
        softAssert.assertEquals(jsonPath.getString("data.name"),"true red","data.name did not match");
        softAssert.assertEquals(jsonPath.getInt("data.year"),2002,"data.year did not match");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","data.color did not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664","data.pantone_value did not match");
        softAssert.assertEquals(jsonPath.getString("support.url"),"https://reqres.in/#support-heading","support.url did not match");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","support.text did not match");

        //We have to assetAll() method.
        // SoftAssert don't throw an exception when an assert fails, but it records the failure.
        // The test execution will continue with the next step after the assert statement.
        // Calling assertAll() will cause an exception to be thrown if at least one assertion failed.
        softAssert.assertAll();

    }


}
