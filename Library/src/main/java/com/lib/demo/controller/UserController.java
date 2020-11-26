package com.lib.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;
import com.lib.demo.bean.Book;
import com.lib.demo.bean.User;
import com.lib.demo.constate.SysConstate;
import com.lib.demo.service.UserService;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.UserVo;

import io.swagger.annotations.Api;
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
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;


    @PostMapping("/login")
    public Map<String, Object> findByName(@RequestBody User user){
    	Map<String, Object> map = new HashMap<String, Object>();
    	QueryWrapper<User> wrapper = new QueryWrapper<User>();
    	wrapper.eq("id",user.getId());
    	wrapper.eq("pwd",user.getPwd());
         User one = userService.getOne(wrapper);
         if(one !=null) {
        	 map.put("user", one);
        	 map.put("result", ResultObj.LOGIN_SUCCESS);
        	 }else {
        	     if(userService.getById(user.getId())!=null) {
        	    	 map.put("result",new ResultObj(SysConstate.CODE_ERROR,"密码错误"));
        	     }else {
        	    	 map.put("result",new ResultObj(SysConstate.CODE_ERROR,"账号不存在"));
        	     }
        	 }
         return map;
    }
    
    @PostMapping("/regist")
    public ResultObj  registUser(@RequestBody User user){
    	QueryWrapper<User> wrapper = new QueryWrapper<User>();
    	wrapper.eq("email",user.getEmail()).or().eq("id",user.getId());
    	User one = userService.getOne(wrapper);
    	
    	if(one!=null) {
    		return new ResultObj(SysConstate.CODE_ERROR,"注册失败，该邮箱或者账号已存在");
    	}
    	user.setAge(0);
    	user.setRoleId(0);
    	user.setImg("default.jpg");
    	return userService.save(user)==true?ResultObj.ADD_SUCCESS:ResultObj.ADD_ERROR;
    }

    
    @PutMapping("update")
    public ResultObj updateUser(@RequestBody User user){
    	return userService.updateUser(user);
    }
    
    @PostMapping("selectByPage")
	public IPage<User> selectUser(@RequestBody UserVo userVo){
		return userService.SelectUser(userVo)	;	
	}
    @DeleteMapping("delete/{id}")
    public ResultObj deleteUser(@PathVariable("id")Integer id){
    	return userService.removeUser(id);
    }
	
	@GetMapping("getall")
	public List<User> selectAllUser(){
		return userService.list();
	}
	@GetMapping("getbyId/{id}")
	public User GetUser(@PathVariable("id")Integer id){
		return userService.getById(id);
	}
}
