package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Eliminate verbosity in creating Java Beans
//Public accessor methos, constructor,
//equals, hashcode and toString are automatically created
//Released in JDK 16.

record Person (String name, int age) {};
record Address (String firstLine, String city) {};

@Configuration
public class HelloWorldConfiguration {

	// call Bean
	@Bean
	public String name() {
		return "Minggu";
	}
	@Bean
	public int age() {
		return 15;
	}
	@Bean
	public Person person(){
		return new Person("Ravi", 20);
	}
	@Bean
	public Address address(){
		return new Address("Bakery Street", "London");
	}
}
