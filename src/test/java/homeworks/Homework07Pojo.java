package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.H07_Actual_Pojo;
import pojos.H07_Expected_Pojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Homework07Pojo extends ReqresBaseUrl {
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
        H07_Expected_Pojo expectedDataPojo=new H07_Expected_Pojo("morpheus","leader");

        Response responsePojo = given().spec(spec).contentType(ContentType.JSON).body(expectedDataPojo).when().post("/{first}");
        responsePojo.prettyPrint();
        //Do Assertion


        H07_Actual_Pojo actualDataPojo=responsePojo.as(H07_Actual_Pojo.class);
        System.out.println(actualDataPojo);

        assertEquals(201,responsePojo.statusCode());
        assertEquals(expectedDataPojo.getName(),actualDataPojo.getName());
        assertEquals(expectedDataPojo.getJob(),actualDataPojo.getJob());

        //Soft Assertion
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(201,responsePojo.statusCode());
        softAssert.assertEquals(actualDataPojo.getName(),expectedDataPojo.getName(),"name value does not match");
        softAssert.assertEquals(actualDataPojo.getJob(),expectedDataPojo.getJob(),"job value does not match");
        //softAssert.assertEquals(actualDataPojo.getId(),555,"id value does not match");
        //softAssert.assertEquals(actualDataPojo.getCreatedAt(),"2022-10-04T15:18:56.372Z","createdAt value does not match");
        softAssert.assertAll();



    }
}
