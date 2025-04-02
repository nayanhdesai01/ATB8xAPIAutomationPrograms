package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.contentOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class APITest_002Integration {

    //Integration- Create a booking, Delete the booking with ID,
    // try to Retrieve the deleted ID with get request
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingID;

    public String getToken(){
        String payload_Post_Token= "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +

                "}";
    requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
    requestSpecification.contentType(ContentType.JSON);
    requestSpecification.body(payload_Post_Token);

    response=requestSpecification.when().post();

    validatableResponse=response.then().log().all();
    validatableResponse.statusCode(200);
    return token;
}

    @Test(priority = 1)
    public void test_CreateBooking(){

        String payload_POST_CreateBooking = "{\n" +
                "    \"firstname\" : \"Disha\",\n" +
                "    \"lastname\" : \"Pant\",\n" +
                "    \"totalprice\" : 5000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-05-01\",\n" +
                "        \"checkout\" : \"2025-05-07\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        System.out.println("Start here");
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST_CreateBooking);

        response=requestSpecification.when().post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);


        bookingID=response.then().extract().path("bookingid");
        String firstName = response.then().extract().path("booking.firstname");
        String lastName = response.then().extract().path("booking.lastname");
        Boolean depositPaid = response.then().extract().path("booking.depositpaid");


        System.out.println("Booking ID:"+bookingID);
        System.out.println("First:"+firstName);
        System.out.println("Last :"+lastName);
        System.out.println("Deposit Paid:"+depositPaid);


assertThat(bookingID).isNotZero().isNotNull().isPositive();
assertThat(firstName).isNotEmpty().isNotNull().isNotBlank().isAlphabetic();
assertThat(lastName).isNotEmpty().isNotNull().isNotBlank().isAlphabetic();
assertThat(depositPaid).isTrue().isNotNull();

        token = getToken();
        }
        @Test(priority = 2)
    public void test_Delete(){
            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com");
            requestSpecification.basePath("/booking/"+bookingID);
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.cookie("token",token);

            response=requestSpecification.when().delete();

            validatableResponse= response.then().log().all();
            validatableResponse.statusCode(403);

        }

        @Test(priority =3)
    public void test_GetRequest_AfterDelete(){
            System.out.println("In GET now");
        requestSpecification=RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com");
            requestSpecification.basePath("/booking/"+bookingID);

            response =requestSpecification.when().get();

            validatableResponse=response.then().log().all();
            validatableResponse.statusCode(200);

        }
    }
