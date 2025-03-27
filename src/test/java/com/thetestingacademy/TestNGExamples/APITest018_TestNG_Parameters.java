package com.thetestingacademy.TestNGExamples;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITest018_TestNG_Parameters {
@Parameters("browser")
@Test
    void demo1(String value){
        System.out.println("Browser is: "+value);
        //Open the browser and Select abc
if(value.equalsIgnoreCase("chrome")){
    System.out.println("Start my testing");
}
if(value.equalsIgnoreCase("firefox")){
    System.out.println("Firefox Testing");
}
    }
}

