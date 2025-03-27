package com.thetestingacademy.RestfullBooker_RestAssured.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_POST_CreateBooking {
    //URL: https://restful-booker.herokuapp.com/booking
    //H: Content-Type: application/json
    //Payload: {
    //    "firstname" : "Amit",
    //    "lastname" : "Shah",
    //    "totalprice" : 4567,
    //    "depositpaid" : true,
    //    "bookingdates" : {
    //        "checkin" : "2025-09-06",
    //        "checkout" : "2025-09-10"
    //    },
    //    "additionalneeds" : "Breakfast"
    //}
    @Description("Verify the POST request to create a new booking.")
    @Test
    public void test_POST_CreateBooking(){
        String payload="{\n" +
                "    \"firstname\" : \"Amit\",\n" +
                "    \"lastname\" : \"Shah\",\n" +
                "    \"totalprice\" : 4567,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-09-06\",\n" +
                "        \"checkout\" : \"2025-09-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }
}
