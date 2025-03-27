package com.thetestingacademy.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

import static org.assertj.core.api.Assertions.assertThat;

public class APITest022_Assertions_Real {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    Integer bookingID;
    String token;

    @Test
    public void test_Post() {
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
        response = requestSpecification.when().post();

        //Then Validate Response
        //Get Validate Response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Validatable Response - I - Hamcrest - RestAssured
        //Rest Assured Default - Hamcrest
        //import org.hamcrest.Matchers
        validatableResponse.body("booking.firstname",Matchers.equalTo("Disha"));
        validatableResponse.body("booking.lastname",Matchers.equalTo("Pant"));
        validatableResponse.body("booking.depositpaid",Matchers.equalTo(true));
        validatableResponse.body("bookingid",Matchers.notNullValue());


        bookingID = response.then().extract().path("bookingid");
        String firstName = response.then().extract().path("booking.firstname");
        String lastName = response.then().extract().path("booking.lastname");

        //TestNG Assertions
        //Hard assertion vs Soft assertion
        Assert.assertNotNull(bookingID);
        Assert.assertEquals(firstName,"Disha");
        Assert.assertEquals(lastName,"Pant");

        //Assert J(3rd Party lib to Assertions)

        assertThat(bookingID).isNotNull().isPositive().isNotZero();
        assertThat(firstName).isEqualTo("Disha").isNotNull().isNotBlank().isNotEmpty().isAlphanumeric();


    }
}
