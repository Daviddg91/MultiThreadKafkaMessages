package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ThreadManager.ThreadSend;
import com.entitys.Email;
import com.google.gson.Gson;


@RequestMapping("/api/thread")
@RestController
public class ThreadController {

	@Autowired
	Environment env;

	@Autowired
	ThreadSend threadSend;


	@GetMapping("/{nthreads}")
	public String sentMessage(@PathVariable(name = "nthreads") int numberThreads) {

		Email email = new Email();
		Email emailGenerate = email.generateOutputEmail();

		Gson gson = new Gson();
		String jsonObject = gson.toJson(emailGenerate);
		System.out.println("comienza a enviar mensajes");
		for (int i = 0; i < numberThreads; i++) {
			threadSend.asyncMethodWithReturnType(jsonObject);
		}

		return "Messages send!";
	}

}
