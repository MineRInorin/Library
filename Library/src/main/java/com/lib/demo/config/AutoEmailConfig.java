package com.lib.demo.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lib.demo.bean.Book;
import com.lib.demo.bean.User;
import com.lib.demo.service.BrrowService;
import com.lib.demo.utils.EmailSendUtils;

@Configuration
@EnableScheduling
public class AutoEmailConfig {
	private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

	@Resource
	EmailSendUtils sendUtils;

	@Autowired
	BrrowService brrowservice;

	@Scheduled(cron = "0 55 16 * * ?")
	@Async("taskExecutor")
	public void send() {
		
		Map<User, List<Book>> expireUser = brrowservice.getExpireUser();
		Iterator<Entry<User, List<Book>>> iter = expireUser.entrySet().iterator();
		
		while (iter.hasNext()) {
			Entry<User, List<Book>> entry = iter.next();
			User user = entry.getKey();
			List<Book> book = entry.getValue();
			for(Book b:book) {
				sendUtils.sendEmail(user, b);
				logger.info("线程：" + Thread.currentThread().getName() + "正在发送邮件.........");
			}
		}
	}
}
