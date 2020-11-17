package com.lib.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.demo.bean.Book;
import com.lib.demo.mapper.BookMapper;

@Service("Bookservice")
public class Bookimpl implements Bookservice {

	@Autowired
	BookMapper bookmapper;

	@Override
	public String insertBook(Book book) {
		// TODO Auto-generated method stub
		int conlum = bookmapper.insertBook(book);
		if (conlum == 1) {
			return "添加成功";
		} else {
			return "添加失败";
		}
	}

	@Override
	public Book SelectBookfromid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBook(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
