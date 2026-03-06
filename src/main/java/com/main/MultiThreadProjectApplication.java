package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//@EnableAutoConfiguration
////@ComponentScan(basePackages = {""})
//public class DemoApplication {
//
//
//	
//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//		
//		
//	}
//
//}
 

@SpringBootApplication(scanBasePackages = "com")
public class MultiThreadProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiThreadProjectApplication.class, args);
	}

}
