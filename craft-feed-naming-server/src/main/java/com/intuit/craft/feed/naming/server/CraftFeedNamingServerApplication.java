package com.intuit.craft.feed.naming.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CraftFeedNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftFeedNamingServerApplication.class, args);
	}

}
