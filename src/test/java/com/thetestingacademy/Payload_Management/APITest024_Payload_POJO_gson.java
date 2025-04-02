package com.thetestingacademy.Payload_Management;


import com.thetestingacademy.Payload_Management.gson.Booking;
import com.thetestingacademy.Payload_Management.gson.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest024_Payload_POJO_gson {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
@Test
    public void testPostRequest() {

    /*
    {
    "firstname" : "Riya",
    "lastname" : "Dash",
    "totalprice" : 2500,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2025-03-07",
        "checkout" : "2025-03-09"
    },
    "additionalneeds" : "Breakfast"
}
     */
Booking booking = new Booking();
booking.setFirstname("Radha");
booking.setLastname("Tiwari");
booking.setTotalprice(2500);
booking.setDepositpaid(true);

Bookingdates bookingDates = new Bookingdates();
bookingDates.setCheckin("2025-05-10");
bookingDates.setCheckout("2025-05-15");
booking.setBookingdates(bookingDates);
booking.setAdditionalneeds("Breakfast");

    System.out.println(booking);
    //Convert JSON->HashMap

    requestSpecification = RestAssured.given();
    requestSpecification.baseUri("https://restful-booker.herokuapp.com");
    requestSpecification.basePath("/booking");
    requestSpecification.contentType(ContentType.JSON);
    requestSpecification.body(booking);

    response= requestSpecification.when().post();

    validatableResponse =response.then().log().all();
    validatableResponse.statusCode(200);

    Integer bookingId = response.then().extract().path("bookingid");
    System.out.println("Booking ID->"+bookingId);

}

}

