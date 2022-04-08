package com.cybertek.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HRTestBase
{
    @BeforeAll
    public static void init(){
        RestAssured.baseURI =ConfigurationReader.getProperty("hrUrl");
    }
}
