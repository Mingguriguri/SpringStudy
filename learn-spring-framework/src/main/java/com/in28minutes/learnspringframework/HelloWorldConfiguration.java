package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

	// call Bean
	@Bean
	public String name() {
		return "Minggu";
	}
}
