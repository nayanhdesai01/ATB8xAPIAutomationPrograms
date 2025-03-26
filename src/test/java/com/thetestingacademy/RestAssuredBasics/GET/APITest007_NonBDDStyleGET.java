package com.thetestingacademy.RestAssuredBasics.GET;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest007_NonBDDStyleGET {

    static RequestSpecification r = RestAssured.given();

    @Description("TC1-Non BDD Style GET Request Positive")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void test_GET_Request_Positive(){
        String pincode= "560037";

        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
    @Description("TC2-Non BDD Style GET Request Negative")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void test_GET_Request_Negative(){
        String pincode= "-1";
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
}
