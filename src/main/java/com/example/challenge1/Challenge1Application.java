package com.example.challenge1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"enn.challenge.controller", "enn.challenge.domain", "enn.challenge.mapper","enn.challenge.service" })
@EnableJpaRepositories
public class Challenge1Application {

	public static void main(String[] args) {
		SpringApplication.run(Challenge1Application.class, args);
	}

}
