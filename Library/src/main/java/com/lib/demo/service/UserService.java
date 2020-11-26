package com.lib.demo.service;

import com.lib.demo.bean.User;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.UserVo;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
public interface UserService extends IService<User> {

	IPage<User> SelectUser(UserVo userVo);

	ResultObj updateUser(User user);
	
	User findByEmail(String email);

	ResultObj removeUser(Integer id);
	

}
