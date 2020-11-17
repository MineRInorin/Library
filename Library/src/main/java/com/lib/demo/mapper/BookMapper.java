package com.lib.demo.mapper;

import org.springframework.stereotype.Repository;

import com.lib.demo.bean.Book;

@Repository
public interface BookMapper {

	// 插入图书信息
	public int insertBook(Book book);

	// 根据id查找图书信息
	public Book selectBookfromid(int id);

	// 管理员修改图书信息
	public int updateBook(Book book);

	// 删除图书
	public int deleteBook(int id);
}
