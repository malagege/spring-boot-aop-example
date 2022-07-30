package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		var  context = SpringApplication.run(DemoApplication.class, args);
		var testClass = context.getBean(TestClass.class);
		var testClass2 = context.getBean(TestClass2.class);
		testClass.callMethod1("1","2","3");
		testClass.callMethod1("1","2","3");
		context.close();
	}

}
