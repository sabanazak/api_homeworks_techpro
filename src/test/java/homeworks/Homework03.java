package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.H03;
import pojos.H03_Data_Pojo;
import pojos.H03_Support_Pojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework03 extends ReqresBaseUrl {
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

    @Test
    public void homework03(){
        //Set the url
        spec.pathParams("first","users","second",2);

        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        /*
        {
            "data": {
                "id": 2,
                "email": "janet.weaver@reqres.in",
                "first_name": "Janet",
                "last_name": "Weaver",
                "avatar": "https://reqres.in/img/faces/2-image.jpg"
            },
            "support": {
                "url": "https://reqres.in/#support-heading",
                "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
            }
        }
         */

           /*

           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

        //1. Way
        response.then().assertThat().statusCode(200)
                .contentType("application/json")
                .body("data.email",equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),
                        "data.last_name",equalTo("Weaver"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));



        //2.Way
        JsonPath jsonPath = response.jsonPath();

        //Hard Assertion
        assertEquals(200,response.statusCode());
        assertTrue(response.getContentType().contains("application/json"));

        assertEquals("Janet",jsonPath.getString("data.first_name"));
        assertEquals("Weaver",jsonPath.getString("data.last_name"));
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",jsonPath.getString("support.text"));

        //3.Way : Pojo
        //ExpectedData
        H03_Data_Pojo data=new H03_Data_Pojo(2,"janet.weaver@reqres.in","Janet","Weaver","https://reqres.in/img/faces/2-image.jpg");
        H03_Support_Pojo support =new H03_Support_Pojo("https://reqres.in/#support-heading","To keep ReqRes free, contributions towards server costs are appreciated!");
        H03 h03=new H03(data,support);  //No need actually

        H03 actualData = response.as(H03.class);

        assertEquals(200,response.statusCode());
        assertTrue(response.getContentType().contains("application/json"));

        assertEquals(data.getEmail(),actualData.getData().getEmail());
        assertEquals(data.getFirst_name(),actualData.getData().getFirst_name());
        assertEquals(data.getLast_name(),actualData.getData().getLast_name());
        assertEquals(support.getText(),actualData.getSupport().getText());


    }


}
