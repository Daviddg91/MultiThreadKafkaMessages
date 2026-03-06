package com.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {
	
	@Value("${async.core-pool-size:8}")
	private int corePoolSize;
	
	@Value("${async.max-pool-size:16}")
	private int maxPoolSize;
	
	@Value("${async.queue-capacity:1000}")
	private int queueCapacity;
	
	@Value("${async.thread-name-prefix:async-}")
	private String threadNamePrefix;
	
   @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
