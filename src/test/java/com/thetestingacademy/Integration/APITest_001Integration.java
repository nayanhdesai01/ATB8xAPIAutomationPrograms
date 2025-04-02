package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class APITest_001Integration {

    //Integration - Create a Token
    // Create a Booking,
    // Update Booking,
    // Get booking ID
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token,firstName,lastName;
    Integer bookingID;

    public String getToken() {
        String payload_POST_Token = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +

                "}";
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST_Token);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        token = response.then().extract().path("token");
        assertThat(token).isNotNull().isNotBlank().isNotEmpty().isAlphanumeric();
        System.out.println("In GetToken: "+token);
        return token;
    }

    public Integer getBookingID() {
        String payload_POST_Create = "{\n" +
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
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST_Create);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingID = response.then().extract().path("bookingid");
        firstName = response.then().extract().path("booking.firstname");
        lastName = response.then().extract().path("booking.lastname");

        assertThat(bookingID).isNotZero().isPositive().isNotNull();
        assertThat(firstName).isEqualTo("Disha").isNotBlank().isNotNull().isNotEmpty().isAlphabetic();
        assertThat(lastName).isEqualTo("Pant").isNotEmpty().isNotBlank().isNotNull().isAlphabetic();

        System.out.println("In GetBookingId: "+bookingID);
        return bookingID;
    }

    @Test(priority = 1)
    public void test_PUT_UpdateRequest() {
        token = getToken();
        bookingID = getBookingID();

        String payload_PUT_Update = " {\n" +
                "        \"firstname\": \"Amit\",\n" +
                "        \"lastname\": \"Shah\",\n" +
                "        \"totalprice\": 4567,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2025-09-10\",\n" +
                "            \"checkout\": \"2025-09-17\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload_PUT_Update);

        response = requestSpecification.when().put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

         firstName = response.then().extract().path("firstname");
         lastName = response.then().extract().path("lastname");

        System.out.println("PUT Booking id:"+bookingID);
        System.out.println("PUT First:"+firstName);
        System.out.println("PUT Lst"+lastName);

            assertThat(bookingID).isNotZero().isPositive().isNotNull();
            assertThat(firstName).isEqualTo("Amit").isNotEmpty().isNotBlank().isNotNull();
            assertThat(lastName).isEqualTo("Shah").isNotNull().isNotNull().isNotEmpty();

    }

    @Test(priority = 2)
    public void test_UpdateRequest_GET() {
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        response = requestSpecification.when().log().all().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

     //   bookingID = response.then().extract().path("bookingid");
         firstName = response.then().extract().path("firstname");
         lastName = response.then().extract().path("lastname");

        System.out.println("GET Booking id:"+bookingID);
        System.out.println("GET First:"+firstName);
        System.out.println("GET LAst"+lastName);

        assertThat(bookingID).isNotZero().isPositive().isNotNull();
        assertThat(firstName).isEqualTo("Amit").isNotEmpty().isNotNull().isNotBlank();
        assertThat(lastName).isEqualTo("Shah").isNotEmpty().isNotNull().isNotBlank();


    }
}