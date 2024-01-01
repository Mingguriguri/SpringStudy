package com.in28minutes.learnspringframework.helloworld;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.MarioGame;
import com.in28minutes.learnspringframework.game.PacMan;
import com.in28minutes.learnspringframework.game.SuperContraGame;

public class App02HelloWorldSpring {

	public static void main(String[] args) {
		// 1: - Use @Configuration -> Launch a Spring Context 
		var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
				
		// 2: Configure the things that we want Spring to manage - @Configuration
		// by creating HelloWorldConfiguration class - @Configuration
		// name(메소드명) - @Bean
		
		// 3: Retrieving Beans managed by Spring
		System.out.println(context.getBean("name"));
		System.out.println(context.getBean("age"));
		System.out.println(context.getBean("person"));
		System.out.println(context.getBean("person2MethodCall"));
		System.out.println(context.getBean("person3Parameters"));
		System.out.println(context.getBean("address2"));
		System.out.println(context.getBean(Person.class));
		System.out.println(context.getBean(Address.class));
		System.out.println(context.getBean("person5Qualifier"));
		//System.out.println
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		
		//context.getBeanDefinitionCount();

	}                                                                                                                                                                                                                                                           
}
