package com.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		final Logger log  = LoggerFactory.getLogger(WeatherApplication.class.getName());
		SpringApplication.run(WeatherApplication.class, args);
	}

}
