package com.lib.demo.mapper;

import com.lib.demo.bean.Bean;
import com.lib.demo.bean.Book;

import java.util.List;

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
public interface BookMapper extends BaseMapper<Book> {
	List<Bean> selecttype();
	List<Bean> selectcountry();

}
