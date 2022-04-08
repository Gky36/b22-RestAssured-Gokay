package com.cybertek.day5;


import com.cybertek.utilities.HRTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
/*
Converting JSON Response to Java Collection/Data structure
as() will get value and convert it as(Map.class)
Deserialization which means converting JSON to JAVA
Jackson or Gson = those are libraries to deserialization and serialization. they are also known as
objectMapper, jsonparser, databinding libraries. we added dependencies
 */
public class JSONtoJAVATest_05 extends SpartanTestBase
{
    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanMap(){

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map
        // we got the response and convert to java map directly
        // in order to get that conversion we have to add to dependency in pom xml file
        Map<String,Object> jsonMap = response.as(Map.class);


        System.out.println(jsonMap.toString());
        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));

    }
    @DisplayName("GET all spartans to JAVA data structure ")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans")
                .then()
                .statusCode(200)
                .extract().response();

        // we need to convert it jason to java which is deserialize
        List<Map<String,Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);
    }
}
