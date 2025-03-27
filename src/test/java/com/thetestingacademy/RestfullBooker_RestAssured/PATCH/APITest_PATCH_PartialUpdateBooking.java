package com.thetestingacademy.RestfullBooker_RestAssured.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_PATCH_PartialUpdateBooking {
    //URL: https://restful-booker.herokuapp.com/booking/:id
    //Header: Content-Type: application/json
    //Cookie:token=abc123
    //{
    //    "firstname" : "James",
    //    "lastname" : "Brown"
    //}

    @Description("Verify the PATCH request to partially update the Booking.")
    @Test
    public void test_PUT_UpdateBooking() {
        String bookingID = "3873";
        String payload_PATCH = "{\n" +
                "        \"firstname\" : \"James\",\n" +
                "        \"lastname\" : \"Brown\"\n" +
                "    }";
        String token = "cd57543f4098183";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload_PATCH).log().all();

        Response response = requestSpecification.when().patch();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
