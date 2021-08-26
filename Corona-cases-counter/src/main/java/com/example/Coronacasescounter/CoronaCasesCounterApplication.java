package com.example.Coronacasescounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronaCasesCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaCasesCounterApplication.class, args);
	}

}
