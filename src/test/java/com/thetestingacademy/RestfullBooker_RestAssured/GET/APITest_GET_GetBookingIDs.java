package com.thetestingacademy.RestfullBooker_RestAssured.GET;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_GET_GetBookingIDs {
    //URL:https://restful-booker.herokuapp.com/booking
    static RequestSpecification r = RestAssured.given();

    @Description("Verify all the booking IDs are returned")
    @Test
    public void test_GET_GetBookingIDs(){

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
}
