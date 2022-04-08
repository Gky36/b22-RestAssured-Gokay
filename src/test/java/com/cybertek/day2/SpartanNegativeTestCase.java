package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeTestCase
{
    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://54.157.183.136:8000";
    }

    /*
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
     */
    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test(){

        Response response = given().accept(ContentType.XML).
                when()
                .get("api/spartans/10");
        // verify status code is 406
        Assertions.assertEquals(406,response.statusCode());

        // verify content type
        assertEquals("application/xml;charset=UTF-8",response.contentType());

    }
}
