package com.example.doodle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.doodle.controller", "com.example.doodle.domain", "com.example.doodle.mapper","com.example.doodle.service" })
@EnableJpaRepositories
public class DoodleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoodleApplication.class, args);
	}

}
