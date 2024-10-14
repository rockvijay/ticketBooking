package com.trainticket.TrainBooking.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Restconfig {

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
}
