package com.intuit.craft.feed.exchange.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CraftFeedExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftFeedExchangeServiceApplication.class, args);
	}

}
