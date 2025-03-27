package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import  org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class APITest011_TestCaseIntegration {
    //Create a Token
    //Create a booking
    //Perform PUT request
    //Verify PUT is a success by GET Request
    //Delete ID
    //Verify it doesn't exist with GET Request

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    Integer bookingID;

    public String getToken(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload).log().all();

        //When - Response
        response=requestSpecification.when().post();
        //Then Validate Response
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        //Extract the token
        token=response.jsonPath().getString("token");
        System.out.println(token);

        assertThat(token).isNotNull().isAlphanumeric().isNotEmpty().isNotBlank();
        return token;
    }

    public Integer getBookingID() {

        String payload_POST = "{\n" +
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
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload_POST).log().all();


        // When - Response
        response=requestSpecification.when().post();

        //Then Validate Response
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        //Extract the booking Id
        bookingID = response.then().extract().path("bookingid");
                System.out.println(bookingID);
        return bookingID;
    }

    @Test (priority = 1)
    public void test_UpdateRequest_PUT(){
     token = getToken();
     bookingID = getBookingID();
       // System.out.println(token);
       // System.out.println(bookingID);

        String payload_PUT = " {\n" +
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
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload_PUT).log().all();

        // When - Response
        response = requestSpecification.when().put();
        //Then Validate Response
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

   @Test (priority = 2)
    public void test_UpdateRequest_GET(){
       requestSpecification.baseUri("https://restful-booker.herokuapp.com");
       requestSpecification.basePath("/booking/"+bookingID).log().all();
      response = requestSpecification.when().log().all().get();
      validatableResponse= response.then().log().all();
      validatableResponse.statusCode(200);

      String firstName = response.jsonPath().getString("firstname");
       Assert.assertEquals(firstName,"Amit");

    }
    @Test(priority = 3)
    public void test_deleteBooking(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        // When - Response
        response = requestSpecification.when().delete();
        //Then Validate Response
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201); //#TODO #1 - delete Bug
    }

    @Test(priority = 4)
    public void test_AfterDeletion_GET(){
        requestSpecification=RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        response = requestSpecification.when().log().all().get();
        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(404);
    }
}
