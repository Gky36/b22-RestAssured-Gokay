package com.cybertek.day10;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class SSLTest
{
    @Test
    public void test1(){
        // SSLHandshakeException for untrusted website
        // unable to ffifnd valid certicifaction path basically it is not safe so there is a method
        given().
                relaxedHTTPSValidation().
                when().get("https://untrusted-root.badssl.com")
                .prettyPrint();

    }
    @Test
    public void keyStore(){
        given()
                .keyStore("pathtofile","password")
        .when().get("apiurl");
    }
}
