package com.myqandrade.movieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class MovieServiceApplication {

	@GetMapping
	public String hello(){
		return "HELLO WORLD!";
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

}
