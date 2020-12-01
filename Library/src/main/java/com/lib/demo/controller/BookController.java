package com.lib.demo.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lib.demo.bean.Bean;
import com.lib.demo.bean.Book;
import com.lib.demo.service.BookService;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BookVo;

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
	
	@PostMapping("selectBook")
	public List<Book> selectBook(@RequestBody Book book){
		
		
		return bookService.SelectBook(book)	;	
	}
	@PostMapping("selectByPage")
	public IPage<Book> selectByPage(@RequestBody BookVo bookVo){
		return bookService.selectBookByIPage(bookVo);
	}
	
	@GetMapping("getall")
	public List<Book> selectAllBook(){
		return bookService.list();
	}
	
	@PostMapping("add")
	public ResultObj  insertBook(@RequestBody Book book){
		return bookService.saveOraddBook(book);
	}
	@PostMapping("adds")
	public ResultObj  insertBook(@RequestBody String str ){
//		Integer umaiId = Integer.parseInt(JSON.parseObject(str).get("umaiId").toString());
		List<Book> list = JSON.parseArray(JSON.parseObject(str).getString("book"), Book.class);
		return bookService.saveBook(list);
	}
	@GetMapping("type")
	public List<Bean> selecttype(){
		return bookService.selecttype();
		
	}
	@GetMapping("country")
	public List<Bean> selectcountry(){
		return bookService.selectcountry();
		
	}
	
	
	@GetMapping("getbyId/{id}")
	public Book GetBook(@PathVariable("id")Integer id){
		return bookService.getById(id);
	}
	@DeleteMapping("delete/{id}")
	public ResultObj deleteBook(@PathVariable("id")Integer id){
		return bookService.deleteBook(id);
	}
	@PutMapping("update")
	public ResultObj updateBook(@RequestBody Book book){
		return bookService.updateById(book)?ResultObj.UPDATE_SUCCESS:ResultObj.UPDATE_ERROR;
	}
	@PostMapping("uploadlocal")
	public Map<String, Object> uploadImg(@RequestParam("file") MultipartFile mf) {
		return bookService.uploadImg(mf);
	}

	@PostMapping("updateimg")
	public ResultObj updateBookImg(@RequestBody Book book) {
		return bookService.updateImg(book);
	}

}

