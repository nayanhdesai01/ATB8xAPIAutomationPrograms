package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_003Integration {

    //Integration- Get existing booking with Get All booking IDs
    // request and the Update the booking
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingID;

    public String getToken(){
        String payload_token="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +

                "}";

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");;
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_token);

        response = requestSpecification.when().post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        return token;
    }
    @Test(priority = 1)
    public void test_GetAllIds(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        response = requestSpecification.when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Integer bookingID = response.then().extract().path("[2]");
        System.out.println("Selected: "+bookingID);
    }

}
