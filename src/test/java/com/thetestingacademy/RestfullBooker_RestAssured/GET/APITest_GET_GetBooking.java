package com.thetestingacademy.RestfullBooker_RestAssured.GET;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_GET_GetBooking {
    //URL:https://restful-booker.herokuapp.com/booking/:id

    @Description("Verify the booking details are shown for the given booking ID")
    @Test
    public void test_GET_GetBookingIDs(){
        RequestSpecification r = RestAssured.given();
        String bookingID = "1";
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/"+bookingID);
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
}
