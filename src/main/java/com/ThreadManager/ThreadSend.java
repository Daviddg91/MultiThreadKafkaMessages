package com.ThreadManager;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.LoggerUtils.CustomLogger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.monitoring.ElasticsearchLogger;

@Service
public class ThreadSend {

	@Autowired 
	CustomLogger logger; 
	
	@Autowired
	ElasticsearchLogger elasticLogger;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafka.topic.name:emails}")
	private String topicName;
	
	@Async
	public Future<String> asyncMethodWithReturnType(String jsonObject) {
	    System.out.println("Execute method asynchronously - " 
	      + Thread.currentThread().getName());
	    functionalSendMessage(jsonObject);
	    return new AsyncResult<String>("ok");
	}
	
	
 
	public void functionalSendMessage(String jsonObject) {
		Gson gson = new Gson();
		JsonObject src = new Gson().fromJson(jsonObject, JsonObject.class);
		JsonObject log = new JsonObject();
		log.addProperty("status", "sent");
		if (src != null) {
			if (src.has("email")) log.add("email", src.get("email"));
			if (src.has("message")) log.add("message", src.get("message"));
			if (src.has("date")) log.add("date", src.get("date"));
		}
		log.addProperty("event", "producer");
		log.addProperty("topic", topicName);
		log.addProperty("thread", Thread.currentThread().getName());
		logger.logInFile(log.toString());
		elasticLogger.logSent(jsonObject, topicName, Thread.currentThread().getName());
		kafkaTemplate.send(topicName, jsonObject);
	}
	
}
