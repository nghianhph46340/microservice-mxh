package com.example.demomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemomicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemomicroserviceApplication.class, args);
	}

}
