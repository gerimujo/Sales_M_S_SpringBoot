package com.bar.barproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bar")
public class BarprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarprojectApplication.class, args);
	}

}
