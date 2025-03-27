package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITest017_TestNG_aftertest {
    @BeforeTest
    public void getToken(){

        System.out.println("Token");
    }
    @BeforeTest
    public void getId(){

        System.out.println("Id");
    }

    @Test
    public void test_PUT(){

        System.out.println("Put");
    }

    @AfterTest
    public void close(){
        System.out.println("Close All");
    }
}

