package com.lib.demo.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ExecutorConfig {

	private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);
	private static BlockingQueue<Runnable> workqueue = new LinkedBlockingDeque<Runnable>(10);

	@Bean("taskExecutor")
	public Executor creatExecutor() {
		logger.info("Start Create ThreadPool");
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 90, 60L, TimeUnit.SECONDS, workqueue,
				new AbortPolicy());

		return executor;
	}
}
