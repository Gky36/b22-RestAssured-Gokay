package com.cybertek.day6;

import com.cybertek.pojo.Search;
import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase
{
    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo(){

        Response response = given()
                                .accept(ContentType.JSON)
                                 .and().pathParam("id", 15)
                            .when()
                                 .get("/api/spartans/{id}")
                            .then()
                                  .statusCode(200)
                                  .log().all()
                                  .extract().response();
        // De serialize --> JSON to POJO (java custom class)
        // 2 different way to do this
        // 1 using as() method

        // we need a Spartan class in order to user it as
        // we convert json response to spartan object with the help of jackson
        // as() method uses jackson to de serialize (Converting JSON to Java class)
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

        // second way of deserialize json to java
        // 2.using JsonPath to deserialize to custom class

        JsonPath jsonPath = response.jsonPath();

        // we do not provide any path, because it will automatically converted
        Spartan s15 = jsonPath.getObject("", Spartan.class);

        System.out.println(s15);
        System.out.println(s15.getName());
        System.out.println(s15.getPhone());
    }

    @DisplayName("GET one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){
        ///spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //get the first spartan from content list and put inside spartan object
        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);

        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getGender() = " + s1.getGender());
    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getContent().get(0).getName());
    }

    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){
        List<Spartan> spartanList = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "a",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("content", Spartan.class);

        System.out.println(spartanList.get(1).getName());

    }
}
/*
So we have converted our json response to pojo class which is our custom class that we created
for Spartan. this is also called deserialization because we convert JSON to JAVA at the end.
We have learned 2 different way to get this conversion

1. response.as(Classname.class) method where we give the custom class as a conversion type
    Spartan spartan15 = response.as(Spartan.class);
This method will work if we have Jackson(Databind) or Gson libraries in our pom.xml because they are the one who
does deserialization. When we look definition of the as() method it says if you add one of those
I will do conversation for you.

2. WAY
    is using JsonPath object getter like getObject
    Spartan s15 = jsonPath.getObject("", Spartan.class);
It also uses Jackson or Gson, but the benefits of using this method, while we do deserialization
we can also give path.

Scenario:
    When we use /api/spartan/search endpoint we got result in following format
    so we have one json object, consist of 2 keys, content and totalElement
    content is keeping multiple spartan json objects
    {
        content:[{},{}],
        totalElement:3
    }
  so the questions the question is how can we save first spartan jsonObject inside the content of array?

  to get this one in one shot, we need to point first element of content and also do deserialozation to Spartan
  class. so what we used is jsonPath.getObject method where we have option to provide path and classType at the same name.

  // get the first spartan from content list and put inside spartan object
    Spartan s1 = jsonPath.getObject("content[0],Spartan.class");

    as you see above, we pointed the first element and we tell to convert it to spartan class.
    response.as() method does not have option to use path and class time at the same time, that is the reasons
    JSonPath sometimes could be more useful.
 */