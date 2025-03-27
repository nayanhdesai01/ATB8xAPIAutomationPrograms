package com.thetestingacademy.TestNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest016_DependsOnMethods {
    @Test
    public void serverStartedOk(){
        System.out.println("I will run first");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOk")
    public void method1(){
        System.out.println("Method 1, I will run after server");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOk")
    public void method2(){
        System.out.println("Method 2");
        Assert.assertTrue(true);
    }
}
