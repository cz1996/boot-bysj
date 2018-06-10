package com.bysj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootBysjApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootBysjApplication.class, args);
	}
}
