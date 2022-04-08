package com.cybertek.day2;

import com.cybertek.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests extends HRTestBase
{



    @DisplayName("GET request to/regions")
    @Test
    public void test1(){
        Response response = get("/regions");

        System.out.println(response.statusCode());
    }

    /**
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */
    @DisplayName("Get request to /region/2")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/regions/2");

        // verify status code
        assertEquals(200,response.statusCode());
        // verify content type
        assertEquals("application/json",response.contentType());

        response.prettyPrint();
        //verify body contains Americas
        assertEquals(response.body().asString().contains("Americas"),true);

    }
}
