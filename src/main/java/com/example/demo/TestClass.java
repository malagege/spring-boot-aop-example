package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestClass {

    @Autowired
    TestClass _testClass;

    public void callMethod1(String... args){
        System.out.println("Call Method1!!!");
        _testClass.callMethod2();
    }

    public void callMethod2(){
        System.out.println("Call Method2!!!");
    }

}
