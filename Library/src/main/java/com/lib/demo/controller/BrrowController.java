package com.lib.demo.controller;


import java.sql.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.lib.demo.bean.Book;
import com.lib.demo.bean.Brrow;
import com.lib.demo.bean.User;
import com.lib.demo.bean.Brrow;
import com.lib.demo.service.BookService;
import com.lib.demo.service.BrrowService;
import com.lib.demo.service.UserService;
import com.lib.demo.service.BrrowService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/brrow")
public class BrrowController {
	
	@Autowired
	BrrowService brrowService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	 @PostMapping("/brrowbook")
	    public String brrowbook(Integer uid,Integer bid){
		 
		 	Book book = bookService.getById(bid);
		 	User user = userService.getById(uid);
		 	
		 	Brrow brrow = new Brrow();
		 	brrow.setBookId(book.getId());
		 	brrow.setBookName(book.getName());
		 	brrow.setBrrCheck(1);
		 	long currentTimeMillis = System.currentTimeMillis();
		 	long exceptTime = currentTimeMillis+(long)  30 * 24 * 60 * 60 * 1000;
		 	Date date = new Date(System.currentTimeMillis());
		 	brrow.setBrrowDate(new Date(System.currentTimeMillis()));
		 	brrow.setExpectDate(new Date(exceptTime));
		 	brrow.setUId(uid);
		 	brrow.setRetCheck(0);
		 	
		 	
	        return brrowService.save(brrow)==true?"借书成功":"借书失败";
	    }
	
	
	
	@GetMapping("/selectall")
	public List<Brrow> SelectAll(){
		
		return brrowService.list();
	}
	@PostMapping("insert")
	public String  insertBrrow(@RequestBody Brrow brrow){
		return brrowService.save(brrow)==true?"success":"fail";
	}
	@GetMapping("getbyId/{id}")
	public Brrow GetBrrow(@PathVariable("id")Integer id){
		return brrowService.getById(id);
	}
	@DeleteMapping("delete/{id}")
	public String deleteBrrow(@PathVariable("id")Integer id){
		return brrowService.removeById(id)?"success":"fail";
	}
	@PutMapping("update")
	public String updateBrrow(@RequestBody Brrow brrow){
		return brrowService.updateById(brrow)?"success":"fail";
	}
}
