package com.thetestingacademy.RestfullBooker_RestAssured.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
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
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();

        Response response = requestSpecification.when().log().all().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
