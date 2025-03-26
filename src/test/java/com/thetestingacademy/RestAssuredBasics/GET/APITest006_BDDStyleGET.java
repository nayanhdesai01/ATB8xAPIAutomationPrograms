package com.thetestingacademy.RestAssuredBasics.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITest006_BDDStyleGET {
    @Test
    public void test_GET_Request_Positive(){
        String pincode= "560037";

        RestAssured
                .given()
                    .baseUri("https://api.zippopotam.us")
                    .basePath("/IN/" + pincode)
                .when()
                    .log()
                    .all()
                    .get()
                .then()
                    .log()
                    .all()
                    .statusCode(200);
    }

    @Test
    public void test_GET_Request_Negative(){
        String pincode= "-1";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/" + pincode)
                .when()
                .log()
                .all()
                .get()
                .then()
                .log()
                .all()
                .statusCode(404);
    }
}