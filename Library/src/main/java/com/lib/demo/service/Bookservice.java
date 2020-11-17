package com.lib.demo.service;

import com.lib.demo.bean.Book;

public interface Bookservice {

	// 插入图书
	public String insertBook(Book book);

	// 查询图书(从id查询)
	public Book SelectBookfromid(int id);

	// 修改图书信息
	public String updateBook(Book book);

	// 删除
	public String deleteBook(int id);
}
