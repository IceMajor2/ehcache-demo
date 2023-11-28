package com.example.ehcache.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EhcacheSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EhcacheSpringDemoApplication.class, args);
	}

}
