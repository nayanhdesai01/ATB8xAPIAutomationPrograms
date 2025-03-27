package com.thetestingacademy.RestfullBooker_RestAssured.DELETE;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_DELETE_DeleteBooking {
    @Description("Verify the DELETE request to delete the Booking.")
    @Test
            public void test_DeleteBooking() {
        String token ="cd57543f4098183";
        String bookingID = "3873";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);

        // When - Response
        Response response = requestSpecification.when().delete();
        //Then Validate Response
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }
}
