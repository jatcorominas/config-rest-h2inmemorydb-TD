package com.config.springrest.h2db.springbootrestinmemoryh2db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.config.springrest.h2db.springbootrestinmemoryh2db.service.ConfigService;

@SpringBootApplication
public class SpringBootRestInmemoryh2dbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestInmemoryh2dbApplication.class, args);
	}
	
	@Bean("configService")
	public ConfigService getConfigRepositoryService(){
		return new ConfigService();
	}
}
