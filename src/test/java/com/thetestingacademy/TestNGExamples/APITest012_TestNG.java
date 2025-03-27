package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITest012_TestNG {
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
}

