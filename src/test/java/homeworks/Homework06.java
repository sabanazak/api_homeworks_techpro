package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

public class Homework06 extends ReqresBaseUrl {
    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void homework06() {

        //Set the url
        spec.pathParams("first","unknown");

        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        //Do Assertion
        //1)Status code is 200
        response.then().assertThat().statusCode(200).body("data",hasSize(6));

        //2)Print all pantone_values
        List<String> pantoneValues=jsonPath.getList("data.pantone_value");

            //a.
            System.out.println("1");
            pantoneValues.stream().forEach(System.out::println);
            System.out.println("2");
            pantoneValues.stream().forEach(t-> System.out.println(t));
            //b.
            System.out.println("3");
            for(String pValue:pantoneValues) {
                System.out.println(pValue);
            }


        //3)Print all ids greater than 3 on the console
        //   Assert that there are 3 ids greater than 3
        //a.
            List<Integer> ids=jsonPath.getList("data.id");
            List<Integer> idsGraterThan3Lambda = ids.stream().filter(t->t>3).collect(Collectors.toList());
            System.out.println(ids.size());
            System.out.println(idsGraterThan3Lambda.size());

            assertEquals(3,idsGraterThan3Lambda.size());

        //b.
            List<Integer> idsGreaterThan3Groovy = jsonPath.getList("data.findAll{it.id>3}.id");
            System.out.println("idsGreaterThan3Groovy = " + idsGreaterThan3Groovy);
            Assert.assertEquals(3,idsGreaterThan3Groovy.size());

        //4)Print all names whose ids are less than 3 on the console
        //      Assert that the number of names whose ids are less than 3 is 2

        List<String> namesWhoseIdGraterLessThan3Lambda = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("namesWhoseIdGraterLessThan3Lambda = " + namesWhoseIdGraterLessThan3Lambda);
        namesWhoseIdGraterLessThan3Lambda.stream().forEach(System.out::println);

        Assert.assertEquals(2,namesWhoseIdGraterLessThan3Lambda.size());

    }

}
