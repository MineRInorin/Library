package com.lib.demo.mapper;

import com.lib.demo.bean.User;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
