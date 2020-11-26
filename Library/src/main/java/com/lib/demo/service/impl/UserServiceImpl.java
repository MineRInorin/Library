package com.lib.demo.service.impl;

import com.lib.demo.bean.Brrow;
import com.lib.demo.bean.User;
import com.lib.demo.constate.SysConstate;
import com.lib.demo.bean.User;
import com.lib.demo.mapper.BrrowMapper;
import com.lib.demo.mapper.UserMapper;
import com.lib.demo.service.UserService;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	UserMapper usermapper;
	@Autowired
	BrrowMapper brrowmapper;
	
	@Override
	public IPage<User> SelectUser(UserVo userVo) {
			IPage<User> page = new Page<User>(userVo.getPage(),userVo.getLimit());
			QueryWrapper<User> wrapper = new QueryWrapper<User>();
			wrapper.like(userVo.getAddress()!=null, "address", userVo.getAddress());
			wrapper.like(userVo.getName()!=null, "name", userVo.getName());
			wrapper.eq(userVo.getAge()!=null, "age", userVo.getAge());
			wrapper.eq(userVo.getRoleId()!=null, "role_id", userVo.getRoleId());
			wrapper.eq(userVo.getSex()!=null,"sex", userVo.getSex());
			return usermapper.selectPage(page, wrapper);
		
	}

	@Override
	public ResultObj updateUser(User user) {
		if(user.getEmail()!=null) {
			User user2 = findByEmail(user.getEmail());
			if(user2==null||user2.getId().equals(user.getId())) {
				return usermapper.updateById(user)==1?ResultObj.UPDATE_SUCCESS:ResultObj.UPDATE_ERROR;
			}else {
				return new ResultObj(SysConstate.CODE_ERROR,"修改失败，该邮箱已经被使用");
			}
				
		}
		return usermapper.updateById(user)==1?ResultObj.UPDATE_SUCCESS:ResultObj.UPDATE_ERROR;
	}

	@Override
	public User findByEmail(String email) {
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.eq("email",email);
		User user = usermapper.selectOne(wrapper);
 		return user==null?null:user;
 		
 		
	}

	@Override
	@Transactional
	public ResultObj removeUser(Integer id) {
		QueryWrapper<Brrow> wrapper = new QueryWrapper<Brrow>();
		wrapper.eq("u_id", id);
		wrapper.eq("brr_check", 2);
		wrapper.eq("ret_check", 0);
		List<Brrow> list = brrowmapper.selectList(wrapper);
		if(list.size()>0) {
			return new ResultObj(SysConstate.CODE_ERROR,"该用户有书未归还，无法删除");
		}else {
			QueryWrapper<Brrow> wrapper2 = new QueryWrapper<Brrow>();
			wrapper2.eq("u_id", id);
			list = brrowmapper.selectList(wrapper2);
			for(Brrow brrow :list) {
				brrowmapper.deleteById(brrow.getId());
			}
			return usermapper.deleteById(id)==1?ResultObj.DELETE_SUCCESS:ResultObj.DELETE_ERROR;
		}
		
		
	}


}
