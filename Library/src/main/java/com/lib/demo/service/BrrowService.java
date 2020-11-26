package com.lib.demo.service;

import com.lib.demo.bean.Book;
import com.lib.demo.bean.Brrow;
import com.lib.demo.bean.Rank;
import com.lib.demo.bean.Tongji;
import com.lib.demo.bean.User;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BrrowVo;

import java.util.List;
import java.util.Map;

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
public interface BrrowService extends IService<Brrow> {
	public ResultObj brrowbook(Integer uid, Integer bid);
    public ResultObj ReturnBook(Integer id);
    public IPage<Brrow> SelectByPage(BrrowVo brrowVo);
    public IPage<Brrow> SelectbrrowBook(BrrowVo brrowVo);
    public IPage<Brrow> findRank(Integer page,Integer limit);
	ResultObj updateBrrowState(Brrow brrow);
	List<Tongji> findRankbyAuthor();
	public List<Tongji> findRankbyType();
	public List<Integer> findRankbyMonth();
	public List<Tongji> findRankbyBook();
	public ResultObj remindUser(Integer uid, Integer bid);
	Map<User, List<Book>> getExpireUser();
	
}
