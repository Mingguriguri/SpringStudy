package com.in28minutes.learnspringframework.examples.g1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class BusinessService {

	private DataService dataService;

	@Autowired
	public DataService getDataService() {
		System.out.println("Setter Injection");
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
}

@Component
class DataService {
	
}
@Configuration
@ComponentScan
public class CdiContextLauncherApplication {
	public static void main(String[] args) {
		try(var context = new AnnotationConfigApplicationContext
							(CdiContextLauncherApplication.class)){
			Arrays.stream(context.getBeanDefinitionNames()).
			forEach(System.out::println);
			
		}
	}                                                                                                                                                                                                                                                                
}
