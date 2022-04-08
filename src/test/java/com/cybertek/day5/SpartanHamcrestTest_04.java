package com.cybertek.day5;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHamcrestTest_04 extends SpartanTestBase
{
  @DisplayName("GET/ spartan/search and chaining together")
  @Test
  public void test1(){

      // we can save any information and get it with extract()
      List<String> names = given().accept(ContentType.JSON)
              .and()
              .queryParams("nameContains","j",
                      "gender","Male")
              .when()
              .get("/api/spartans/search")
              .then()
              .statusCode(200)
              .and()
              .body("totalElement",greaterThanOrEqualTo(3))
              .extract().response().jsonPath().getList("content.name");
                //extract is kind of magical key word that it can help us to get whatever we want
      System.out.println(names);

  }

    @DisplayName("GET/ spartan/search and chaining together")
    @Test
    public void test2(){

        // we can save any information and get it with extract()
        //save status code

        int statusCode = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains","j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement",greaterThanOrEqualTo(3))
                .extract().response().statusCode();
        //extract is kind of magical key word that it can help us to get whatever we want
        // extract is allows us chaining as much as we you want
        System.out.println(statusCode);

    }
}
