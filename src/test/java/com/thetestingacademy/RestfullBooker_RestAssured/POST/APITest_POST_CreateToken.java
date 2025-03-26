package com.thetestingacademy.RestfullBooker_RestAssured.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_POST_CreateToken {
    //URL  :https://restful-booker.herokuapp.com/auth
    //Header:Content-Type: application/json
    //Payload:{
    //    "username" : "admin",
    //    "password" : "password123"
    //}
    @Description("Verify a new auth token is created.")
    @Test
    public void test_POST_CreateToken(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.log().all().body(payload);
        r.when().log().all().post();
        r.then().log().all().statusCode(200);
    }
}
