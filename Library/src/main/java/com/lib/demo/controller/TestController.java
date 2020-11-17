package com.lib.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.demo.bean.Book;
import com.lib.demo.service.AsycnTest;
import com.lib.demo.service.Bookservice;

import io.swagger.annotations.Api;

@RestController
@Api
public class TestController {

	@Autowired
	AsycnTest asycntest;

	@Autowired
	Bookservice bookservice;

	@RequestMapping("/hello")
	public String returnHello() {
		return "Hello String Boot!!";
	}

	@RequestMapping("/Asycn")
	public String returnasycn() {
		asycntest.testTask(2);
		asycntest.strTask("16888888");
		return "异步操作";
	}

	@PostMapping("/test")
	public String testinsert(@RequestBody Book book) {
		System.out.println(book);
		return bookservice.insertBook(book);
	}
}
