package com.thetestingacademy.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class ApiTest009_NonBDDStylePOST {
    @Description("Verify the Post Request_Non BDD Style")
    @Test
    public void test_Post_NonBDDStyle(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.log().all().body(payload);

        requestSpecification.when().log().all().post();
        requestSpecification.then().log().all().statusCode(200);


    }
}
