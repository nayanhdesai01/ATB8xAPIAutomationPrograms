package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITest013_TestNG_Priority {
    @Test(priority = 1)
    public void getToken(){

        System.out.println("Token");
    }
    @Test(priority = 2)
    public void getId(){

        System.out.println("Id");
    }

    @Test(priority = 3)
    public void test_PUT(){
        System.out.println("Put");
    }
}

