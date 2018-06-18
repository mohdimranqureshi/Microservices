package com.xavient.cop_training_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CopTrainingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopTrainingServiceApplication.class, args);
	}
}
