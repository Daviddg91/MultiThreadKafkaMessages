package com.LoggerUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class CustomLogger {

	private static final Logger log = LoggerFactory.getLogger(CustomLogger.class);
	
	@CircuitBreaker(name = "logInFile", fallbackMethod = "logInFileFallback")
	public void logInFile(String msg) {
		log.info(msg);
	}

	public void logInFileFallback(String msg, Throwable e) {
	}

}
