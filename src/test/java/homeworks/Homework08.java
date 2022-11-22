package homeworks;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.H08_User;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Homework08 extends PetStoreBaseUrl {
   /*
        Type automation code to create a 'user' by using "https://petstore.swagger.io/"" documantation.
        https://petstore.swagger.io/user/createWithList

[
  {
    "id": 100,
    "username": "Saban",
    "firstName": "Saban",
    "lastName": "AZAK",
    "email": "saban@azak.com",
    "password": "12345",
    "phone": "226",
    "userStatus": 1
  }
]
    */

    @Test
    public void homework08_Create() {
        //Set the url
        spec.pathParams("first","v2","second","user");

        H08_User user1 = new H08_User("sabanazak","Saban","AZAK","saban@azak.com","123456","+1(226) 226-2626",0);

        System.out.println("Createing Single User");
        Response response = given().spec(spec).contentType(ContentType.JSON).body(user1).when().post("{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        //createWithList
        System.out.println();
        System.out.println("Createing 2 Users With createWithList");
        spec.pathParams("first","v2","second","user","third","createWithList");
        H08_User user2 = new H08_User("sazak","Shaban","KAZAK","azak@azak.com","654321","+1(222) 222-222",0);
        List<H08_User> users= Arrays.asList(user1,user2);

        Response responseList = given().spec(spec).contentType(ContentType.JSON).body(users).when().post("{first}/{second}/{third}");
        responseList.prettyPrint();

        responseList.then().assertThat().statusCode(200);


        //createWithArray
        System.out.println();
        System.out.println("Createing 3 Users With createWithArray");
        spec.pathParams("first","v2","second","user","third","createWithList");
        H08_User user3 = new H08_User("dummyuser","Dummy","User","dummy@user.com","2222","+1(333) 333-3333",0);

        H08_User[] usersArray = {user1, user2,user3};

        Response responseArray = given().spec(spec).contentType(ContentType.JSON).body(usersArray).when().post("{first}/{second}/{third}");
        responseArray.prettyPrint();

        responseArray.then().assertThat().statusCode(200);

    }

    @Test
    public void homework08_Get() {
        //spec.pathParams("first","v2","second","user","third","sabanazak");
        spec.pathParams("first","v2","second","user","third","sazak");
        //spec.pathParams("first","v2","second","user","third","dummyuser");

        Response response = given().spec(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();
    }

}
