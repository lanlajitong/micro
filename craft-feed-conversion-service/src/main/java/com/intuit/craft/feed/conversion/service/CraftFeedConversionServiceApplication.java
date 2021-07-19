package com.intuit.craft.feed.conversion.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.intuit.craft.feed.conversion.service")
@EnableDiscoveryClient
public class CraftFeedConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftFeedConversionServiceApplication.class, args);
	}

}
