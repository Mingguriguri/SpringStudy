package com.in28minutes.learnspringframework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//Eliminate verbosity in creating Java Beans
//Public accessor methos, constructor,
//equals, hashcode and toString are automatically created
//Released in JDK 16.

record Person (String name, int age, Address address) {};
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
		return new Person("Ravi", 20, new Address("Main Street", "Uthrecht"));
	}
	
	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age(), address()); // name, age
	}
	
	@Bean
	public Person person3Parameters(String name, int age, Address address3) { // name, age, address2
		return new Person(name, age, address3); 
	}
	
	// No qualifying bean of type 'com.in28minutes.learnspringframework.Address' 
	// available: expected single matching bean but found 2: address2,address3
	@Bean
	@Primary
	public Person person4Parameters(String name, int age, Address address) { // name, age, address
		return new Person(name, age, address); 
	}
	
	@Bean
	public Person person5Qualifier(String name, int age, @Qualifier("address3Qualifier") Address address) { // name, age, address3Qualifier
		return new Person(name, age, address); 
	}
	
	
	@Bean(name = "address2")
	@Primary
	public Address address(){
		return new Address("Bakery Street", "London");
	}
	
	@Bean(name = "address3")
	@Qualifier("address3Qualifier")
	public Address address3(){
		return new Address("Motinagar", "HGyderabad");
	}
}
