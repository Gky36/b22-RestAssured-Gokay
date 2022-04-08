package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests
{
    String baseUrl = "http://54.157.183.136:8000";

    // Given Accept type application/json
    // When user send GET request to api/spartans end point
    // Then status code must 200
    // Then response Content Type must be application/json
    // And response body should include spartan result

    @Test
    public void test1(){

        // response object keep your body and status code
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        // printing status code from response object
        System.out.println(response.statusCode());

        // printing response content type from response object
        // they want to see what type of content we have
        System.out.println("response.contentType() = " + response.contentType());

        //printing whole result body
        response.prettyPrint();

        //how to do API testing them ?
        // verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content is application/json
        Assertions.assertEquals(response.contentType(),"application/json");
    }

    /*

        Given accept is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */
    @DisplayName("GET one spartan/api/spartans/3 and verifty")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl + "/api/spartans/3");

        //verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        // verify content type
        Assertions.assertEquals("application/json",response.contentType());

        //We dont know how to save it json so I assume whole body as String
        // verify json body contains Fidole
        // ASSERT TRUe kullanmamizin nedeni contains methods bize boolean donduruuyor
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

     /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */
    @DisplayName("Get request to /api/hello")
    @Test
    public void test3(){
        // sende request and save response inside the response object
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        // verify we have headers named date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        // how to get and header from response using header key?
        // in order to get header we have to use headers method
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println(response.header("Date"));

        // verify content length is 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        // verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta",response.body().asString());
    }
}
