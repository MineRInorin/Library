package com.lib.demo.service.impl;

import com.lib.demo.bean.Book;
import com.lib.demo.bean.Brrow;
import com.lib.demo.bean.Rank;
import com.lib.demo.bean.Tongji;
import com.lib.demo.bean.User;
import com.lib.demo.constate.SysConstate;
import com.lib.demo.mapper.BookMapper;
import com.lib.demo.mapper.BrrowMapper;
import com.lib.demo.service.BookService;
import com.lib.demo.service.BrrowService;
import com.lib.demo.service.UserService;
import com.lib.demo.utils.EmailSendUtils;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BrrowVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
public class BrrowServiceImpl extends ServiceImpl<BrrowMapper, Brrow> implements BrrowService {
	
	@Autowired
	EmailSendUtils sendUtils;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BrrowMapper brrowMapper;
	
		public ResultObj brrowbook(Integer uid, Integer bid) {
			long currentTimeMillis = System.currentTimeMillis();

			QueryWrapper<Brrow> wrapper = new QueryWrapper<Brrow>();
			wrapper.eq("u_id", uid);
			wrapper.eq("ret_check", 0);
			wrapper.eq("brr_check", 2).or().eq("brr_check", 1);
			List<Brrow> list = this.list(wrapper);
			User user = userService.getById(uid);
			Book book = bookService.getById(bid);
			int a = 0;
			for (Brrow b : list) {
				if (b.getBrrCheck() == 2) {
					a++;
					if (a == 3) {
						return new ResultObj(SysConstate.CODE_ERROR, "借书数量超出，无法继续借书");
					}
				}
				if (b.getBookId().equals(bid)) {
					return new ResultObj(SysConstate.CODE_ERROR, "你已借阅或申请借阅了本书，无法继续借用");
				}

			}

			if (book.getTotal() <= 0) {
				return new ResultObj(SysConstate.CODE_ERROR, "书籍数量不足，无法借书");
			}
			 	Brrow brrow = new Brrow();
			 	brrow.setBookId(book.getId());
			 	brrow.setBookName(book.getName());
			 	brrow.setBrrCheck(1);
			 	long exceptTime = currentTimeMillis+(long)  30 * 24 * 60 * 60 * 1000;
			 	System.out.println(currentTimeMillis);
			 	System.out.println(exceptTime);
			 	brrow.setBrrowDate(new Timestamp(currentTimeMillis));
			 	brrow.setExpectDate(new Timestamp(exceptTime));
			 	brrow.setUId(uid);
			 	brrow.setRetCheck(0);
			 	boolean flag = this.save(brrow);
			 	if(flag) {
			 		return new ResultObj(SysConstate.CODE_SUCCESS,"申请借书成功");
			 				}
		        return new ResultObj(SysConstate.CODE_ERROR,"申请借书失败");
			
		}
		@Override
		public ResultObj ReturnBook(Integer id) {
			Brrow brrow = this.getById(id);
			Book book = bookService.getById(brrow.getBookId());
			brrow.setRetCheck(1);
			brrow.setReturnDate(new Timestamp(System.currentTimeMillis()));
			book.setTotal(book.getTotal()+1);
			bookService.updateById(book);
			return this.updateById(brrow)==true?ResultObj.RETURN_SUCCESS:ResultObj.RETURN_ERROR;
		}
		
		@Override
		public IPage<Brrow> SelectByPage(BrrowVo brrowVo) {
			IPage<Brrow> page = new Page<Brrow>(brrowVo.getPage(),brrowVo.getLimit());
			QueryWrapper<Brrow> wrapper = new QueryWrapper<Brrow>();
			wrapper.like(brrowVo.getBookName()!=null, "book_name", brrowVo.getBookName());
			wrapper.eq(brrowVo.getRetCheck()!=null,"ret_check", brrowVo.getRetCheck());
			wrapper.eq(brrowVo.getBrrCheck()!=null,"brr_check", brrowVo.getBrrCheck());
			wrapper.ge(brrowVo.getStartDate()!=null, "brrow_date", brrowVo.getStartDate());
			wrapper.le(brrowVo.getEndDate()!=null, "brrow_date", brrowVo.getEndDate());
			return brrowMapper.selectPage(page, wrapper);
		}
		
		@Override
		public IPage<Brrow> SelectbrrowBook(BrrowVo brrowVo) {
			IPage<Brrow> page = new Page<Brrow>(brrowVo.getPage(),brrowVo.getLimit());
			QueryWrapper<Brrow> wrapper = new QueryWrapper<Brrow>();
			wrapper.eq(brrowVo.getUId()!=null,"u_id", brrowVo.getUId());
			wrapper.like(brrowVo.getBookName()!=null, "book_name", brrowVo.getBookName());
			wrapper.eq(brrowVo.getRetCheck()!=null,"ret_check", brrowVo.getRetCheck());
			wrapper.eq(brrowVo.getBrrCheck()!=null,"brr_check", brrowVo.getBrrCheck());
			wrapper.ge(brrowVo.getStartDate()!=null, "brrow_date", brrowVo.getStartDate());
			wrapper.le(brrowVo.getEndDate()!=null, "brrow_date", brrowVo.getEndDate());
			
			return brrowMapper.selectPage(page, wrapper);
		}
		@Override
		public IPage<Brrow> findRank(Integer page, Integer limit) {
			Page<Brrow> pages = new Page<Brrow>(page,limit);
			pages.setRecords(brrowMapper.selectBrrowCountPageList(pages));
			
			return pages;
		}
		
		@Override
		@Transactional
		public ResultObj updateBrrowState(Brrow brrow) {
			Book book = bookService.getById(brrow.getBookId());
			User user = userService.getById(brrow.getUId());
			if(brrow.getBrrCheck()==0) {
				System.out.println(111);
				sendUtils.sendEmail(user, book, -1);
				brrowMapper.updateById(brrow);
		 		return	ResultObj.UPDATE_SUCCESS;
			}
			if(brrow.getBrrCheck()==2) {
				QueryWrapper<Brrow> wrapper = new QueryWrapper<Brrow>();
				wrapper.eq("u_id",brrow.getUId());
				wrapper.eq("ret_check",0);
				wrapper.eq("brr_check",2);
				if(brrowMapper.selectList(wrapper).size()>=3) {
					sendUtils.sendEmail(user, book, 4);
					return new ResultObj(SysConstate.CODE_ERROR,"该用户借书达三本，借出失败");
				}
				
				
			}
			if(book.getTotal()<=0) {
				sendUtils.sendEmail(user, book, 3);
				return new ResultObj(SysConstate.CODE_ERROR,"库存不足，借出失败");
			}
			if( brrowMapper.updateById(brrow)==1) {
			 		book.setTotal(book.getTotal()-1);
			 		sendUtils.sendEmail(user, book, 0);
			 		bookService.updateById(book);
			 		return	ResultObj.UPDATE_SUCCESS;
				}
			sendUtils.sendEmail(user, book, -1);
			return ResultObj.UPDATE_ERROR;
}
		@Override
		public List<Tongji> findRankbyAuthor() {
			List<Tongji> rank = brrowMapper.findRankbyAuthor();
			
			return rank;
		}
		@Override
		public List<Tongji> findRankbyType() {
			// TODO Auto-generated method stub
			List<Tongji> rank = brrowMapper.findRankbyType();
			return rank;
		}
		@Override
		public List<Tongji> findRankbyBook() {
			return  brrowMapper.findRankbyBook();
		}
		@Override
		public List<Integer> findRankbyMonth() {
			// TODO Auto-generated method stub
				List<Tongji> list = brrowMapper.findRankbyMonth();
				ArrayList<Integer> list2 = new ArrayList<Integer>();
				for(Tongji i:list) {
					list2.add(i.getValue());
				}
				return list2;
		}
		
		@Override
		public ResultObj remindUser(Integer uid, Integer bid) {
			// TODO Auto-generated method stub
			User user = userService.getById(uid);
			Book book = bookService.getById(bid);
			return sendUtils.sendEmail(user, book) == true ? new ResultObj(SysConstate.CODE_SUCCESS, "发送成功")
					: new ResultObj(SysConstate.CODE_ERROR, "发送失败");
		}
		@Override
		 public Map<User, List<Book>> getExpireUser() {
		  // TODO Auto-generated method stub
		  long currentTimeMillis = System.currentTimeMillis() + (long) 7 * 24 * 60 * 60 * 1000;
		  Timestamp tem_time = new Timestamp(currentTimeMillis);
		  Map<User, List<Book>> expireUser = new Hashtable<User, List<Book>>();
		  // 查询即将过期的用户
		  QueryWrapper<Brrow> wrapper = new QueryWrapper<Brrow>();
		  wrapper.le("expect_date", tem_time);// 28>20+7 不发 26<20+7 发
		  wrapper.eq("brr_check", 2);
		  wrapper.eq("ret_check", 0);
		  List<Brrow> brrows = list(wrapper);
		  // 循环获取User
		  for (Brrow brrow : brrows) {
		   List<Book> list = new ArrayList<Book>();
		   User user = userService.getById(brrow.getUId());
		   Book book = bookService.getById(brrow.getBookId());
		   if(expireUser.containsKey(user)==false) {
			   list.add(book);
			   expireUser.put(user, list);
		   }else {
			   List<Book> list2 = expireUser.get(user);
			   list2.add(book);
			   expireUser.put(user, list2);
		   }
		  }
		  return expireUser;
		 }
}
