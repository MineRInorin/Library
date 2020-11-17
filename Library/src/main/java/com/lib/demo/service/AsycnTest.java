package com.lib.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("asycnTest")
public class AsycnTest {

	@Async("taskExecutor")
	public void testTask(int i) {
		System.out.println(Thread.currentThread().getName() + "-------" + i);
	}

	@Async("taskExecutor")
	public void strTask(String str) {
		System.out.println(Thread.currentThread().getName() + str);
	}
}
