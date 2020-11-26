package com.lib.demo.service.impl;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lib.demo.bean.Book;
import com.lib.demo.bean.Brrow;
import com.lib.demo.constate.SysConstate;
import com.lib.demo.mapper.BookMapper;
import com.lib.demo.service.BookService;
import com.lib.demo.service.BrrowService;
import com.lib.demo.utils.AppFileUtils;
import com.lib.demo.utils.RandomUtils;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BookVo;

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

	@Autowired
	BookMapper bookmapper;
	@Autowired
	BrrowService brrowService;
	@Override
	public List<Book> SelectBook(Book book) {
		QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
		wrapper.like(book.getAuthor()!=null, "author", book.getAuthor());
		wrapper.like(book.getCountry()!=null, "country", book.getCountry());
		wrapper.like(book.getLength()!=null, "length", book.getLength());
		wrapper.like(book.getName()!=null, "name", book.getName());
		wrapper.like(book.getType()!=null, "type", book.getType());
		wrapper.like(book.getTheme()!=null, "theme", book.getTheme());
		wrapper.gt(book.getTotal()!=null,"total",0);
		return bookmapper.selectList(wrapper);
	}

	@Override
	public IPage<Book> selectBookByIPage(BookVo bookVo) {
		
		Page<Book> page = new Page<Book>(bookVo.getPage(),bookVo.getLimit());
		QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
		wrapper.like(bookVo.getAuthor()!=null, "author", bookVo.getAuthor());
		wrapper.like(bookVo.getCountry()!=null, "country", bookVo.getCountry());
		wrapper.like(bookVo.getLength()!=null, "length", bookVo.getLength());
		wrapper.like(bookVo.getName()!=null, "name", bookVo.getName());
		wrapper.like(bookVo.getType()!=null, "type", bookVo.getType());
		wrapper.like(bookVo.getTheme()!=null, "theme", bookVo.getTheme());
		wrapper.gt(bookVo.getTotal()!=null,"total",0);
		wrapper.orderByDesc("id");
		Page<Book> pageBook = bookmapper.selectPage(page, wrapper);
		return pageBook;
	}
	
	public ResultObj saveBook(List<Book> book) {
		 for(Book b:book) {
			 this.saveOraddBook(b);
		 }
		 return ResultObj.ADD_SUCCESS;
	}
	
	public ResultObj saveOraddBook(Book book) {
		QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
		wrapper.eq("name", book.getName());
		wrapper.eq("author", book.getAuthor());
		Book selectOne = bookmapper.selectOne(wrapper);
		if(selectOne!=null) {
			selectOne.setTotal(selectOne.getTotal()+book.getTotal());
			bookmapper.updateById(selectOne);
			return  new ResultObj(SysConstate.CODE_SUCCESS,"该书已存在，自动添加库存");
		}else {
			return bookmapper.insert(book)==1?ResultObj.UPDATE_SUCCESS:ResultObj.UPDATE_ERROR;
		}
		
	}

	@Override
	public ResultObj deleteBook(Integer id) {
		Book book = bookmapper.selectById(id);
		QueryWrapper<Brrow> wrapper =new QueryWrapper<Brrow>();
		wrapper.eq("book_id", id);
		List<Brrow> list = brrowService.list(wrapper);
		if(list==null||list.size()<=0) {
			return bookmapper.deleteById(id)==1?ResultObj.DELETE_SUCCESS:ResultObj.DELETE_ERROR;
		}else {
			book.setTotal(0);
			return  bookmapper.updateById(book)==1?new ResultObj(SysConstate.CODE_SUCCESS,"该书有出借记录，无法删除，数量自动设置为0"):ResultObj.UPDATE_ERROR;
		}
		
	}

	@Override
	public Map<String, Object> uploadImg(MultipartFile mf) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new Hashtable<String, Object>();
		String parentPath = AppFileUtils.PATH;
		// 得到当前日期作为文件夹名称
		String dirName = RandomUtils.getCurrentDateForString();
		// 构造文件夹对象
		File dirFile = new File(parentPath, dirName);
		if (!dirFile.exists()) {

			dirFile.mkdirs();// 创建文件夹
		}
		// 得到文件原名
		String oldName = mf.getOriginalFilename();

		String newName = RandomUtils.createFileNameUseTime(oldName, "");
		System.out.println(newName);
		File dest = new File(dirFile, newName);
		try {
			mf.transferTo(dest);
			map.put("src", dirName + "/" + newName);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("error", "上传错误");
			return map;
		}
	}

	@Override
	public ResultObj updateImg(Book book) {
		// TODO Auto-generated method stu
				String oldname = book.getImg();
				String realname = AppFileUtils.updateFileName(oldname, SysConstate.FILE_UPLOAD_TEMP);
				book.setImg(realname);
				return updateById(book) ? ResultObj.UPDATE_SUCCESS : ResultObj.UPDATE_ERROR;
	}
	
	

}
