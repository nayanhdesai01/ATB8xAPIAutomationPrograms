package com.thetestingacademy.RestfullBooker_RestAssured.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_PUT_UpdateBooking {
    //URL  :https://restful-booker.herokuapp.com/auth
    //Header:Content-Type: application/json
    //Booking ID
    //Token
    //Payload:{
    //    "firstname" : "Amit",
    //    "lastname" : "Shah",
    //    "totalprice" : 4567,
    //    "depositpaid" : true,
    //    "bookingdates" : {
    //        "checkin" : "2018-09-10",
    //        "checkout" : "2019-09-17"
    //    },
    //    "additionalneeds" : "Breakfast"
    //}
    @Description("Verify the PUT Request to update the Booking.")
    @Test
    public void test_PUT_UpdateBooking() {
        String bookingID = "3873";
        String payloadPUT = " {\n" +
                "        \"firstname\": \"Dhruv\",\n" +
                "        \"lastname\": \"Sharma\",\n" +
                "        \"totalprice\": 5000,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2025-09-10\",\n" +
                "            \"checkout\": \"2025-09-17\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }";
        String token = "cd57543f4098183";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        // requestSpecification.auth().preemptive().basic("admin","password123");
        requestSpecification.cookie("token",token);
        requestSpecification.body(payloadPUT).log().all();

        Response response = requestSpecification.when().put();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
