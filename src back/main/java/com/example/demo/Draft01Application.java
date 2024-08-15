package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
public class Draft01Application {

	public static void main(String[] args) {
		SpringApplication.run(Draft01Application.class, args);
	}

}
