package com.example.doodle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.doodle.controller", "com.example.doodle.mapper","com.example.doodle.service","com.example.doodle.dto","com.example.doodle.websocket"})
@EnableJpaRepositories
@MapperScan(basePackages = {"com.example.doodle.dao","com.example.doodle.mapper"})
public class DoodleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoodleApplication.class, args);
	}

}
