package com.cybertek.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
public abstract class SpartanTestBase
{
    @BeforeAll
    public static void init(){
        baseURI =ConfigurationReader.getProperty("spartanUrl");

        String dbUrl = "jdbc:oracle:thin:@54.157.183.136:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

        //DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void teardown(){

        //DBUtils.destroy();
    }
}
