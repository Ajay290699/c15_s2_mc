package com.niit.DemoSprint1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoSprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoSprint1Application.class, args);
	}

}
