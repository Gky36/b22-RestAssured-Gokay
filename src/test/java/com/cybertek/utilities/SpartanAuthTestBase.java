package com.cybertek.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanAuthTestBase
{
    @BeforeAll
    public static void init(){
        RestAssured.baseURI  = "http://54.92.248.102:7000";

    }
}
