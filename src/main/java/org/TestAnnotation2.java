package org;

import org.testng.annotations.Test;

public class TestAnnotation2 {
    @Test(priority = 1)
    public void SearchCustomer(){
        System.out.println("I want to Search the customer records");
    }

    @Test(priority = 2)
    public void AddCustomer(){
        System.out.println("I want to add the customer");
    }

    @Test(priority = 3)
    public void logout(){
        System.out.println("Logging out, Thank you!");
    }
}
