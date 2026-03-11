package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import com.main.MultiThreadProjectApplication;

@SpringBootTest(classes = MultiThreadProjectApplication.class, properties = {
		"spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
@EmbeddedKafka(partitions = 1, topics = { "emails" })
class MultiThreadProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
