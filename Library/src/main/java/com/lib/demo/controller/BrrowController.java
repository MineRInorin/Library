package com.lib.demo.controller;


import java.security.Timestamp;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lib.demo.bean.Book;
import com.lib.demo.bean.Brrow;
import com.lib.demo.bean.Rank;
import com.lib.demo.bean.Tongji;
import com.lib.demo.bean.User;
import com.lib.demo.constate.SysConstate;
import com.lib.demo.bean.Brrow;
import com.lib.demo.service.BookService;
import com.lib.demo.service.BrrowService;
import com.lib.demo.service.UserService;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BrrowVo;
import com.lib.demo.vo.BuVo;
import com.lib.demo.vo.PageVo;

import freemarker.core.ReturnInstruction.Return;

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
	 public ResultObj brrowbook( @RequestBody BuVo buVo){
		  return brrowService.brrowbook(buVo.getUid(),buVo.getBid());
	    }
	
	 
	@PutMapping("/returnbook")
	public ResultObj ReturnBook(Integer id) {
		return brrowService.ReturnBook(id);
		
	}
	//查询用户借阅的图书信息
	@PostMapping("getbrrowBook")
	public IPage<Brrow> getBrrowBook(@RequestBody BrrowVo brrowVo){

		return brrowService.SelectbrrowBook(brrowVo);
	}
	
	@PostMapping("getByPage")
	public IPage<Brrow> getByPage(@RequestBody BrrowVo brrowVo){
		return brrowService.SelectByPage(brrowVo);
	}
	
	 @PostMapping("findRank")
	    public IPage<Brrow> findRank(@RequestBody PageVo page){
	    	IPage<Brrow> findRank = brrowService.findRank(page.getPage(), page.getLimit());
			return findRank;
	    	
	    }
	@GetMapping("findRankbyAuthor")
	 public List<Tongji> findRankbyAuthor() {
		  List<Tongji> list = brrowService.findRankbyAuthor();
		  return list;
	 }
	@GetMapping("findRankbyBook")
	public List<Tongji> findRankbyBook() {
		List<Tongji> list = brrowService.findRankbyBook();
		return list;
	}
	 
	@GetMapping("findRankbyType")
	public List<Tongji> findRankbyType() {
		List<Tongji> list = brrowService.findRankbyType();
		return list;
	}
	@GetMapping("findRankbyMonth")
	public List<Integer> findRankbyMonth() {
		List<Integer> list = brrowService.findRankbyMonth();
		
		return list;
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
		Brrow brrow = brrowService.getById(id);
		return brrow;
	}
	@DeleteMapping("delete/{id}")
	public String deleteBrrow(@PathVariable("id")Integer id){
		return brrowService.removeById(id)?"success":"fail";
	}
	@PutMapping("updateBrrow")
	public ResultObj updateBrrow(@RequestBody Brrow brrow){
		return brrowService.updateBrrowState(brrow);
	}
	@PostMapping("remind")
	public ResultObj remindUser(@RequestBody BuVo buVo) {
		return brrowService.remindUser(buVo.getUid(), buVo.getBid());
	}
}
