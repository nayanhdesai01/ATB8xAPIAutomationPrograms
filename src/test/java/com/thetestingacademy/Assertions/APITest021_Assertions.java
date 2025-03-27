package com.thetestingacademy.Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITest021_Assertions {
//    @Test
//    public void test_hardAssertions(){
//        System.out.println("Start of the program");
//        Assert.assertTrue(false);
//        System.out.println("End of the program");
//    }

    @Test
    public void test_softAssertions(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false); //This will not stop the execution
        System.out.println("This line will be executed");
       softAssert.assertAll(); //This will report all collected errors.

    }
}
