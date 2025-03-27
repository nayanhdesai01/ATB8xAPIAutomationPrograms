package com.thetestingacademy.TestNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest015_TestNG_Groups {
    @Test(groups = {"sanity","qa","preprod"})
    public void sanityRun(){
        System.out.println("Sanity");
        System.out.println("QA");
        Assert.assertTrue(true);
    }
    @Test(groups = {"reg","qa","preprod"})
    public void regRun(){
        System.out.println("Regression");
        Assert.assertTrue(true);
    }
    @Test(groups = {"dev","stage"})
    public void smokeRun(){
        System.out.println("Smoke");
        Assert.assertTrue(true);
    }
    @Test(groups = {"sanity","qa","preprod"})
    public void sanityRun1(){
        System.out.println("Sanity");
        System.out.println("QA");
        Assert.assertTrue(true);
    }
    @Test(groups = {"reg","qa","preprod"})
    public void regRun2(){
        System.out.println("Regression");
        Assert.assertTrue(true);
    }
    @Test(groups = {"dev","stage"})
    public void smokeRun3(){
        System.out.println("Smoke");
        Assert.assertTrue(true);
    }
}
