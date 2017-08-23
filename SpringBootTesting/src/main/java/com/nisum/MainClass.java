package com.nisum;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MainClass {


	public static void main(String[] args) {
		SpringApplication.run(MainClass.class, args);
		
		HelloWorld hellowWorld = new HelloWorld();
		System.out.println(" Basic output "+hellowWorld.sayHello());

	}
}
