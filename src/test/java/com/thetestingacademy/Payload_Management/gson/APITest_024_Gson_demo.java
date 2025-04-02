package com.thetestingacademy.Payload_Management.gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class APITest_024_Gson_demo {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void testPositive(){
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

        Gson gson = new Gson();
        String jsonStringBooking=gson.toJson(booking);
        System.out.println(jsonStringBooking);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringBooking);

        Response response= requestSpecification.when().post();
        String jsonResponseString = response.asString();

        validatableResponse =response.then().log().all();
        validatableResponse.statusCode(200);

        //extract(),jsonpath.getString() ->For small response
        //When response is complex JSON or HUGE JSON
        //convert String ->Object

        BookingResponse bookingResponse = gson.fromJson(jsonResponseString, BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isPositive().isNotNull().isNotZero();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Radha").isNotNull().isNotEmpty().isNotBlank();
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Tiwari").isNotBlank().isNotEmpty().isNotNull();
        assertThat(bookingResponse.getBooking().getTotalprice()).isEqualTo(2500);
        assertThat(bookingResponse.getBooking().getDepositpaid()).isEqualTo(true);
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckin()).isEqualTo("2025-05-10").isNotNull().isNotEmpty().isNotBlank();
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckout()).isEqualTo("2025-05-15").isNotBlank().isNotNull().isNotEmpty();
        assertThat(bookingResponse.getBooking().getAdditionalneeds()).isNotEmpty().isNotNull().isNotBlank();
    }
}
