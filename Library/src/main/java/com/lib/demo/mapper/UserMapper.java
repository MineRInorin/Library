package com.lib.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

//添加MyBatis接口方法
@Mapper
public interface UserMapper {

	// 与Service层通信的测试接口
	public void insert();
}
