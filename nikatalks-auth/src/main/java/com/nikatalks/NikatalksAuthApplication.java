package com.nikatalks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@EnableDiscoveryClient
@EntityScan({"com.nikatalks.commons.entity","com.nikatalks.commons.dto","com.nikatalks.commons.mapper"})
@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class NikatalksAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(NikatalksAuthApplication.class, args);
	}

}
