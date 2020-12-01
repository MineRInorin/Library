package com.lib.demo.service;

import com.lib.demo.bean.Bean;
import com.lib.demo.bean.Book;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BookVo;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
public interface BookService extends IService<Book> {
		List<Book> SelectBook(Book book);
		IPage<Book> selectBookByIPage (BookVo bookVo);
		ResultObj saveOraddBook(Book book);
		ResultObj deleteBook(Integer id);
		ResultObj saveBook(List<Book> book);
		public Map<String, Object> uploadImg(MultipartFile mf);
		public ResultObj updateImg(Book book);
		List<Bean> selecttype();
		List<Bean> selectcountry();
}
