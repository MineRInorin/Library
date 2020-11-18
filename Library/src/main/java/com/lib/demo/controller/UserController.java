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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;
import com.lib.demo.bean.User;
import com.lib.demo.service.UserService;
import com.lib.demo.utils.ResultObj;

import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
@RestController
@RequestMapping("//user")
public class UserController {
	@Autowired
	UserService userService;
    /**
     * 通过名字去查寻
     * @param
     * @return
     */
    @PostMapping("/login")
    public ResultObj findByName(@RequestBody User user){
    	QueryWrapper<User> wrapper = new QueryWrapper<User>();
    	wrapper.eq("id",user.getId());
    	wrapper.eq("pwd",user.getPwd());
         User one = userService.getOne(wrapper);
         if(one !=null) return ResultObj.LOGIN_SUCCESS;
         else return ResultObj.LOGIN_ERROR;
    }
    @PostMapping("/regist")
    public ResultObj  registUser(@RequestBody User user){
    	QueryWrapper<User> wrapper = new QueryWrapper<User>();
    	wrapper.eq("email",user.getEmail()).or().eq("id",user.getId());
    	User one = userService.getOne(wrapper);
    	if(one!=null) {
    		return ResultObj.ADD_ERROR;
    	}
    	return userService.save(user)==true?ResultObj.ADD_SUCCESS:ResultObj.ADD_ERROR;
    	 
    	
    }
	
	
	@GetMapping("getall")
	public List<User> selectAllUser(){
		return userService.list();
	}
	@GetMapping("getbyId/{id}")
	public User GetUser(@PathVariable("id")Integer id){
		return userService.getById(id);
	}
	@DeleteMapping("delete/{id}")
	public String deleteUser(@PathVariable("id")Integer id){
		return userService.removeById(id)?"success":"fail";
	}
	@PutMapping("update")
	public String updateUser(@RequestBody User user){
		return userService.updateById(user)?"success":"fail";
	}
}
