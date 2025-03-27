package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.Test;

public class APITest020_TestNG_AlwaysRun {
    @Test(priority = 1)
    public void getToken(){

        System.out.println("Token");
    }
    @Test(alwaysRun = true)
    public void getId(){

        System.out.println("Id");
    }

    @Test(priority = 3)
    public void test_PUT(){
    System.out.println("Put");
    }
}

