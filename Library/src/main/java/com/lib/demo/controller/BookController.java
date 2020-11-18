package com.lib.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.lib.demo.bean.Book;
import com.lib.demo.service.BookService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
	@GetMapping("getall")
	public List<Book> selectAllBook(){
		return bookService.list();
	}
	@PostMapping("insert")
	public String  insertBook(@RequestBody Book book){
		return bookService.save(book)==true?"success":"fail";
	}
	@GetMapping("getbyId/{id}")
	public Book GetBook(@PathVariable("id")Integer id){
		return bookService.getById(id);
	}
	@DeleteMapping("delete/{id}")
	public String deleteBook(@PathVariable("id")Integer id){
		return bookService.removeById(id)?"success":"fail";
	}
	@PutMapping("update")
	public String updateBook(@RequestBody Book book){
		return bookService.updateById(book)?"success":"fail";
	}

}
