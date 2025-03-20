package org;

import org.testng.annotations.*;

public class TestAnnotation1 {

    @BeforeClass
    public void beforeClass(){
        System.out.println("Hello & Welcome!");
    }

    @BeforeTest
    public void PreChecks(){
        System.out.println("Doing pre-checks before launching the browser");
    }

    @Test(priority=1)
    public void launchBrowser(){
        System.out.println("I am launching the browser");
    }

    @Test(priority = 2)
    public void login(){
        System.out.println("login");
    }

    @Test(priority = 3)
    public void logout(){
        System.out.println("logout");
    }

    @Test(priority = 3)
    public void closingBrowser(){
        System.out.println("Closing Browser, Thank you!");
    }

    @AfterTest
    public void PostExecution(){
        System.out.println("Success!");
    }

    @AfterClass
    public void AfterClass(){
        System.out.println("Goodbye!");
    }
}