package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.Test;

public class APITest019_TestNG_Enabled {
    @Test(priority = 1)
    public void getToken(){

        System.out.println("Token");
    }
    @Test(enabled = false)
    public void getId(){

        System.out.println("Id");
    }

    @Test(priority = 3)
    public void test_PUT(){
    System.out.println("Put");
    }
}

