package org;

import org.testng.annotations.Test;

public class TestAnnotation1 {
    @Test(priority=1)
    public void launchBrowser(){
        System.out.println("I want to launch the browser");
    }

    @Test(priority = 2)
    public void login(){
        System.out.println("login");
    }

    @Test(priority = 3)
    public void closingBrowser(){
        System.out.println("Closing Browser, Thank you!");
    }
}