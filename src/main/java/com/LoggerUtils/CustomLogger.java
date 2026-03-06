package com.LoggerUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class CustomLogger {

	@Value("${app.log.file:customLogFile.log}")
	private String logFile;
	
	@CircuitBreaker(name = "logInFile", fallbackMethod = "logInFileFallback")
	public void logInFile(String msg) {
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;

		try {
			fh = new FileHandler(logFile, true);
			logger.addHandler(fh);
			PlainFormatter formatter = new PlainFormatter();
			fh.setFormatter(formatter);

			logger.info(msg);
			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}

	}

	public void logInFileFallback(String msg, Throwable e) {
	}

}
