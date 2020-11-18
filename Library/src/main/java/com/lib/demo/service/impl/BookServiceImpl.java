package com.lib.demo.service.impl;

import com.lib.demo.bean.Book;
import com.lib.demo.mapper.BookMapper;
import com.lib.demo.service.BookService;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
