package com.spotify.outh2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod(Method m)
    {
        System.out.println("Executing test: "+m.getName());
        System.out.println("At thread :"+Thread.currentThread().getId());
    }
}
