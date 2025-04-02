package com.thetestingacademy.Payload_Management;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Objects;

public class APITest023_Payload_Map {
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

    //Convert JSON->HashMap
Map<String, Object> jsonMap = new LinkedHashMap();
    jsonMap.put("firstname","Riya");
    jsonMap.put("lastname","Dash");
    jsonMap.put("totalprice","2500");
    jsonMap.put("depositpaid","true");
    jsonMap.put("lastname","Dash");
    jsonMap.put("lastname","Dash");

    Map<String, Object> bookingDatesMap=new LinkedHashMap();
    bookingDatesMap.put("checkin","2025-03-07");
    bookingDatesMap.put("checkout","2025-03-09");

    jsonMap.put("bookingdates",bookingDatesMap);
    jsonMap.put("additionalneeds","Breakfast");

    System.out.println(jsonMap);

 requestSpecification = RestAssured.given();
 requestSpecification.baseUri("https://restful-booker.herokuapp.com");
 requestSpecification.basePath("/booking");
 requestSpecification.contentType(ContentType.JSON);
 requestSpecification.body(jsonMap);

 response= requestSpecification.when().post();

 validatableResponse =response.then().log().all();
 validatableResponse.statusCode(200);

 String bookingId = response.then().extract().path("bookingid");
    System.out.println("Booking Id is ->"+bookingId);


}

}

