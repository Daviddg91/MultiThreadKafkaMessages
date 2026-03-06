package com.ThreadManager;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.LoggerUtils.CustomLogger;
import com.google.gson.JsonObject;

@Service
public class ThreadSend {

	@Autowired 
	CustomLogger logger; 
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafka.topic.name:emails}")
	private String topicName;
	
	@Async
	public Future<String> asyncMethodWithReturnType(String jsonObject) {
	    System.out.println("Execute method asynchronously - " 
	      + Thread.currentThread().getName());
	    try {
	    	functionalSendMessage(jsonObject);
	        return new AsyncResult<String>("ok");
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    return null;
	}
	
	
 
	public void functionalSendMessage(String jsonObject) {
		JsonObject log = new JsonObject();
		log.addProperty("status", "sent");
		log.addProperty("payload", jsonObject);
		logger.logInFile(log.toString());
		kafkaTemplate.send(topicName, jsonObject);
	}
	
}
