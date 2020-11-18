package com.lib.demo.service.impl;

import com.lib.demo.bean.User;
import com.lib.demo.mapper.UserMapper;
import com.lib.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
