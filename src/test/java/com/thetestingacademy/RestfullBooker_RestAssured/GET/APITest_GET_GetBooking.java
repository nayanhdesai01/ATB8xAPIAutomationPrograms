package com.thetestingacademy.RestfullBooker_RestAssured.GET;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

public class APITest_GET_GetBooking {
    //URL:https://restful-booker.herokuapp.com/booking/:id

    @Description("Verify the booking details are shown for the given booking ID")
    @Test
    public void test_GET_GetBookingIDs(){
        RequestSpecification requestSpecification = RestAssured.given();
        String bookingID = "1";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID).log().all();

        Response response =requestSpecification.when().get();

        ValidatableResponse validatableResponse= response.then().log().all();
        requestSpecification.then().log().all().statusCode(200);
    }
}
